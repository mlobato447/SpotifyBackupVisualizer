package com.mauricio.spotifybackupvisualizer;


import net.lingala.zip4j.ZipFile;

public class VerifyPath {
    public static boolean verify(String path) {

        ZipFile zipFile = new ZipFile(path);
        return zipFile.isValidZipFile();
    }
}
