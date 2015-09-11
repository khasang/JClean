package ru.khasang.jclean.module;

import java.util.ArrayList;

/**
 * Created by GamBit on 12/09/15.
 */
public class JCFileProperty {
    //имя первого найденного дубликата
    private String name;

    //адреса всех файлов с одинаковыми размерами
    public ArrayList<String> copies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public JCFileProperty(String name, String absolutePath, String absolutePath2) {
        this.name = name;
        this.copies.add(absolutePath);
        this.copies.add(absolutePath2);
    }
}
