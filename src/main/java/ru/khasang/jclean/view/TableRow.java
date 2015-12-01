package ru.khasang.jclean.view;

public class TableRow {
    private int id;
    private String iconPath;
    private String path;
    private boolean check;

    public TableRow(String iconPath, String path, boolean check, int id) {
        this.iconPath = iconPath;
        this.path = path;
        this.check = check;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
