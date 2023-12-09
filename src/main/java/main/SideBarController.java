package main;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import object.Playlist;

import java.util.List;

public class SideBarController {

    public static void displayHome(BorderPane root) {
        HomeController.display(root);
    }

    public static void displayAddFile(BorderPane root) {
        AddFileController.display(root);
    }

    public static void displayAddPlaylist(BorderPane root) {
        AddPlaylistController.display(root);
    }

    public static void displayAllSong(BorderPane root) {
        AllSongController.display(root);
    }

    public static void displayPlaylist(BorderPane root, Playlist playlist) {
        PlaylistController.display(root, playlist);
    }

    public static void display(BorderPane root, List<Playlist> playlists) {
        SideBarView.display(root, playlists);
    }
}
