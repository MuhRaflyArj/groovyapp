package main;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import object.Playlist;

import java.util.List;

public class SideBarController {

    public static void displayHome() {

    }

    public static void displayAddFile(BorderPane root) {
        AddFileController.display(root);
    }

    public static void displayAddPlaylist() {

    }

    public static void displayAllSong(BorderPane root) {
        AllSongController.display(root);
    }

    public static void displayPlaylist(Playlist playlist) {

    }

    public static void display(BorderPane root, List<Playlist> playlists) {
        SideBarView.display(root, playlists);
    }
}
