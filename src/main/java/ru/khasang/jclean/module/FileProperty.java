package ru.khasang.jclean.module;

import java.io.File;

public class FileProperty {

    private boolean isMarked = false;
    private long size;
    private String path;
    private String fileType;
    private String fileName;
    private String fileExtension;
    private String hashCode;

    public FileProperty(File file) {
        setFileName(file.getName());
        setPath(file.getAbsolutePath());
        setSize(file.length());
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtensionAndType() {
        int dotPos = fileName.lastIndexOf(".");
        this.fileExtension = dotPos != -1 ? fileName.substring(dotPos + 1) : "";
        fileType = FileTypeQualifier.getFileType(fileExtension);
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileHashCode() { return hashCode; }

    public void setFileHashCode(String hashCode) { this.hashCode = hashCode; }

    public String toString () { return getFileName(); }

}
