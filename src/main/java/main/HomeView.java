package main;

import components.AppBars;
import components.Buttons;
import components.Images;
import components.SearchBars;
import dao.SongDAO;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import object.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class HomeView {
    private static ContextMenu songOptions = new ContextMenu();
    private static MenuItem deleteSong;
    private static List<Song> songs;
    public static void display(BorderPane root) {
        VBox homeBox = new VBox(30);
        File titleStyle = new File("styles/homeStyles.css");
        homeBox.getStylesheets().add(titleStyle.toURI().toString());
        homeBox.setPadding(new Insets(50,50,0,50));

        // Search bar/title
        HBox sectionTitle = AppBars.Home();

        // Frequently played label
        Text labelFreq = new Text("FOR YOU");
        labelFreq.getStyleClass().add("title-18");

        // Frequently played content
        HBox sectionFreq = new HBox();
        sectionFreq.setAlignment(Pos.CENTER);
        if (SongDAO.getAllSong().isEmpty()) {
            sectionFreq.setPrefHeight(200);
            Text textEmpty = new Text("Empty");
            textEmpty.getStyleClass().add("title-18");
            sectionFreq.getChildren().add(textEmpty);
        } else {
            songs = HomeController.recommendation(Math.min(SongDAO.getAllSong().size(), 6));

            for (Song song : songs) {
                Button imageButton;
                if (song.getImagePath().equals("")) {
                    imageButton = Buttons.ButtonWithImage("empty-song-large.png", 200, 200);
                } else {
                    imageButton = Buttons.ButtonWithImage(song.getImagePath(), 200, 200);
                }
                imageButton.setOnAction(actionEvent -> handlePlay(song, imageButton));

                HBox.setHgrow(imageButton, Priority.ALWAYS);
                sectionFreq.getChildren().add(imageButton);
            }
        }

        // Song Options Context Menu
        Text textDelete = new Text("Delete");
        textDelete.getStyleClass().add("text-delete");
        deleteSong = new MenuItem("", textDelete);
        deleteSong.getStyleClass().add("delete-option");
        songOptions.getStyleClass().add("context-menu");
        songOptions.getItems().add(deleteSong);


        // Most played
        HBox sectionMost = new HBox();

        VBox mostLeft = new VBox(30);
        mostLeft.setFillWidth(true);
        mostLeft.setAlignment(Pos.BASELINE_LEFT);

        Text labelMost = new Text("MOST PLAYED");
        labelMost.getStyleClass().add("title-18");

        songs = SongDAO.getMostPlayedSongs(4);
        int iter = 1;
        VBox songListMost = new VBox(20);

        for (Song song: songs) {
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

        mostLeft.getChildren().add(labelMost);
        mostLeft.getChildren().add(songListMost);

        HBox.setHgrow(mostLeft, Priority.ALWAYS);
        sectionMost.getChildren().add(mostLeft);

        // Custom button with text on top
        StackPane btnMostPane = new StackPane();
        VBox btnMostText = new VBox();
        btnMostText.setPadding(new Insets(29, 0, 0, 34));

        Text buttonMostTitle = new Text("All Song");
        buttonMostTitle.getStyleClass().add("btn-font-title");

        Text buttonMostBody = new Text("See your song");
        buttonMostBody.getStyleClass().add("btn-font-body");

        Text buttonMostSub = new Text(SongDAO.getAllSong().size() + " Songs");
        buttonMostSub.getStyleClass().add("btn-font-sub");

        btnMostText.getChildren().addAll(buttonMostTitle,buttonMostBody,buttonMostSub);
        btnMostText.setPrefHeight(236);
        btnMostText.setPrefWidth(384);

        File image_file = new File("images/main-bt-bg-1.png");
        ImageView btnMostBackground = new ImageView(new Image(image_file.toURI().toString()));

        Rectangle clipBtnMost = new Rectangle(384,236);
        clipBtnMost.setArcWidth(20);
        clipBtnMost.setArcHeight(20);
        btnMostBackground.setClip(clipBtnMost);

        btnMostPane.getChildren().addAll(btnMostBackground,btnMostText);

        Button buttonAllSong = new Button("", btnMostPane);
        File buttonStyles = new File("styles/buttonStyles.css");
        buttonAllSong.getStylesheets().add(buttonStyles.toURI().toString());
        buttonAllSong.getStyleClass().add("button-with-icon");

        buttonAllSong.setOnAction(actionEvent -> handleSwitchPage("AllSong"));

        sectionMost.getChildren().add(buttonAllSong);

        // Section Recently Played
        HBox sectionRecent = new HBox();

        VBox recentLeft = new VBox(30);
        recentLeft.setFillWidth(true);
        recentLeft.setAlignment(Pos.BASELINE_LEFT);

        Text labelRecent = new Text("RECENTLY PLAYED");
        labelRecent.getStyleClass().add("title-18");

        songs = SongDAO.recentlyPlayed(4);
        iter = 1;
        VBox songListRecent = new VBox(20);

        for (Song song: songs) {
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
            songOption.setOnAction(e -> handleContextMenu(song, songOption));
            songRow.onContextMenuRequestedProperty().set(e -> handleContextMenu(song, songOption));
            // Add row to the main VBox
            songListRecent.getChildren().add(songRow);
            iter++;
        }

        recentLeft.getChildren().add(labelRecent);
        recentLeft.getChildren().add(songListRecent);

        StackPane btnRecentPane = new StackPane();
        VBox btnRecentText = new VBox();
        btnRecentText.setPadding(new Insets(29, 0, 0, 34));

        Text buttonRecentTitle1 = new Text("Create");
        buttonRecentTitle1.getStyleClass().add("btn-font-title");

        Text buttonRecentTitle2 = new Text("Playlist");
        buttonRecentTitle2.getStyleClass().add("btn-font-title");

        btnRecentText.getChildren().addAll(buttonRecentTitle1, buttonRecentTitle2);
        btnRecentText.setPrefHeight(236);
        btnRecentText.setPrefWidth(384);

        File imageRecentFile = new File("images/main-bt-bg-2.png");
        ImageView btnRecentBackground = new ImageView(new Image(imageRecentFile.toURI().toString()));

        Rectangle clipBtnRecent = new Rectangle(384,236);
        clipBtnRecent.setArcWidth(20);
        clipBtnRecent.setArcHeight(20);
        btnRecentBackground.setClip(clipBtnRecent);

        btnRecentPane.getChildren().addAll(btnRecentBackground,btnRecentText);

        Button btnCreatePlaylist = new Button("", btnRecentPane);
        btnCreatePlaylist.getStylesheets().add(buttonStyles.toURI().toString());
        btnCreatePlaylist.getStyleClass().add("button-with-icon");

        btnCreatePlaylist.setOnAction(actionEvent -> handleSwitchPage("CreatePlaylist"));

        HBox.setHgrow(recentLeft, Priority.ALWAYS);
        sectionRecent.getChildren().add(recentLeft);
        sectionRecent.getChildren().add(btnCreatePlaylist);

        // Add child
        homeBox.getChildren().add(sectionTitle);
        homeBox.getChildren().add(labelFreq);
        homeBox.getChildren().add(sectionFreq);
        homeBox.getChildren().add(sectionMost);
        homeBox.getChildren().add(sectionRecent);

        homeBox.getStyleClass().add("center-pane");
        ScrollPane home = new ScrollPane(homeBox);
        home.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        home.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        home.getStyleClass().add("center-pane");
        home.setFitToWidth(true);
        home.setFitToHeight(true);
        root.setCenter(home);
    }

    private static void handlePlay(Song song, Button clicked) {
        HomeController.playSong(song);
    }

    private static void handleSwitchPage(String page) {
        HomeController.switchPage(page);
    }

    private static void handleContextMenu(Song song, Node target) {
        deleteSong.setOnAction(e -> {
            HomeController.deleteSong(song);
            songOptions.hide();
        });
        songOptions.show(target, Side.TOP, -20, 5);
    }

}