package main;

import javafx.scene.layout.BorderPane;

public class AddPlaylistView {
    public static void display(BorderPane root) {
        AddPlaylistPopUp addPlaylistPopUp = new AddPlaylistPopUp();
        addPlaylistPopUp.display();
    }
}
