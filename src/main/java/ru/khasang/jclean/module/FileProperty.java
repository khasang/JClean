package ru.khasang.jclean.module;

import java.io.File;

public class FileProperty {

    private boolean isMarked = false;
    private long size;
    private String path;
    private String fileType;
    private String fileName;
    private String fileExtension;
    private boolean fileDeleteError;
    private boolean isFirstDouble = false;

    public FileProperty(File file) {
        setFileName(file.getName());
        setPath(file.getAbsolutePath());
        setSize(file.length());
    }

    public boolean isFirstDouble() {
        return isFirstDouble;
    }

    public void setIsFirstDouble(boolean isFirstDouble) {
        this.isFirstDouble = isFirstDouble;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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

    public void setFileDeleteError(boolean fileDeleteError) {
        this.fileDeleteError = fileDeleteError;
    }

    public boolean isFileDeleteError() {
        return fileDeleteError;
    }
}
