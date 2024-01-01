package main;

import components.AppBars;
import components.Buttons;
import components.Images;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import object.Song;

import java.io.File;
import java.util.List;

public class SearchView {
    private static MenuItem deleteSong;
    private static ContextMenu songOptions = new ContextMenu();
    public static void display(BorderPane root, List<Song> searchedSong, String keyword) {
        VBox searchBox = new VBox(30);
        File searchStyle = new File("styles/searchStyles.css");
        root.getStylesheets().add(searchStyle.toURI().toString());

        searchBox.setPadding(new Insets(50,50,0,50));

        // Search bar/title
        HBox sectionTitle = AppBars.Search();
        Text searchedText = new Text("Result for \""+keyword+"\"");
        searchedText.getStyleClass().add("result-text");
        int iter = 1;
        VBox songListMost = new VBox(20);

        for (Song song: searchedSong) {
            // Create the row
            HBox songRow = new HBox();
            songRow.setAlignment(Pos.CENTER_LEFT);
            songRow.setMaxWidth(1050);
            // Create left side
            HBox sideLeft = new HBox(20);
            sideLeft.setAlignment(Pos.CENTER_LEFT);

            Text songNumber = new Text(iter<10 ? "0"+iter : String.valueOf(iter));
            songNumber.getStyleClass().addAll("song", "num");

            Button listPlay = Buttons.ButtonWithIcon("tabler-icon-player-play-inactive.png", 16, 16);
            listPlay.setOnAction(e -> handlePlay(song, listPlay));

            StackPane songThumb;
            if (song.getImagePath().equals("")) {
                songThumb = Images.ExtraSmall("empty-song-small.png");
            } else {
                songThumb = Images.ExtraSmall(song.getImagePath());
            }
            // Song detail is a vbox
            VBox songDetail = new VBox(-1);
            Text songTitle = new Text(song.getTitle());
            songTitle.getStyleClass().addAll("song", "title");

            Text songArtist = new Text(song.getArtist());
            songArtist.getStyleClass().addAll("song", "artist");

            songDetail.getChildren().addAll(songTitle,songArtist);
            // Assign child and setting hgrow
            sideLeft.getChildren().addAll(songNumber, listPlay, songThumb, songDetail);
            HBox.setHgrow(sideLeft, Priority.ALWAYS);
            // Add iteration adding child for the main row
            Button songOption = Buttons.ButtonWithIcon("tabler-icon-dots.png", 16,16);
            songRow.getChildren().addAll(sideLeft, songOption);
            songOption.setOnAction(e -> {
                handleContextMenu(song, songOption);
            });
            songRow.onContextMenuRequestedProperty().set(e -> {
                handleContextMenu(song, songOption);
            });
            // Add row to the main VBox
            songListMost.getChildren().add(songRow);
            iter++;
        }

        searchBox.getChildren().addAll(sectionTitle, searchedText, songListMost);
        root.setCenter(searchBox);
    }

    private static void handlePlay(Song song, Button clicked) {
        SearchController.playSong(song);
    }

    private static void handleContextMenu(Song song, Node target) {
        deleteSong.setOnAction(e -> {
            HomeController.deleteSong(song);
            songOptions.hide();
        });
        songOptions.show(target, Side.TOP, -20, 5);
    }
}
