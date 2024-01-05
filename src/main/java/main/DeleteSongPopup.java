package main;

import components.Buttons;
import components.Images;
import dao.SongDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import object.Playlist;
import object.Song;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DeleteSongPopup {
    public static void display(Playlist playlist, BorderPane root) {
        Popup popup = new Popup();
        VBox popupComponent = new VBox(15);
        popup.setX(500);
        popup.setY(250);

        File sidebarStyles = new File ("styles/popupStyles.css");
        popupComponent.getStylesheets().add(sidebarStyles.toURI().toString());
        popupComponent.getStyleClass().add("pop-up-song");

        VBox textBox = new VBox(5);
        HBox buttonBox = new HBox(25);
        textBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        HBox close = new HBox(550);
        close.setMinWidth(700);
        Text title = new Text("Delete Song");
        title.getStyleClass().add("add-song");
        StackPane titleBox = new StackPane();
        titleBox.getChildren().add(title);
        titleBox.minWidth(650);

        VBox songBox = new VBox(20);
        songBox.setMinHeight(325);
        songBox.getStyleClass().add("song-box");
        songBox.setPadding(new Insets(25,50,50,50));

        ScrollPane songScroll = new ScrollPane(songBox);
        songScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        songScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        songScroll.getStyleClass().add("scroll-element");
        songScroll.setMaxWidth(700);
        songScroll.setMaxHeight(325);
        songScroll.setMinWidth(700);
        songScroll.setMinHeight(325);

        Map<CheckBox, String> checkBoxMap = new HashMap<>();
        for(String songID : playlist.getSongList()) {
            Song song = SongDAO.getSongById(songID);
            HBox songRow = new HBox(20);
            songRow.setMinWidth(700);

            StackPane songImage;
            if (song.getImagePath().equals("")) {
                songImage = Images.ExtraSmall("images/empty-song-small.png");
            } else {
                songImage = Images.ExtraSmall(song.getImagePath());
            }

            VBox songDesc = new VBox();
            songDesc.setMinWidth(500);
            Text songTitle = new Text(song.getTitle());
            songTitle.getStyleClass().add("song-title");
            Text songArtist = new Text(song.getArtist());
            songArtist.getStyleClass().add("song-artist");
            songDesc.getChildren().addAll(songTitle, songArtist);

            CheckBox checkBox = new CheckBox();
            checkBoxMap.put(checkBox, song.getSongID());

            songRow.getChildren().addAll(songImage, songDesc, checkBox);
            songBox.getChildren().add(songRow);


        }

        Button buttonClose = Buttons.ButtonWithIcon("tabler-icon-x-inactive.png", 16, 16);
        buttonClose.setOnAction(e -> popup.hide());
        close.getChildren().addAll(titleBox, buttonClose);
        close.setAlignment(Pos.TOP_LEFT);
        VBox.setMargin(close, new Insets(0, 50, 0, 50));

        Button buttonAdd = Buttons.Small("Remove");
        buttonAdd.setOnAction(e-> {
            PlaylistController.deletePlaylistSong(checkBoxMap, playlist, root);
            popup.hide();
        });


        // Set the alignment to center
        popupComponent.setAlignment(Pos.CENTER);

        // Set the layout parameters to center the popupComponent
        popupComponent.setLayoutX((popup.getWidth() - popupComponent.getBoundsInLocal().getWidth()) / 2);
        popupComponent.setLayoutY((popup.getHeight() - popupComponent.getBoundsInLocal().getHeight()) / 2);


        popupComponent.getChildren().addAll(close, songScroll, buttonAdd);

        popup.getContent().addAll(popupComponent);
        popup.show(Main.primaryStage);
    }
}


