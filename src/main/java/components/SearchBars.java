package components;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SearchBars {
    public static HBox SearchBar(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(SearchBars.class.getResource("searchbarStyles.css").toExternalForm());
        textField.getStyleClass().add("text-field");

        ImageView searchIcon = new ImageView(new Image(SearchBars.class.getResourceAsStream("icons/tabler-icon-search-inactive.png")));
        searchIcon.setFitHeight(16);
        searchIcon.setFitWidth(16);

        HBox searchBar = new HBox(15, searchIcon, textField);
        searchBar.getStylesheets().add(SearchBars.class.getResource("searchbarStyles.css").toExternalForm());
        searchBar.getStyleClass().add("search-bar");
        searchBar.setAlignment(Pos.CENTER_LEFT);

        return searchBar;
    }

}
