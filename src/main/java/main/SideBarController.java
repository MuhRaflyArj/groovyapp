package main;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import object.Playlist;

import java.util.List;

public class SideBarController {
    public static BorderPane root;

    public static void displayHome() {
        HomeController.display(SideBarController.root);
    }

    public static void displayAddFile() {
        AddFileController.display(SideBarController.root);
    }

    public static void displayAddPlaylist() {
        AddPlaylistController.display(SideBarController.root);
    }

    public static void displayAllSong() {
        AllSongController.display(SideBarController.root);
    }

    public static void displayPlaylist(Playlist playlist) {
        PlaylistController.display(SideBarController.root, playlist);
    }

    public static void display(BorderPane root, List<Playlist> playlists) {
        SideBarController.root = root;
        SideBarView.display(SideBarController.root, playlists);
    }
}
