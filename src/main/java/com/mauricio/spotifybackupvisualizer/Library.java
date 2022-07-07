package com.mauricio.spotifybackupvisualizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class Library {

    public static YourLibrary library(String path) throws IOException {
        String json = "";
        Gson gson = new Gson();
        ZipFile zipFile = new ZipFile(path);
        FileHeader fileHeader = zipFile.getFileHeader("MyData/YourLibrary.json");
        InputStream input = zipFile.getInputStream(fileHeader);
        json = new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.joining("\n"));
        zipFile.close();
        return gson.fromJson(json, YourLibrary.class);
    }
    
}
