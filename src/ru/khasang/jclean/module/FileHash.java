package ru.khasang.jclean.module;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

public class FileHash {

    public static String getFastHash(File file, String hash) {
        byte[] fastMD5 = null;
        byte[] fastSHA1 = null;
        String fastHash = null;
        FileInputStream fis = null;
        MessageDigest ex = null;
        MessageDigest sha1 = null;
        try {
            ex = MessageDigest.getInstance("MD5");
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] buf = new byte[1024];
        int r = 0;
        try {
            r = (fis = new FileInputStream(file)).read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert ex != null;
        ex.update(buf, 0, r);
        assert sha1 != null;
        sha1.update(buf, 0, r);

        if(hash != null) {
            if(hash.toLowerCase() == "md5") {
                fastHash = new String(Hex.encodeHex(ex.digest()));
            }else if(hash.toLowerCase() == "sha1") {
                fastHash = new String(Hex.encodeHex(sha1.digest()));
            }
        }else {
            fastHash = new String(Hex.encodeHex(ex.digest()));
        }
        return fastHash;
    }
}
