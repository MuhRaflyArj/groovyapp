package main;

import components.Buttons;
import components.Images;
import dao.PlaylistDAO;
import dao.SongDAO;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import object.Song;

import java.io.File;
import java.util.ArrayList;
public class MusicPlayerView {
    private static BorderPane root;
    private static boolean isShuffled = false;
    private static boolean isRepeated = false;
    private static boolean isPlaying = false;
    private static boolean isMute = false;
    static BorderPane musicPlayer = new BorderPane();
    private static Button shuffle = Buttons.ButtonWithIcon("tabler-icon-arrows-shuffle-inactive.png", 32, 32);
    private static Button prev = Buttons.ButtonWithIcon("tabler-icon-player-track-prev-inactive.png", 32, 32);
    private static Button play = Buttons.ButtonWithIcon("tabler-icon-player-play-inactive.png", 32, 32);
    private static Button next = Buttons.ButtonWithIcon("tabler-icon-player-track-next-inactive.png", 32, 32);
    private static Button repeat = Buttons.ButtonWithIcon("tabler-icon-reload-inactive.png", 32, 32);
    private static Button mute = Buttons.ButtonWithIcon("tabler-icon-volume-maxvolume.png", 32, 32);

    public static void setRoot(BorderPane newRoot) {
        MusicPlayerView.root = newRoot;
    }

    public static BorderPane getRoot() {
        return MusicPlayerView.root;
    }
    public static void MusicInfoDisplay(Song musicPlayed) {
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

        musicPlayer.setLeft(musicInfoDisplay);
    }

    public static void MusicMenu() {
        VBox musicMenu = new VBox(25);

        HBox controlButtons = new HBox(40);
        controlButtons.getChildren().addAll(shuffle, prev, play, next, repeat);
        controlButtons.setAlignment(Pos.CENTER);

        shuffle.setOnAction(e -> toggleShuffle());
        prev.setOnAction(e -> prev());
        play.setOnAction(e -> togglePlay());
        next.setOnAction(e -> next());
        repeat.setOnAction(e -> toggleRepeat());

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

        musicPlayer.setCenter(musicMenu);
    }

    public static void MusicVolume() {
        HBox musicVolume = new HBox(20);

        mute.setOnAction(e -> toggleMute());
        Slider volumeSlider = new Slider();
        volumeSlider.getStyleClass().add("seek-bar");

        musicVolume.getChildren().addAll(mute, volumeSlider);
        musicVolume.getStyleClass().add("music-volume");
        musicVolume.setAlignment(Pos.CENTER);

        musicPlayer.setRight(musicVolume);
    }

    public static void display(Song playedSong) {
        MusicPlayerController.play(PlaylistDAO.getPlaylistById("P001"), SongDAO.getSongById("S001"));
        musicPlayer.setMaxHeight(162);
        musicPlayer.setMinHeight(162);

        MusicMenu();
        MusicInfoDisplay(playedSong);
        MusicVolume();

        File musicplayerStyles = new File("styles/musicplayerStyles.css");
        musicPlayer.getStylesheets().add(musicplayerStyles.toURI().toString());
        musicPlayer.getStyleClass().add("music-player");

        MusicPlayerView.root.setBottom(musicPlayer);
    }

    private static void toggleShuffle() {
        if (MusicPlayerView.isShuffled) {
            MusicPlayerView.isShuffled = false;
            MusicPlayerController.setIsShuffled(false);
            Buttons.ButtonUpdateIcon(repeat, "tabler-icon-reload-inactive.png", 32, 32);
            Buttons.ButtonUpdateIcon(shuffle, "tabler-icon-arrows-shuffle-inactive.png", 32, 32);
        } else {
            MusicPlayerView.isShuffled = true;
            MusicPlayerView.isRepeated = false;
            MusicPlayerController.setIsShuffled(true);
            Buttons.ButtonUpdateIcon(repeat, "tabler-icon-reload-inactive.png", 32, 32);
            Buttons.ButtonUpdateIcon(shuffle, "tabler-icon-arrows-shuffle-active.png", 32, 32);
        }
    }

    public static void toggleRepeat() {
        if (MusicPlayerView.isRepeated) {
            MusicPlayerView.isRepeated = false;
            MusicPlayerController.setIsRepeated(false);
            Buttons.ButtonUpdateIcon(repeat, "tabler-icon-reload-inactive.png", 32, 32);
            Buttons.ButtonUpdateIcon(shuffle, "tabler-icon-arrows-shuffle-inactive.png", 32, 32);
        } else {
            MusicPlayerView.isRepeated = true;
            MusicPlayerView.isShuffled = false;
            MusicPlayerController.setIsRepeated(true);
            Buttons.ButtonUpdateIcon(repeat, "tabler-icon-reload-active.png", 32, 32);
            Buttons.ButtonUpdateIcon(shuffle, "tabler-icon-arrows-shuffle-inactive.png", 32, 32);
        }
    }

    public static void togglePlay() {
        if (MusicPlayerView.isPlaying) {
            MusicPlayerView.isPlaying = false;
            MusicPlayerController.setIsPlaying(false);
            Buttons.ButtonUpdateIcon(play,"tabler-icon-player-play-inactive.png", 32, 32);
        } else {
            MusicPlayerView.isPlaying = true;
            MusicPlayerController.setIsPlaying(true);
            Buttons.ButtonUpdateIcon(play, "tabler-icon-player-pause-inactive.png", 32, 32);
        }
    }

    public static void togglePlayIcon(boolean playStatus) {
        if (!playStatus) {
            MusicPlayerView.isPlaying = false;
            MusicPlayerController.setIsPlaying(false);
            Buttons.ButtonUpdateIcon(play,"tabler-icon-player-play-inactive.png", 32, 32);
        } else {
            MusicPlayerView.isPlaying = true;
            MusicPlayerController.setIsPlaying(true);
            Buttons.ButtonUpdateIcon(play, "tabler-icon-player-pause-inactive.png", 32, 32);
        }
    }

    private static void toggleMute() {
        MusicPlayerController.setIsMuted();
        if (MusicPlayerView.isMute) {
            MusicPlayerView.isMute = false;
            Buttons.ButtonUpdateIcon(mute,"tabler-icon-volume-maxvolume.png", 32, 32);
        } else {
            MusicPlayerView.isMute = true;
            Buttons.ButtonUpdateIcon(mute, "tabler-icon-volume-mute.png", 32, 32);
        }
    }

    private static void next() {
        MusicPlayerController.next();
    }

    private static void prev() {
        MusicPlayerController.prev();
    }
}
