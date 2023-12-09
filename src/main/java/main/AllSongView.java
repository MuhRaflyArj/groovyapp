package main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AllSongView {

    public static void display(BorderPane root) {
        StackPane allSong = new StackPane();
        Text temp = new Text("Ini All Song");
        allSong.getChildren().add(temp);
        root.setCenter(allSong);

    }
}
