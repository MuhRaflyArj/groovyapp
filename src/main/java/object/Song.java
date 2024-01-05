package object;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Song {
    private String songID;
    private String title;
    private String artist;
    private String albumName;
    private int year;
    private int length;
    private int trackNo;
    private List<Integer> genre;
    private String filePath;
    private String imagePath;
    private Date lastPlayed;
    private int countPlayed;

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(int trackNo) {
        this.trackNo = trackNo;
    }

    public List<Integer> getGenre() {
        return genre;
    }

    public void setGenre(List<Integer> genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public int getCountPlayed() {
        return countPlayed;
    }

    public void setCountPlayed(int countPlayed) {
        this.countPlayed = countPlayed;
    }

    public void display(){
        System.out.println("SongID       : " + songID);
        System.out.println("Title        : " + title);
        System.out.println("Artist       : " + artist);
        System.out.println("Album Name   : " + albumName);
        System.out.println("Year         : " + year);
        System.out.println("Duration     : " + length);
        System.out.println("Track No     : " + trackNo);
        System.out.println("Genre        : " + genre);
        System.out.println("File Path    : " + filePath);
        System.out.println("Image Path   : " + imagePath);
        System.out.println("Last Played  : " + lastPlayed);
        System.out.println("Count Played : " + countPlayed);
        System.out.println();
    }

    public static Song emptySong() {
        Song song = new Song();
        song.setSongID("");
        song.setTitle("No Song");
        song.setTitle("");
        song.setAlbumName("");
        song.setYear(1970);
        song.setLength(0);
        song.setTrackNo(0);
        song.setFilePath("/");
        song.setImagePath("/");
        return song;
    }
}
