package components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.File;

public class AppBars {

    public static HBox Home() {
        HBox appBarComponent = new HBox(25);
        HBox appBar = new HBox();

        HBox searchBar = SearchBars.SearchBar("search");
        Text titleText = new Text("Listen Now");
        titleText.getStyleClass().add("app-bar-text");

        appBarComponent.getChildren().addAll(titleText, searchBar);
        appBarComponent.getStyleClass().add("home");
        appBarComponent.setAlignment(Pos.CENTER_LEFT);

        appBar.getChildren().addAll(appBarComponent);
        File appBarStyles = new File("styles/appbarStyles.css");
        appBar.getStylesheets().add(appBarStyles.toURI().toString());
        appBar.getStyleClass().add("app-bar");

        return appBar;
    }

    public static HBox AllSongs() {
        HBox appBarComponent = new HBox(25);
        HBox appBar = new HBox(0);

        HBox searchBar = SearchBars.SearchBar("search");
        Text titleText = new Text("All Songs");
        titleText.setStyle("-fx-fill: #ffffff;");

        appBarComponent.getChildren().addAll(titleText, searchBar);
        appBarComponent.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBarComponent.getStyleClass().add("all-songs");
        appBarComponent.setAlignment(Pos.CENTER_LEFT);

        appBar.getChildren().addAll(appBarComponent);
        appBar.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBar.getStyleClass().add("app-bar");


        return appBar;
    }

    public static HBox Search() {
        HBox appBarComponent = new HBox(25);
        HBox appBar = new HBox(0);

        HBox searchBar = SearchBars.SearchBar("search");
        Text titleText = new Text("Search");
        titleText.setStyle("-fx-fill: #ffffff;");

        appBarComponent.getChildren().addAll(titleText, searchBar);
        appBarComponent.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBarComponent.getStyleClass().add("search");
        appBarComponent.setAlignment(Pos.CENTER_LEFT);

        appBar.getChildren().addAll(appBarComponent);
        appBar.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBar.getStyleClass().add("app-bar");

        return appBar;
    }

    public static HBox Return() {
        HBox appBarComponent = new HBox(25);
        HBox appBar = new HBox(0);

        Button back = Buttons.ButtonWithIcon("tabler-icon-chevron-left-inactive.png", 32, 32);
        appBarComponent.getChildren().addAll(back);

        appBar.getChildren().addAll(appBarComponent);
        appBar.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBar.getStyleClass().add("app-bar");

        return appBar;
    }

    public static HBox AddFiles() {
        HBox appBarComponent = new HBox(25);
        HBox appBar = new HBox(0);

        Button back = Buttons.ButtonWithIcon("tabler-icon-chevron-left-inactive.png", 32, 32);

        Text titleText = new Text("Add Files");
        titleText.setStyle("-fx-fill: #ffffff;");

        appBarComponent.getChildren().addAll(back, titleText);
        appBarComponent.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBarComponent.getStyleClass().add("add-files");
        appBarComponent.setAlignment(Pos.CENTER_LEFT);

        appBar.getChildren().addAll(appBarComponent);
        appBar.getStylesheets().addAll(AppBars.class.getResource("appbarStyles.css").toExternalForm());
        appBar.getStyleClass().add("app-bar");

        return appBar;
    }
}
