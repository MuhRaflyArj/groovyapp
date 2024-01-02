package main;

import dao.SongDAO;
import javafx.scene.layout.BorderPane;
import object.Playlist;
import object.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaylistController {
    public static void display(BorderPane root, Playlist playlist) {
        PlaylistView.display(root, playlist);
    }

    public static void deleteSong(Song song) {
        System.out.println(song.getSongID() + " deleted!!");
        // SongDAO.deleteSong(song.getSongID());
    }

    public static void playSong(Song selected, Playlist playlist) {
        MusicPlayerController.play(playlist, selected);
    }
}
