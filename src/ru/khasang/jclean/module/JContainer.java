package ru.khasang.jclean.module;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;


public class JContainer {
    private static ArrayList<File> listWithFileNames = new ArrayList<>();
    private static HashMap<Long, File> duble = new HashMap<>();
    private static HashMap<String, File> hexMap = new HashMap<>();
    private static ArrayList<File> hexIdentical = new ArrayList<>();

    public static ArrayList<File> getHexIdentical() {
        return hexIdentical;
    }

    public static void getListFiles(String str) {
        File f = new File(str);
        for (File s : f.listFiles()) {
            if (s.isFile()) {
                File file = duble.get(s.length());
                if(file != null) {
                    int index = listWithFileNames.indexOf(file);
                    if (index == -1) {
                        listWithFileNames.add(file);
                    }
                    listWithFileNames.add(s);
                }else {
                    duble.put(s.length(), s);
                }
            } else if (s.isDirectory()) {
                getListFiles(s.getAbsolutePath());
            }
        }
    }

    public void FindAllIdenticalFiles(String path) throws NoSuchAlgorithmException {
        getListFiles(path);
        for (File fil : listWithFileNames) {
            String crc;
            crc = FileHash.getFastHash(fil, "MD5");
            File file = hexMap.get(crc);
            if(file != null) {
                if (hexIdentical.indexOf(file) == -1) {
                    hexIdentical.add(file);
                }
                hexIdentical.add(fil);
            }else {
                hexMap.put(crc, fil);
            }

        }
    }

}
