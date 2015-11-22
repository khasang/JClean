package ru.khasang.jclean.module;


import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Starter {

    static HashMap<String, ArrayList<FileProperty>> totalHexIdentical = new HashMap<>();

    public static HashMap<String, ArrayList<FileProperty>> startScanning() {

        ArrayList<String> paths = getListStores();
        ArrayList<Thread> threadList = new ArrayList<>();

        int i = 0;
        while (i < paths.size()) {
            JContainer jContainer = new JContainer();
            final int y = i;

            synchronized (threadList) {
                threadList.add(i, new Thread() {
                    @Override
                    public void run() {
                        jContainer.findAllIdenticalFiles(paths.get(y));
                        HashMap<String, ArrayList<FileProperty>> currentHexIdentical = jContainer.getHexIdentical();
                        totalHexIdentical.putAll(currentHexIdentical);
                    }
                });
            }
            i++;
        }

        synchronized (totalHexIdentical) {
            for (Thread aThreadList : threadList) {
                aThreadList.start();
            }
        }

        return totalHexIdentical;
    }

    private static ArrayList<String> getListStores() {
        FileSystem fs = FileSystems.getDefault();
        ArrayList<String> paths = new ArrayList<>();
        for (Path rootPath : fs.getRootDirectories()) {
            try {
                FileStore store = Files.getFileStore(rootPath);
                System.out.println(rootPath + ": " + store.type());
                if (store.type().equals("NTFS")) paths.add(rootPath.toString());
            } catch (IOException e) {
                System.out.println(rootPath + ": " + "<error getting store details>");
            }
        }
        return paths;
    }
}
