package ru.khasang.jclean.module;

import java.io.File;
import java.io.IOException;
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

    public void findAllIdenticalFiles(String folderPath) {
        hexIdentical.clear();
        filesOfDirectory.clear();
        File folder = new File(folderPath);
        findIdenticalFilesInFolder(folder);
    }

    private void findIdenticalFilesInFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
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
        String fileHash;
        for (FileProperty file : filesOfDirectory) {
            if (currentFile.getSize() == file.getSize()) {
                try {
                    fileHash = FileHash.getHash(file.getPath(), file.getSize());
                } catch (IOException e) {
                    continue;
                }
                if (currentFileHash == null) {
                    try {
                        currentFileHash = FileHash.getHash(currentFile.getPath(), currentFile.getSize());
                    } catch (IOException e) {
                        return;
                    }
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

    public void markAsDeleted(String hashStr, int index) {
        hexIdentical.get(hashStr).get(index).setIsMarked(true);
    }

    public void deleteFiles(ArrayList<String> hashWithNumbers, String splitter) {
        for (String s : hashWithNumbers) {
            String[] splitted = s.split("[" + splitter + "]"); //разбиваем строку и создаем массив из получившихся элементов
            String hash = splitted[0]; //в первой ячейке хранится хэш
            for (int i = 1; i < splitted.length; i++) { //пробегаем массив, начиная со второй ячейки
                int number = Integer.parseInt(splitted[i]);
                File file = new File(hexIdentical.get(hash).get(number).getPath());
                if (file.delete()) {
                    hexIdentical.get(hash).get(number).setFileDeleteError(false); //если файл удалился, помечаем флаг как false
                } else {
                    hexIdentical.get(hash).get(number).setFileDeleteError(true); //если файл не удалился, помечаем флаг как true
                }
            }
            deleteFilesFromMap(hash, splitted);
        }
    }

    public void deleteFilesFromMap(String hash, String[] splitted) {
        int[] numbers = new int[splitted.length - 1]; //создаем пустой массив для индексов файлов
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(splitted[i + 1]); //заполняем массив индексами файлов
        }
        Arrays.parallelSort(numbers); //быстрая сортировка элементов массива по возрастанию
        for (int i = numbers.length - 1; i >= 0; i--) { //пробегаем массив с конца
            int number = numbers[i];
            if (!hexIdentical.get(hash).get(number).isFileDeleteError()) { //если файл был удален с диска (флаг установлен как false), удалить его из карты
                hexIdentical.get(hash).remove(number);
            }
        }
    }

}
