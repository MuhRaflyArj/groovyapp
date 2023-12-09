package main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class AddFileView {
    public static void display(BorderPane root) {
        StackPane allSong = new StackPane();
        Text temp = new Text("Ini Add File");
        allSong.getChildren().add(temp);
        root.setCenter(allSong);

    }
}
