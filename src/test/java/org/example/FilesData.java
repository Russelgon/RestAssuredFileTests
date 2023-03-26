package org.example;

import java.io.File;

public class FilesData {
    private File fileName = new File("fileTest.txt");
    private String pathFile = "C:\\tests\\";
    private String pathFileForCreation = "C:\\tests\\borders\\";

    private String token = "IK2W3MS.KN6G0HB-4NYMR7B-J2Q8K1C-D6TGFMK";
    private long twoGB = 2; // Testing File.io is required 2GB in Web-application File.io 2GB = 1,86GB
    private int onePointNineGB = 2040109465;
    private int oneByte = 1;
    private int zeroByte = 0;

    public File getFileName() {
        return fileName;
    }

    public String getPathFile() {
        return pathFile;
    }

    public String getPathFileForCreation() {
        return pathFileForCreation;
    }

    public long getTwoGB() {
        return twoGB;
    }

    public int getOnePointNineGB() {
        return onePointNineGB;
    }

    public int getOneByte() {
        return oneByte;
    }

    public int getZeroByte() {
        return zeroByte;
    }

    public String getToken() {
        return token;
    }
}
