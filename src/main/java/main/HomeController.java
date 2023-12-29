package main;

import dao.SongDAO;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import object.Playlist;
import object.Song;

import java.util.ArrayList;
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
        System.out.println(song.getSongID()+" deleted!!");
    }


}
