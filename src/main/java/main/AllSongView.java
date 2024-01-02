package main;

import components.Buttons;
import components.Images;
import components.SearchBars;
import dao.SongDAO;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import object.Song;
import javafx.scene.layout.VBox;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static main.HomeView.handleContextMenu;
import static main.HomeView.handlePlay;

public class AllSongView {

    public static void display(BorderPane root) {
        VBox allSong = new VBox();
        allSong.getStyleClass().add("all-song");
        VBox allSongComponent = new VBox(30);

        Text textTitle = new Text("All Songs");
        textTitle.getStyleClass().add("title");

        HBox searchBar = SearchBars.SearchBar("Search");

        HBox titleAndSearchContainer = new HBox(15);
        titleAndSearchContainer.getChildren().addAll(textTitle, searchBar);

        allSongComponent.getChildren().addAll(titleAndSearchContainer);

        VBox.setMargin(allSongComponent, new Insets(25, 25, 25, 50));
        File addFileStyles = new File("styles/allsongStyles.css");
        allSongComponent.getStylesheets().add(addFileStyles.toURI().toString());

        allSong.getStylesheets().add(addFileStyles.toURI().toString());
        allSong.getChildren().add(allSongComponent);

        // Logic to display the list of all songs from the database
        List<Song> allSongs = SongDAO.getAllSong();

        Collections.sort(allSongs, Comparator.comparing(Song::getTitle));

        for (int i = 0; i < allSongs.size(); i++) {
            Song song = allSongs.get(i);
            HBox songRow = createSongRow(song);
            // Set nomor urut lagu secara dinamis berdasarkan indeks
            setTextForSongNumber(songRow, i + 1);
            allSong.getChildren().add(songRow);
        }

        ScrollPane scrollPaneSong = new ScrollPane(allSong);
        scrollPaneSong.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneSong.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPaneSong.setFitToWidth(true);
        scrollPaneSong.setFitToHeight(true);
        root.setCenter(scrollPaneSong);

    }

    private static void updateDisplay(VBox allSong, ObservableList<Song> filteredSongs) {
        // Clear existing display and add filtered songs
        allSong.getChildren().clear();
        for (int i = 0; i < filteredSongs.size(); i++) {
            Song song = filteredSongs.get(i);
            HBox songRow = createSongRow(song);
            setTextForSongNumber(songRow, i + 1);
            allSong.getChildren().add(songRow);
        }
    }

    private static void setTextForSongNumber(HBox songRow, int songNumber) {
        Text songNumberText = (Text) songRow.lookup(".num");
        songNumberText.setText(String.format("%02d", songNumber));
    }

    private static HBox createSongRow(Song song) {
        HBox songRow = new HBox();
        songRow.setAlignment(Pos.CENTER_LEFT);
        songRow.setMaxWidth(1050);

        HBox sideLeft = new HBox(20);
        sideLeft.setAlignment(Pos.CENTER_LEFT);

        Text songNumber = new Text("01"); // You need to adjust the song number
        songNumber.getStyleClass().addAll("song", "num");

        Button listPlay = Buttons.ButtonWithIcon("tabler-icon-player-play-inactive.png", 16, 16);
        listPlay.setOnAction(e -> handlePlay(song, listPlay));

        StackPane songThumb;
        if (song.getImagePath().equals("")) {
            songThumb = Images.ExtraSmall("empty-song-small.png");
        } else {
            songThumb = Images.ExtraSmall(song.getImagePath());
        }

        VBox songDetail = new VBox(-1);
        Text songTitle = new Text(song.getTitle());
        songTitle.getStyleClass().addAll("song", "title");

        Text songArtist = new Text(song.getArtist());
        songArtist.getStyleClass().addAll("song", "artist");

        songDetail.getChildren().addAll(songTitle, songArtist);

        VBox.setMargin(songRow, new Insets(0, 0, 10, 50));

        sideLeft.getChildren().addAll(songNumber, listPlay, songThumb, songDetail);
        HBox.setHgrow(sideLeft, Priority.ALWAYS);

        Button songOption = Buttons.ButtonWithIcon("tabler-icon-dots.png", 16, 16);
        songRow.getChildren().addAll(sideLeft, songOption);

        songOption.setOnAction(e -> handleContextMenu(song, songOption));
        songRow.onContextMenuRequestedProperty().set(e -> handleContextMenu(song, songOption));

        return songRow;
    }
}
