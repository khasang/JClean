package ru.khasang.jclean.module;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    public static long getTotalDublicates(HashMap<String, ArrayList<FileProperty>> hexIdentical) {
        long i = 0;
        for (ArrayList arrayList : hexIdentical.values()){
            i += arrayList.size();
        }
        return i;
    }

    public static int getGroupTotalDublicates(HashMap<String, ArrayList<FileProperty>> hexIdentical, String type){
        int i = 0;
        for (ArrayList<FileProperty> arrayList : hexIdentical.values()){
            for (FileProperty property : arrayList) {
                if (type.equals(property.getFileType())) {
                    i += 1;
                }
            }
        }
        return i;
    }
}