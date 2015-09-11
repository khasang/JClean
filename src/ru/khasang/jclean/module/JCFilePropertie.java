package ru.khasang.jclean.module;

import java.util.ArrayList;

/**
 * Created by GamBit on 12/09/15.
 */
public class JCFilePropertie {
    //имя первого найденного дубликата
    private String name;

    //адреса всех файлов с одинаковыми размерами
    public ArrayList<String> copies = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public JCFilePropertie(String name, String absolutePath, String absolutePath2) {
        this.name = name;
        this.copies.add(absolutePath);
        this.copies.add(absolutePath2);
    }
}
