package ru.khasang.jclean.module;

/**
 класс в котором будет отражаться инфа о том, помечен файл на удаление или нет,
 где находится, какое расширение имеет, какой у него размер.
 */
public class JCFilePropertie {

    private boolean isMarked = false;
    private long size;
    private String path;
    private String fileType;
    private String fileName;

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
}
