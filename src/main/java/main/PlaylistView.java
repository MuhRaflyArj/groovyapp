package main;

import components.AppBars;
import components.Buttons;
import components.Images;
import dao.SongDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import object.Playlist;
import object.Song;

import java.io.File;

public class PlaylistView {
    private static MenuItem deleteSong;
    private static ContextMenu songOptions = new ContextMenu();
    public static void display(BorderPane root, Playlist playlist) {
        VBox allSong = new VBox(30);
        HBox appBar = AppBars.Return();

        HBox playlistInfo = new HBox(25);

        StackPane playlistImage = Images.Medium(playlist.getCoverPath());

        VBox playlistDesc = new VBox(10);
        Text title = new Text(playlist.getName());
        title.getStyleClass().add("title");
        Text desc = new Text(playlist.getDesc());
        desc.getStyleClass().add("desc");
        playlistDesc.getChildren().addAll(title, desc);

        playlistInfo.getChildren().addAll(playlistImage, playlistDesc);

        // Tinggal add function untuk button
        HBox buttons = new HBox(15);
        Button addSong = Buttons.ButtonWithIcon("tabler-icon-plus-inactive.png", 32, 32);
        Button more = Buttons.ButtonWithIcon("tabler-icon-dots-vertical-inactive.png", 32, 32);
        buttons.getChildren().addAll(addSong, more);

        int iter = 1;
        VBox songList = new VBox(20);

        for (String songID: playlist.getSongList()) {
            Song song = SongDAO.getSongById(songID);
            // Create the row
            HBox songRow = new HBox();
            songRow.setAlignment(Pos.CENTER_LEFT);
            songRow.setMaxWidth(1280);
            // Create left side
            HBox sideLeft = new HBox(20);
            sideLeft.setAlignment(Pos.CENTER_LEFT);

            Text songNumber = new Text(iter<10 ? "0"+iter : String.valueOf(iter));
            songNumber.getStyleClass().addAll("song-num");

            Button listPlay = Buttons.ButtonWithIcon("tabler-icon-player-play-inactive.png", 16, 16);
            listPlay.setOnAction(e -> handlePlay(song, playlist, listPlay));

            StackPane songThumb;
            if (song.getImagePath().equals("")) {
                songThumb = Images.ExtraSmall("empty-song-small.png");
            } else {
                songThumb = Images.ExtraSmall(song.getImagePath());
            }
            // Song detail is a vbox
            VBox songDetail = new VBox(-1);
            Text songTitle = new Text(song.getTitle());
            songTitle.getStyleClass().addAll("song-title");

            Text songArtist = new Text(song.getArtist());
            songArtist.getStyleClass().addAll("song-artist");

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
            songList.getChildren().add(songRow);
            iter++;
        }

        allSong.getChildren().addAll(appBar, playlistInfo, buttons, songList);

        File playlistStyles = new File("styles/playlistStyles.css");
        allSong.getStylesheets().add(playlistStyles.toURI().toString());
        allSong.getStyleClass().add("playlist");
        allSong.setPadding(new Insets(50,50,0,50));

        root.setCenter(allSong);
    }

    private static void handlePlay(Song song, Playlist playlist,Button clicked) {
        PlaylistController.playSong(song, playlist);
    }

    private static void handleContextMenu(Song song, Node target) {
        deleteSong.setOnAction(e -> {
            PlaylistController.deleteSong(song);
            songOptions.hide();
        });
        songOptions.show(target, Side.TOP, -20, 5);
    }
}
