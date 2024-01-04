package object;

import java.util.List;

public class Playlist {
    private String playlistID;
    private String name;
    private String desc;
    private List<String> songList;
    private String coverPath;

    public String getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getSongList() {
        return songList;
    }

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public void display(){
        System.out.println("PlaylistID       : " + playlistID);
        System.out.println("Name             : " + name);
        System.out.println("Desc             : " + desc);
        System.out.println("Song List        : " + songList);
        System.out.println("Cover Path       : " + coverPath);
    }
}
