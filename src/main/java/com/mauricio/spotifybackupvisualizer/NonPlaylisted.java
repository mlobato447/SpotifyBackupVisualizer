package com.mauricio.spotifybackupvisualizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class NonPlaylisted {

    public static List<String> nonPlaylisted(String path) throws IOException {
        ZipFile zipFile = new ZipFile(path);
        FileHeader fileHeaderMusics = zipFile.getFileHeader("MyData\\YourLibrary.json");
        InputStream inputMusics = zipFile.getInputStream(fileHeaderMusics);
        FileHeader fileHeaderPlaylist = zipFile.getFileHeader("MyData\\Playlist1.json");
        InputStream inputPlaylist = zipFile.getInputStream(fileHeaderPlaylist);
        String jsonMusics = new BufferedReader(new InputStreamReader(inputMusics)).lines()
                .collect(Collectors.joining("\n"));
        String jsonPlaylist = new BufferedReader(new InputStreamReader(inputPlaylist)).lines()
                .collect(Collectors.joining("\n"));
        zipFile.close();
        Gson gson = new Gson();
        YourLibrary yourLibrary = gson.fromJson(jsonMusics, YourLibrary.class);
        Playlist playlist = gson.fromJson(jsonPlaylist, Playlist.class);
        int cont = 0;
        List<String> unlistedMusics = new ArrayList<>();

        for (Tracks tracks : yourLibrary.getTracks()) {
            for (Playlists playlists : playlist.getPlaylists()) {
                for (Items items : playlists.getItems()) {
                    if (tracks.getTrack().equals(items.getTrack().getTrackName())
                            && tracks.getArtist().equals(items.getTrack().getArtistName())) {
                        cont++;
                    }
                }
            }
            if (cont == 0) {
                unlistedMusics.add(tracks.getTrack());
            }
            cont = 0;
        }
        return unlistedMusics;
    }

}
