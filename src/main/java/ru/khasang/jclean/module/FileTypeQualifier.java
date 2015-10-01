package ru.khasang.jclean.module;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamBit on 29/09/15.
 */
public class FileTypeQualifier {

    private static final String[] AUDIO = {
            "aa", "aac", "amr", "ape", "asf", "cda", "flac", "lav", "mp3", "mt9", "ogg", "voc", "wav", "wma"
    };
    private static final String[] GRAPHIC = {
            //растровые
            "bmp", "cpt", "djvu", "gif", "hdr", "jpeg", "jpg", "jpe", "jp2", "pcx", "pdf", "pdn",
            "png", "psd", "tga", "tiff", "tif", "wdp", "hdp", "xpm",
            //векторные
            "ai", "cdr", "cgm", "eps", "pdf", "ps", "svg", "sai", "wmf", "emf"
    };
    private static final String[] TEXT = {
            "txt", "rtf", "doc", "docx", "odt", "sxw", "xls", "xlsx"
    };
    private static final String[] VIDEO = {
            "3g2", "3gp", "3gp2", "3gpp", "3gpp2", "asf", "asx", "avi", "bin", "dat",
            "drv", "f4v", "flv", "gtp", "h264", "m4v", "mkv", "mod", "moov", "mov", "mp4",
            "mpeg", "mpg", "mts", "rm", "spl", "srt", "stl", "swf", "vcd", "vid", "vob",
            "wm", "wmv", "yuv"
    };
    private static HashMap<String, String[]> typeList = new HashMap<String, String[]>() {{
        put("audio", AUDIO);
        put("graphic", GRAPHIC);
        put("video", VIDEO);
        put("text", TEXT);
    }};

    public static String getFileType(String fileExtension) {
        for (Map.Entry<String, String[]> type : typeList.entrySet()) {
            if (compareWithExtensionList(type.getValue(), fileExtension)) {
                return type.getKey();
            }
        }
        return "other";
    }

    private static boolean compareWithExtensionList(String[] extensionList, String fileExtension) {
        for (String extension : extensionList) {
            if (fileExtension.compareToIgnoreCase(extension) == 0) {
                return true;
            }
        }
        return false;
    }
}
