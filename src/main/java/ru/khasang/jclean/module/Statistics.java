package ru.khasang.jclean.module;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {
    public static ArrayList<StatsReport> getTotalReport(HashMap<String, ArrayList<FileProperty>> hexIdentical) {
        long amountAll = 0;
        long sizeAll = 0;
        for (ArrayList arrayList : hexIdentical.values()){
            amountAll += arrayList.size();
            for (int j = 0; j < arrayList.size(); j++) {
                FileProperty currentFile = (FileProperty) arrayList.get(j);
                sizeAll += currentFile.getSize();
            }
        }

        ArrayList<StatsReport> result = new ArrayList<>();

        StatsReport statsAllReport = new StatsReport();
        statsAllReport.setTypeName("all");
        statsAllReport.setSize(sizeAll);
        statsAllReport.setSize(sizeAll);

        result.add(statsAllReport);

        return result;
    }

}