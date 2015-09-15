package ru.khasang.jclean.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class JContainer {

    private HashMap<String, ArrayList<JCFileProperty>> hexIdentical = new HashMap<>();
    private ArrayList<JCFileProperty> filesOfDirectory = new ArrayList<>();
    private ArrayList<String> fileFolders;

    public HashMap<String, ArrayList<JCFileProperty>> getHexIdentical() {
        return hexIdentical;
    }

    public ArrayList<String> getFileFolders() {
        return fileFolders;
    }

    public void setFileFolders(ArrayList<String> fileFolders) {
        this.fileFolders = fileFolders;
    }

    public void findAllIdenticalFiles() {
        for (String folderPath : fileFolders) {
            File folder = new File(folderPath);
            findIdenticalFilesInFolder(folder);
        }
    }

    private void findIdenticalFilesInFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findIdenticalFilesInFolder(file);
                }
                if (file.isFile()) {
                    JCFileProperty currentFile = new JCFileProperty(file);
                    findDuplicatesInFiles(currentFile);
                    filesOfDirectory.add(currentFile);
                }
            }
        }
    }

    private void findDuplicatesInFiles(JCFileProperty currentFile) {
        String currentFileHash = null;
        for (JCFileProperty file : filesOfDirectory) {
            if (currentFile.getSize() == file.getSize()) {
                String fileHash = FileHash.getFastHash(file.getPath());
                if (currentFileHash == null) {
                    currentFileHash = FileHash.getFastHash(currentFile.getPath());
                }
                if (currentFileHash.equals(fileHash)) {
                    addFileToDuplicates(currentFile, currentFileHash, file);
                    return;
                }
            }
        }
    }

    private void addFileToDuplicates(JCFileProperty currentFile, String currentFileHash, JCFileProperty fileFromList) {
        if (hexIdentical.get(currentFileHash) == null) {
            ArrayList<JCFileProperty> arrayWithCopies = new ArrayList<>();
            arrayWithCopies.add(currentFile);
            arrayWithCopies.add(fileFromList);
            hexIdentical.put(currentFileHash, arrayWithCopies);
        } else {
            hexIdentical.get(currentFileHash).add(currentFile);
        }
    }
}
