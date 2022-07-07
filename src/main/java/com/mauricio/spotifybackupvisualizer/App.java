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
            System.out.printf(">>Current path:" + path + "\n>>");
            comand = scan.nextLine();
            switch (comand) {
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
                case "exit":
                    exitFlag = true;
                    break;
                default:
                    System.out.println("Comando inválido.");
            }
        }
    }
}
