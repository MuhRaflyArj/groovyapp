package main;

import components.SearchBars;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class AllSongView {

    public static void display(BorderPane root) {
        VBox allSong = new VBox();
        allSong.getStyleClass().add("all-song");
        VBox allSongComponent = new VBox(30);

        Text textTitle = new Text("All Songs");
        textTitle.getStyleClass().add("title");

        HBox searchBar = SearchBars.SearchBar("Search");

        HBox titleandsearchContainer = new HBox(15);
        titleandsearchContainer.getChildren().addAll(textTitle, searchBar);

        allSongComponent.getChildren().addAll(titleandsearchContainer);

        VBox.setMargin(allSongComponent, new Insets(25, 25, 0, 25));
        File addFileStyles = new File ("styles/allsongStyles.css");
        allSongComponent.getStylesheets().add(addFileStyles.toURI().toString());

        allSong.getStylesheets().add(addFileStyles.toURI().toString());
        allSong.getChildren().add(allSongComponent);
        root.setCenter(allSong);

    }
}
