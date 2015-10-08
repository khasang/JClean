package ru.khasang.jclean.module;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JContainer {

    private HashMap<String, ArrayList<FileProperty>> hexIdentical = new HashMap<>();
    private ArrayList<FileProperty> filesOfDirectory = new ArrayList<>();
    private ArrayList<String> fileFolders;

    public HashMap<String, ArrayList<FileProperty>> getHexIdentical() {
        return hexIdentical;
    }

    public ArrayList<String> getFileFolders() {
        return fileFolders;
    }

    public void setFileFolders(ArrayList<String> fileFolders) {
        this.fileFolders = fileFolders;
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
                } else if (currentFileHash.equals(fileHash)) {
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

    public void markAsDeleted(String hashStr, int index) {
        hexIdentical.get(hashStr).get(index).setIsMarked(true);
    }

    public void deleteFiles(ArrayList<String> hashWithNumbers, String splitter) {
        for (String s : hashWithNumbers) {
            String[] splitted = s.split("[" + splitter + "]"); //��������� ������ � ������� ������ �� ������������ ���������
            String hash = splitted[0]; //� ������ �������� �������� ���
            for (int i = 1; i < splitted.length; i++) { //��������� ������, ������� �� ������� ��������
                int number = Integer.parseInt(splitted[i]);
                File file = new File(hexIdentical.get(hash).get(number).getPath());
                if (file.delete()) {
                    hexIdentical.get(hash).get(number).setFileDeleteError(false); //���� ���� ��������, �������� ���� ��� false
                } else {
                    hexIdentical.get(hash).get(number).setFileDeleteError(true); //���� ���� ���������� �������, �������� ���� ��� true
                }
            }
            deleteFilesFromMap(hash, splitted);
        }
    }

    public void deleteFilesFromMap(String hash, String[] splitted) {
        int[] numbers = new int[splitted.length - 1]; //������� ������ ������ ��� �������� ������
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(splitted[i + 1]); //��������� ������ ��������� ������
        }
        Arrays.parallelSort(numbers); //������� ���������� ��������� ������� �� �����������
        for (int i = numbers.length - 1; i >= 0; i--) { //��������� ������ � �����
            int number = numbers[i];
            if (!hexIdentical.get(hash).get(number).isFileDeleteError()) { // ���� ���� ��� ������ � �����, ������� ��� �� �����
                hexIdentical.get(hash).remove(number);
            }
        }
    }

}
