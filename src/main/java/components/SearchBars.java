package components;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class SearchBars {
    public static HBox SearchBar(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStyleClass().add("text-field");

        File searchIconFile = new File("icons/tabler-icon-search-inactive.png");
        ImageView searchIcon = new ImageView(searchIconFile.toURI().toString());
        searchIcon.setFitHeight(16);
        searchIcon.setFitWidth(16);

        HBox searchBar = new HBox(1, searchIcon, textField);
        File searchBarStyles = new File ("styles/searchbarStyles.css");
        searchBar.getStylesheets().add(searchBarStyles.toURI().toString());
        searchBar.getStyleClass().add("search-bar");
        searchBar.setAlignment(Pos.CENTER_LEFT);

        return searchBar;
    }

}
