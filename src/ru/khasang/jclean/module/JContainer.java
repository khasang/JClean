package ru.khasang.jclean.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class JContainer {

    private HashMap<String, ArrayList<JCFilePropertie>> hexIdentical = new HashMap<>();
    private ArrayList<JCFilePropertie> filesOfDirectory = new ArrayList<>();
    private ArrayList<String> fileFolders;

    public HashMap<String, ArrayList<JCFilePropertie>> getHexIdentical() {
        return hexIdentical;
    }

    public ArrayList<String> getFileFolders() {
        return fileFolders;
    }

    public void setFileFolders(ArrayList<String> fileFolders) {
        this.fileFolders = fileFolders;
    }

    public void FindAllIdenticalFiles() {
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
                    JCFilePropertie currentFile = new JCFilePropertie();
                    currentFile.setFileName(file.getName());
                    currentFile.setPath(file.getAbsolutePath());
                    currentFile.setSize(file.length());

                    findDuplicatesInFiles(currentFile);
                    filesOfDirectory.add(currentFile);
                }
            }
        }
    }

    private void findDuplicatesInFiles(JCFilePropertie currentFile) {
        String currentFileHash = null;
        for (JCFilePropertie file : filesOfDirectory) {
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

    private void addFileToDuplicates(JCFilePropertie currentFile, String currentFileHash, JCFilePropertie fileFromList) {
        if (hexIdentical.get(currentFileHash) == null) {
            ArrayList<JCFilePropertie> arrayWithCopies = new ArrayList<>();
            arrayWithCopies.add(currentFile);
            arrayWithCopies.add(fileFromList);
            hexIdentical.put(currentFileHash, arrayWithCopies);
        } else {
            hexIdentical.get(currentFileHash).add(currentFile);
        }
    }
}
