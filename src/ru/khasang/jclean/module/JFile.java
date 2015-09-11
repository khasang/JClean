package ru.khasang.jclean.module;

/**
 * Created by GamBit on 12/09/15.
 */
public class JFile {

    long size;
    String absolutePath, name;

    public JFile(long size, String absolutePath, String name) {
        this.size = size;
        this.absolutePath = absolutePath;
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public String getName() {
        return name;
    }
}
