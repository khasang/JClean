package ru.khasang.jclean.module;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class JContainer {

    //список дубликатов
    //ключ - хеш; значение - объект с данными дубликатов
    private HashMap<String, JCFileProperty> duplicates = new HashMap<>();
    //список файлов в просканированной дирктории
    private ArrayList<JFile> nameList = new ArrayList<>();

    //сканирует всю директорию
    public HashMap<String, JCFileProperty> searchDuplicates(File directory) {
        nameList.clear();
        duplicates.clear();
        scanFolder(directory);
        return duplicates;
    }


    //сканирует файлы в папке на совпадения
    private void scanFolder(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && !file.isHidden()) {
                    scanFolder(file);
                }
                if (file.isFile()) {
                    JFile f = new JFile(file.length(), file.getAbsolutePath(), file.getName());
                    try {
                        checkDuplicate(f);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    nameList.add(f);
                }
            }
        }
    }

    //проверяет совпадения по размеру и добавляет в список дубликатов
    private void checkDuplicate(JFile file) throws Exception {
        String path1 = file.getAbsolutePath();
        String fileHash = HashMD5.getMD5Checksum(path1);
        long fileSize = file.getSize();
        for (JFile name : nameList) {
            String path2 = name.getAbsolutePath();

            if (fileSize == name.getSize() && fileHash.equals(HashMD5.getMD5Checksum(path2))) {
                if (duplicates.get(fileHash) == null) {
                    duplicates.put(fileHash, new JCFileProperty(file.getName(), path1, path2));
                } else {
                    duplicates.get(fileHash).addPathToCopiesList(path1);
                }
                return;
            }
        }
    }

    //преобразует размер файла в сокращенный вариант. Например 123456байт в 123,45КБ
    private String convertFileSize(long size) {
        if (size > 1000000000) {
            return size / 1000000000 + "," + (size - (size / 1000000000) * 1000000000) / 10000000 + "GB";
        } else if (size > 1000000) {
            return size / 1000000 + "," + (size - (size / 1000000) * 1000000) / 10000 + "MB";
        } else if (size > 1000) {
            return size / 1000 + "," + (size - (size / 1000) * 1000) / 10 + "KB";
        }
        return size + "B";
    }

}
