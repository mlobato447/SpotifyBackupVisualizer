package com.mauricio.spotifybackupvisualizer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean exitFlag = false;
        String path = "";
        Scanner scan = new Scanner(System.in);
        String comand = "";
        while (!exitFlag) {
            System.out.printf("Caminho atual:" + path + "\n>>");
            comand = scan.nextLine();
            switch (comand) {
                case "help":
                    System.out.println("'path' - seta o caminho para arquivo de backup(em formato zip).");
                    System.out.println("'library' - lista todas as músicas curtidas.");
                    System.out.println("'nonplaylisted' - lista músicas curtidas que não estão en nenhuma playlist.");
                    System.out.println("'twiceplaylisted' - lista músicas curtidas presentes em mais de uma playlist.");
                    System.out.println("'playlists' - lista todas as playlists.");
                    System.out.println("'exit' - finaliza o programa.");
                    break;

                case "path":
                    System.out.printf(">>set path to: ");
                    path = scan.nextLine();
                    if (!VerifyPath.verify(path)) {
                        System.out.println("Não é um arquizo zip válido.");
                        path = "";
                    }
                    break;
                case "library":
                    if (path.length() == 0) {
                        System.out.println("Caminho do arquivo não especificado.");
                        break;
                    }
                    try {
                        YourLibrary yourLibraries = Library.library(path);
                        for (Tracks tracks : yourLibraries.getTracks()) {
                            System.out.println(tracks.getTrack() + " - " + tracks.getArtist());
                        }
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao tentar acessar o arquivo.");
                    }
                    break;

                case "nonplaylisted":
                    if (path.length() == 0) {
                        System.out.println("Caminho do arquivo não especificado.");
                        break;
                    }
                    try {
                        List<String> unlistedMusics = NonPlaylisted.nonPlaylisted(path);
                        for (String str : unlistedMusics) {
                            System.out.println(str);
                        }
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao tentar acessar o arquivo.");
                    }
                    break;
                case "twiceplaylisted":
                    if (path.length() == 0) {
                        System.out.println("Caminho do arquivo não especificado.");
                        break;
                    }
                    try {
                        List<String> duplicatedMusics = TwicePlaylisted.twicePlaylisted(path);
                        if (duplicatedMusics.isEmpty()) {
                            System.out.println("Nenhuma música em mais de uma playlist ao mesmo tempo.");
                        } else {
                            for (String str : duplicatedMusics) {
                                System.out.println(str);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao tentar acessar o arquivo.");
                    }
                    break;
                case "playlists":
                    if (path.length() == 0) {
                        System.out.println("Caminho de arquivo não especificado.");
                        break;
                    }
                    try {

                        List<String> playlists = PlaylistList.getList(path);
                        if (playlists.isEmpty()) {
                            System.out.println("Sem playlists.");
                        } else {
                            for (String str : playlists) {
                                System.out.println(str);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao tentar acessar o arquivo.");
                        break;
                    }
                    boolean getPlaylistsFlag = true;
                    while (getPlaylistsFlag) {
                        System.out.printf(">>Escolha uma playlist para exibir(deixe em branco para sair): ");
                        String playlistName = scan.nextLine();
                        if (playlistName.isBlank()) {
                            getPlaylistsFlag = false;
                        } else {
                            try {
                                Playlists playlistMusics = PlaylistList.getMusics(path, playlistName);
                                if (playlistMusics.getItems() == null) {
                                    System.out.println("Playlist não encontrada.");
                                } else {
                                    for (Items str : playlistMusics.getItems()) {
                                        System.out.println(
                                                str.getTrack().getTrackName() + " - " + str.getTrack().getArtistName());
                                    }
                                }

                            } catch (IOException e) {
                                System.out.println("Ocorreu um erro ao tentar acessar o arquivo.");
                            }
                        }
                    }
                    break;

                case "exit":
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Comando inválido.");
            }
        }
    }
}
