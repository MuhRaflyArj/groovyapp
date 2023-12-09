package main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HomeView {

    public static void display(BorderPane root) {
        StackPane home = new StackPane();
        Text temp = new Text("Ini Home");
        home.getChildren().add(temp);
        root.setCenter(home);
    }

}
