package main;

import dao.SongDAO;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import object.Playlist;
import object.Song;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class HomeController {
    private static final Playlist allSong = new Playlist();

    public static void display(BorderPane root) {
        HomeView.display(root);
    }

    public static void playSong(Song selected) {
        List<String> songIdList = new ArrayList<>();
        SongDAO.getAllSong()
                .forEach(song ->
                        songIdList.add(song.getSongID())
                );
        allSong.setSongList(songIdList);
        allSong.setDesc("");
        allSong.setName("");

        MusicPlayerController.play(allSong, selected);
    }

    public static void switchPage(String page) {
        if (page.equals("AllSong")) {
            SideBarController.displayAllSong();
        } else if (page.equals("CreatePlaylist")) {
            SideBarController.displayAddPlaylist();
        }
    }

    public static void deleteSong(Song song) {
        System.out.println(song.getSongID() + " deleted!!");
        // SongDAO.deleteSong(song.getSongID());
    }

    public static List<Song> recommendation(int limit) {
        List<Song> allSong = SongDAO.getAllSong();
        Date currDate = new Date();
        allSong.sort(new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                long v1 = (long) ((Math.pow(o1.getCountPlayed(), 2) * 10) - (currDate.getTime() / 1000 - o1.getLastPlayed().getTime() / 1000));
                long v2 = (long) ((Math.pow(o2.getCountPlayed(), 2) * 10) - (currDate.getTime() / 1000 - o2.getLastPlayed().getTime() / 1000));
                return (int) (v2 - v1);
            }
        });
        return allSong.subList(0, limit);
    }
}
