package ru.khasang.jclean.module;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {

    public static String getHash(String path) {
        StringBuffer hexString = null;
        try (FileInputStream fin = new FileInputStream(path);// создаем поток чтения байтов из файла
             BufferedInputStream bis = new BufferedInputStream(fin, 3072))// создаем буферизированный поток (для увеличения производительности) чтения байтов из файла; размер буфера 3072 байта
        {
            int size = bis.available();// получаем количество доступных для чтения байтов в файле
            byte[] buff = new byte[size];
            if (bis.read(buff) != size) {// читаем байты из файла и записываем в вспомогательный массив
                System.err.println("Нельзя прочитать байты");
                return null;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");// создаем объект, инкапсулирующий алгоритм MD5
            md5.reset();
            md5.update(buff);// генерируем хэш-код на основе данных из вспомогательного массива
            byte[] hash = md5.digest();// получаем хэш-код в другой вспомогательный массив
            hexString = new StringBuffer();
            for (byte hashData : hash) {// переводим хэш-код в 16-ое представление
                String hex = Integer.toHexString(0xff & hashData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }


    public static String getHashFaster(String path) {
        StringBuffer hexString = null;
        try (FileInputStream fin = new FileInputStream(path)) // создаем поток чтения байтов из файла
        {
            int size = fin.available(); // получаем количество доступных для чтения байтов в файле
            if (size <= 2048) { // если размер файла не более 2048, то читаем весь файл целиком
                return getHash(path);
            }
            byte[] buff = new byte[2048]; // создаем вспомогательный массив байтов, куда будем записывать байты из фала для последующей передащи функции хеширования
            if (fin.read(buff, 0, 1024) != 1024) {// читаем первые 1024 байта в первую половину вспомогательного массива
                System.err.println("Нельзя прочитать первые " + 1024 + " байтов");
                return null;
            }

            fin.skip(size - 2048); // пропустить все байты перед последними 1024

            if (fin.read(buff, 1024, 1024) != 1024) {// читаем последние 1024 байта во вторую половину вспомогательного массива
                System.err.println("Нельзя прочитать последние " + 1024 + " байтов");
                return null;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");// создаем объект, инкапсулирующий алгоритм MD5
            md5.reset();
            md5.update(buff);// генерируем хэш-код на основе данных из вспомогательного массива
            byte[] hash = md5.digest();// получаем хэш-код в другой вспомогательный массив
            hexString = new StringBuffer();
            for (byte hashData : hash) {// переводим хэш-код в 16-ое представление
                String hex = Integer.toHexString(0xff & hashData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

}
