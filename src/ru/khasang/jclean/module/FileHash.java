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
        try (FileInputStream fin = new FileInputStream(path);// ������� ����� ������ ������ �� �����
             BufferedInputStream bis = new BufferedInputStream(fin, 3072))// ������� ���������������� ����� (��� ���������� ������������������) ������ ������ �� �����; ������ ������ 3072 �����
        {
            int size = bis.available();// �������� ���������� ��������� ��� ������ ������ � �����
            byte[] buff = new byte[size];
            if (bis.read(buff) != size) {// ������ ����� �� ����� � ���������� � ��������������� ������
                System.err.println("������ ��������� �����");
                return null;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");// ������� ������, ��������������� �������� MD5
            md5.reset();
            md5.update(buff);// ���������� ���-��� �� ������ ������ �� ���������������� �������
            byte[] hash = md5.digest();// �������� ���-��� � ������ ��������������� ������
            hexString = new StringBuffer();
            for (byte hashData : hash) {// ��������� ���-��� � 16-�� �������������
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
        try (FileInputStream fin = new FileInputStream(path)) // ������� ����� ������ ������ �� �����
        {
            int size = fin.available(); // �������� ���������� ��������� ��� ������ ������ � �����
            if (size <= 2048) { // ���� ������ ����� �� ����� 2048, �� ������ ���� ���� �������
                return getHash(path);
            }
            byte[] buff = new byte[2048]; // ������� ��������������� ������ ������, ���� ����� ���������� ����� �� ���� ��� ����������� �������� ������� �����������
            if (fin.read(buff, 0, 1024) != 1024) {// ������ ������ 1024 ����� � ������ �������� ���������������� �������
                System.err.println("������ ��������� ������ " + 1024 + " ������");
                return null;
            }

            fin.skip(size - 2048); // ���������� ��� ����� ����� ���������� 1024

            if (fin.read(buff, 1024, 1024) != 1024) {// ������ ��������� 1024 ����� �� ������ �������� ���������������� �������
                System.err.println("������ ��������� ��������� " + 1024 + " ������");
                return null;
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");// ������� ������, ��������������� �������� MD5
            md5.reset();
            md5.update(buff);// ���������� ���-��� �� ������ ������ �� ���������������� �������
            byte[] hash = md5.digest();// �������� ���-��� � ������ ��������������� ������
            hexString = new StringBuffer();
            for (byte hashData : hash) {// ��������� ���-��� � 16-�� �������������
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
