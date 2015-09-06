package ru.khasang.jclean.view;


import java.util.ArrayList;

public class CommunicationsProtocol {
    private boolean readyReport;
    private SearchMethod searchMethod;
    private ArrayList<String> foldersList;
    private StepType stepType;
    private enum SearchMethod {
        FULL, MANUAL
    }
    private enum StepType {

    }
    public SearchMethod getSeatchMethod() {
        return searchMethod;
    }

    public void setSearchMethod(SearchMethod searchMethod) {
        this.searchMethod = searchMethod;
    }

    public boolean isReadyReport() {
        return readyReport;
    }

    public ArrayList<String> getFoldersList() {
        return foldersList;
    }

    public void setFoldersList(ArrayList<String> foldersList) {
        this.foldersList = foldersList;
    }
}
