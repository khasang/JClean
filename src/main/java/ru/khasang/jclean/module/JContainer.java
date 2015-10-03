package ru.khasang.jclean.module;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class JContainer {

    private HashMap<String, ArrayList<FileProperty>> hexIdentical = new HashMap<>();
    private ArrayList<FileProperty> filesOfDirectory = new ArrayList<>();
    private ArrayList<String> fileFolders;
    private HashMap<String, ArrayList<FileProperty>> filesToDeleteMap = new HashMap<>();
    private ArrayList<FileProperty> filesToDeleteList = new ArrayList<>();
    private HashMap<String, Path> tempFilesMap = new HashMap<>();

    public HashMap<String, ArrayList<FileProperty>> getHexIdentical() {
        return hexIdentical;
    }

    public ArrayList<String> getFileFolders() {
        return fileFolders;
    }

    public void setFileFolders(ArrayList<String> fileFolders) {
        this.fileFolders = fileFolders;
    }

    public HashMap<String, ArrayList<FileProperty>> getFilesToDeleteMap() {
        return filesToDeleteMap;
    }

    public HashMap<String, Path> getTempFilesMap() {
        return tempFilesMap;
    }

    public void findAllIdenticalFiles() {
        hexIdentical.clear();
        filesOfDirectory.clear();
        for (String folderPath : fileFolders) {
            File folder = new File(folderPath);
            findIdenticalFilesInFolder(folder);
        }
    }

    private void findIdenticalFilesInFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.canRead()) continue;
                if (file.isDirectory()) {
                    findIdenticalFilesInFolder(file);
                } else if (file.isFile()) {
                    FileProperty currentFile = new FileProperty(file);
                    findDuplicatesInFiles(currentFile);
                    filesOfDirectory.add(currentFile);
                }
            }
        }
    }

    private void findDuplicatesInFiles(FileProperty currentFile) {
        String currentFileHash = null;
        for (FileProperty file : filesOfDirectory) {
            if (currentFile.getSize() == file.getSize()) {
                String fileHash = FileHash.getHash(file.getPath(), file.getSize());
                if (currentFileHash == null) {
                    currentFileHash = FileHash.getHash(currentFile.getPath(), currentFile.getSize());
                }
                if (currentFileHash.equals(fileHash)) {
                    addFileToDuplicates(currentFile, currentFileHash, file);
                    return;
                }
            }
        }
    }

    private void addFileToDuplicates(FileProperty currentFile, String currentFileHash, FileProperty fileFromList) {
        currentFile.setFileExtensionAndType();
        if (hexIdentical.get(currentFileHash) == null) {
            fileFromList.setFileExtensionAndType();
            ArrayList<FileProperty> arrayWithCopies = new ArrayList<>();
            arrayWithCopies.add(currentFile);
            arrayWithCopies.add(fileFromList);
            hexIdentical.put(currentFileHash, arrayWithCopies);
        } else {
            hexIdentical.get(currentFileHash).add(currentFile);
        }
    }

    public void markDublicateFiles(String hashStr, int index) {
        hexIdentical.get(hashStr).get(index).setIsMarked(true);
    }

    public void collectFiles(FileProperty currentFile) {
        setFilesToDeleteMap(currentFile.getFileHashCode(), currentFile);
    }

    private void setFilesToDeleteMap(String currentFileHash, FileProperty currentFile) {
        filesToDeleteMap.clear();
        if (filesToDeleteMap.get(currentFileHash) == null) {
            filesToDeleteList.add(currentFile);
            filesToDeleteMap.put(currentFileHash, filesToDeleteList);
        } else {
            filesToDeleteMap.get(currentFileHash).add(currentFile);
        }
    }

    public void deleteFiles(HashMap<String, ArrayList<FileProperty>> filesToDeleteMap) {
        try {
            Set<String> set = filesToDeleteMap.keySet();
            for (String s : set) {
                createTempFile(s, filesToDeleteMap.get(s).get(0));
                for (FileProperty fileToDelete : filesToDeleteMap.get(s)) {
                    Path file = Paths.get(fileToDelete.getPath());
                    Files.delete(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTempFile(String currentFileHash, FileProperty currentFile) {
        try {
            Path tempFile = Files.createTempFile(currentFile.getFileName(), ".tmp");
            Path file = Paths.get(currentFile.getPath());
            Files.copy(file, tempFile, StandardCopyOption.REPLACE_EXISTING);
            tempFile.toFile().deleteOnExit();
            tempFilesMap.put(currentFileHash, tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreFiles(HashMap<String, ArrayList<FileProperty>> filesToDeleteMap, HashMap<String, Path> tempFilesMap) {
        try {
            Set<String> set = filesToDeleteMap.keySet();
            for (String s : set) {
                for (FileProperty f : filesToDeleteMap.get(s)) {
                    Files.copy(tempFilesMap.get(s), Paths.get(f.getPath()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
