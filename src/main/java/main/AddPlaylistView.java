package main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import object.Playlist;

public class AddPlaylistView {
    public static void display(BorderPane root) {
        StackPane addPlaylist = new StackPane();
        Text temp = new Text("Add Playlist");
        addPlaylist.getChildren().add(temp);
        root.setCenter(addPlaylist);

    }
}
