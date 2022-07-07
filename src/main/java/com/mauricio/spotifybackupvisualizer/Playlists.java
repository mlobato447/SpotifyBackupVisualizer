package com.mauricio.spotifybackupvisualizer;

public class Playlists {
    private String name;
    private String lastModifiedDate;
    private Items[] items;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Items[] getItems() {
        return this.items;
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

}
