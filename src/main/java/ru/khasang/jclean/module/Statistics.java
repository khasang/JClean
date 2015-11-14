package ru.khasang.jclean.module;

import java.util.ArrayList;
import java.util.HashMap;

public class Statistics {

    public static ArrayList<StatsReport> getTotalReport(HashMap<String, ArrayList<FileProperty>> hexIdentical) {

        StatsReport allFilesReport = new StatsReport("all", 0, 0);
        StatsReport audioFilesReport = new StatsReport("audio", 0, 0);
        StatsReport graphicFilesReport = new StatsReport("graphic", 0, 0);
        StatsReport videoFilesReport = new StatsReport("video", 0, 0);
        StatsReport textFilesReport = new StatsReport("text", 0, 0);

        for (ArrayList arrayList : hexIdentical.values()) {
            for (Object anArrayList : arrayList) {

                FileProperty currentFile = (FileProperty) anArrayList;

                allFilesReport.setAmount(allFilesReport.getAmount() + 1);
                allFilesReport.setSize(allFilesReport.getSize() + currentFile.getSize());

                switch (currentFile.getFileType()) {

                    case "audio":
                        audioFilesReport.setAmount(audioFilesReport.getAmount() + 1);
                        audioFilesReport.setSize(audioFilesReport.getSize() + currentFile.getSize());
                        break;

                    case "graphic":
                        graphicFilesReport.setAmount(graphicFilesReport.getAmount() + 1);
                        graphicFilesReport.setSize(graphicFilesReport.getSize() + currentFile.getSize());
                        break;

                    case "video":
                        videoFilesReport.setAmount(videoFilesReport.getAmount() + 1);
                        videoFilesReport.setSize(videoFilesReport.getSize() + currentFile.getSize());
                        break;

                    case "text":
                        textFilesReport.setAmount(textFilesReport.getAmount() + 1);
                        textFilesReport.setSize(textFilesReport.getSize() + currentFile.getSize());
                        break;

                }
            }
        }

        ArrayList<StatsReport> result = new ArrayList<>();

        result.add(allFilesReport);
        result.add(audioFilesReport);
        result.add(graphicFilesReport);
        result.add(videoFilesReport);
        result.add(textFilesReport);

        return result;
    }

}