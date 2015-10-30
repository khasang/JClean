package ru.khasang.jclean.module;

import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JContainer {
    private HashMap<String, ArrayList<FileProperty>> hexIdentical = new HashMap<>();
    private HashMap<Long, FileProperty> sizeDoubles = new HashMap<>();
    private HashMap<String, FileProperty> hexDoubles = new HashMap<>();

    private ArrayList<String> fileFolders;

    public HashMap<String, ArrayList<FileProperty>> getHexIdentical() {
        return hexIdentical;
    }

    public void setFileFolders(ArrayList<String> fileFolders) {
        this.fileFolders = fileFolders;
    }

    public void findAllIdenticalFiles() {
        hexIdentical.clear();
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
                } else if (file.isFile()) {
                    FileProperty currentFile = new FileProperty(file);
                        try {
                            makeMagic(currentFile);
                        } catch (IOException e) {
                        }
                }
            }
        }
    }

    private void makeMagic(FileProperty currentFile) throws IOException {
        FileProperty fromMapFile = sizeDoubles.get(currentFile.getSize());
        if(fromMapFile != null) { // Опа! Нашли дубль
            String curHex = FileHash.getHash(currentFile.getPath(), currentFile.getSize());
            String mapHex = FileHash.getHash(fromMapFile.getPath(), fromMapFile.getSize());
            if(curHex.equals(mapHex)) { //Внатуре одинаковые
                if(fromMapFile.isFirstDouble()){
                    addToHexIdentical(currentFile, curHex);
                }else {
                    fromMapFile.setIsFirstDouble(true);
                    addToHexIdentical(fromMapFile, mapHex);
                    addToHexIdentical(currentFile, curHex);
                }
            } else { //Размер один, а файлы то, разные
                FileProperty fp = hexDoubles.get(curHex);
                if(fp != null) {
                    if(fp.isFirstDouble()) {
                        addToHexIdentical(currentFile, curHex);
                    }else {
                        fp.setIsFirstDouble(true);
                        addToHexIdentical(currentFile, curHex);
                        addToHexIdentical(fp, curHex);
                    }
                } else {
                    hexDoubles.put(curHex, currentFile);
                }
            }
        } else {
            sizeDoubles.put(currentFile.getSize(), currentFile);
        }
    }

    private void addToHexIdentical(FileProperty fileProperty, String hex) {
        fileProperty.setFileExtensionAndType();
        if (hexIdentical.get(hex) == null) {
            ArrayList<FileProperty> arrayWithCopies = new ArrayList<>();
            arrayWithCopies.add(fileProperty);
            hexIdentical.put(hex, arrayWithCopies);
        } else {
            hexIdentical.get(hex).add(fileProperty);
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
