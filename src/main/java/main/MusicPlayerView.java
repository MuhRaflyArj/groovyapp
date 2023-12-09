package main;

import components.Buttons;
import components.Images;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import object.Song;

import java.io.File;
import java.util.ArrayList;
public class MusicPlayerView {
    private static HBox MusicInfoDisplay(Song musicPlayed) {
        HBox musicInfoDisplay = new HBox(25);

        musicInfoDisplay.setMinWidth(360);
        musicInfoDisplay.getStyleClass().add("music-display");

        StackPane musicImage = Images.Small(musicPlayed.getImagePath());

        Text title = new Text(musicPlayed.getTitle());
        title.getStyleClass().add("title");

        Text artist = new Text(musicPlayed.getArtist());
        artist.getStyleClass().add("artist");

        VBox musicName = new VBox(0);
        musicName.getStyleClass().add("music-name");
        musicName.setAlignment(Pos.CENTER_LEFT);
        musicName.getChildren().addAll(title, artist);

        musicInfoDisplay.getChildren().addAll(musicImage, musicName);
        musicInfoDisplay.setAlignment(Pos.CENTER_LEFT);

        return musicInfoDisplay;
    }

    public static VBox MusicMenu() {
        VBox musicMenu = new VBox(25);

        HBox controlButtons = new HBox(40);
        Button shuffle = Buttons.ButtonWithIcon("tabler-icon-arrows-shuffle-inactive.png", 32, 32);
        Button prev = Buttons.ButtonWithIcon("tabler-icon-player-track-prev-inactive.png", 32, 32);
        Button play = Buttons. ButtonWithIcon("tabler-icon-player-play-inactive.png", 32, 32);
        Button next = Buttons.ButtonWithIcon("tabler-icon-player-track-next-inactive.png", 32, 32);
        Button repeat = Buttons.ButtonWithIcon("tabler-icon-reload-inactive.png", 32, 32);
        controlButtons.getChildren().addAll(shuffle, prev, play, next, repeat);
        controlButtons.setAlignment(Pos.CENTER);

        Slider songSeeker = new Slider();
        songSeeker.getStyleClass().add("seek-bar");
        songSeeker.setMinWidth(750);

        HBox seek = new HBox(10);

        Text start = new Text("0.00");
        start.getStyleClass().add("duration");
        Text end = new Text("3.04");
        end.getStyleClass().add("duration");

        seek.getChildren().addAll(start, songSeeker ,end);

        musicMenu.getChildren().addAll(controlButtons, seek);
        musicMenu.setMaxWidth(750);
        musicMenu.getStyleClass().add("music-menu");
        musicMenu.setAlignment(Pos.CENTER);

        return musicMenu;
    }

    public static HBox MusicVolume() {
        HBox musicVolume = new HBox(20);

        Button mute = Buttons.ButtonWithIcon("tabler-icon-volume-maxvolume.png", 32, 32);
        Slider volumeSlider = new Slider();
        volumeSlider.getStyleClass().add("seek-bar");

        musicVolume.getChildren().addAll(mute, volumeSlider);
        musicVolume.getStyleClass().add("music-volume");
        musicVolume.setAlignment(Pos.CENTER);

        return musicVolume;
    }

    public static void display(BorderPane root, Song playedSong) {
        BorderPane musicPlayer = new BorderPane();
        musicPlayer.setMaxHeight(162);
        musicPlayer.setMinHeight(162);

        VBox musicMenu = MusicMenu();
        HBox musicDisplay = MusicInfoDisplay(playedSong);
        HBox musicVolume = MusicVolume();


        musicPlayer.setCenter(musicMenu);
        musicPlayer.setLeft(musicDisplay);
        musicPlayer.setRight(musicVolume);
        File musicplayerStyles = new File("styles/musicplayerStyles.css");
        musicPlayer.getStylesheets().add(musicplayerStyles.toURI().toString());
        musicPlayer.getStyleClass().add("music-player");

        root.setBottom(musicPlayer);
    }
}
