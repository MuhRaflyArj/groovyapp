package main;

import components.AppBars;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import object.Song;

import java.io.File;
import java.util.List;

public class SearchView {
    public static void display(BorderPane root, List<Song> searchedSong) {
        VBox searchBox = new VBox(30);
        File titleStyle = new File("styles/searchStyles.css");
        searchBox.getStylesheets().add(titleStyle.toURI().toString());
        searchBox.setPadding(new Insets(50,50,0,50));

        // Search bar/title
        HBox sectionTitle = AppBars.Search();

        searchBox.getChildren().add(sectionTitle);
        root.setCenter(searchBox);

    }
}
