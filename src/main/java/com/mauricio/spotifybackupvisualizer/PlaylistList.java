package com.mauricio.spotifybackupvisualizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class PlaylistList {
    public static List<String> getList(String path) throws IOException {
        ZipFile zipFile = new ZipFile(path);
        FileHeader fileHeader = zipFile.getFileHeader("MyData\\Playlist1.json");
        InputStream inputStream = zipFile.getInputStream(fileHeader);
        String json = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        Playlist playlist = gson.fromJson(json, Playlist.class);
        List<String> playlistList = new ArrayList<>();
        for (Playlists playlists : playlist.getPlaylists()) {
            playlistList.add(playlists.getName());
        }
        return playlistList;
    }

    public static Playlists getMusics(String path, String playlistName) throws IOException {
        ZipFile zipFile = new ZipFile(path);
        FileHeader fileHeader = zipFile.getFileHeader("MyData\\Playlist1.json");
        InputStream inputStream = zipFile.getInputStream(fileHeader);
        String json = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        Playlist playList = gson.fromJson(json, Playlist.class);
        Playlists playlists = new Playlists();
        for (Playlists ply : playList.getPlaylists()) {
            if (ply.getName().equals(playlistName)) {
                playlists = ply;
                break;
            }
        }
        return playlists;
    }
}
