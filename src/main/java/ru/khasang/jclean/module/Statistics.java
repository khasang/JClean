package ru.khasang.jclean.module;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by demon on 30.10.2015.
 */
public class Statistics {
    public static long getTotalDublicates(HashMap<String, ArrayList<FileProperty>> hexIdentical) {
        long i = 0;
        for (ArrayList arrayList : hexIdentical.values()){
            i += arrayList.size();
        }
        return i;
    }

    public static int getGroupDublicates(HashMap<String, ArrayList<FileProperty>> hexIdentical, String type){
        int i = 0;
        for (ArrayList<FileProperty> arrayList : hexIdentical.values()){
            for (int j = 0; j < arrayList.size(); j++) {
                if (type.equals(arrayList.get(j).getFileType())){
                    i += 1;
                }
            }
        }
        return i;
    }
}
