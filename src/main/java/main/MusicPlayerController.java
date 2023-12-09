package main;

import javafx.scene.layout.BorderPane;
import object.Song;

public class MusicPlayerController {

    public static void display(BorderPane root, Song playedSong) {
        MusicPlayerView.display(root, playedSong);
    }

}
