package main;

import javafx.scene.layout.BorderPane;
import object.Playlist;

public class PlaylistController {
    public static void display(BorderPane root, Playlist playlist) {
        PlaylistView.display(root, playlist);
    }
}
