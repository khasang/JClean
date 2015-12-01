package ru.khasang.jclean.module;

import java.util.ArrayList;

public class FolderContainer {
    private static FolderContainer instanse;
    private ArrayList<String> folders = new ArrayList<>();

    private FolderContainer() {
    }

    public ArrayList<String> getFolders() {
        return this.folders;
    }

    public void setFolder(String folder) {
        this.folders.add(folder);
    }

    public int getNumbersOfFoldes() {
        return this.folders.size();
    }

    public void removeFolder(String folder) {
        this.folders.remove(folder);
    }

    public static FolderContainer getInstant() {
        if(instanse == null){
            instanse = new FolderContainer();
        }
        return instanse;
    }
}
