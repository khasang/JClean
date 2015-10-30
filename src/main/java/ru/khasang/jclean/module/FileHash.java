package ru.khasang.jclean.module;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {

    private final static int PERCENT = 1;

    public static int getPERCENT() {
        return PERCENT;
    }

    public static String getHash(String path, long size) throws IOException {
        long currentBufferReadPiece = size * PERCENT / 100;
        int bufferReadPiece = (int) currentBufferReadPiece;
        return getFastHash(path, size, bufferReadPiece);
    }

    public static String getFastHash(String path, long fileSize, int bufferReadPiece) throws IOException {
        byte[] hash = null;
        try (FileInputStream fin = new FileInputStream(path)) // создаем поток чтения байтов из файла
        {
            int bufferSize = bufferReadPiece * 2;
            byte[] buff = new byte[bufferSize]; // создаем вспомогательный массив байтов, куда будем записывать байты из файла для последующей передащи функции хэширования
            fin.read(buff, 0, bufferReadPiece); // читаем байты с начала файла в первую половину вспомогательного массива
            fin.skip(fileSize - bufferSize); // пропустить все байты перед последним куском
            fin.read(buff, bufferReadPiece, bufferReadPiece); // читаем байты в конце файла во вторую половину вспомогательного массива
            MessageDigest md5 = MessageDigest.getInstance("MD5");// создаем объект, инкапсулирующий алгоритм MD5
            md5.reset();
            md5.update(buff);// генерируем хэш-код на основе данных из вспомогательного массива
            hash = md5.digest();// получаем хэш-код в другой вспомогательный массив
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // переводим хэш-код в 16-ое представление
        StringBuilder hexString = new StringBuilder();
        if (hash != null) {
            for (byte hashData : hash) {
                String hex = Integer.toHexString(0xff & hashData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        }
        return hexString.toString();
    }
}
