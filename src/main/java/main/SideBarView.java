package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import components.Buttons;
import javafx.scene.text.Text;
import object.Playlist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SideBarView {
    static ArrayList<Button> buttonList = new ArrayList<>();
    public static void display(BorderPane root, List<Playlist> playlists) {
        VBox sideBar = new VBox();
        sideBar.getStyleClass().add("side-bar");
        VBox sideBarComponent = new VBox(5);

        Text textMenu = new Text("MENU");
        textMenu.getStyleClass().add("title");
        Button sbHome = Buttons.ButtonWithIconText("tabler-icon-home-inactive.png", 32, 32, "HOME", 216);
        Button sbAddFile = Buttons.ButtonWithIconText("tabler-icon-file-music-inactive.png", 32, 32, "ADD FILE", 216);
        Button sbCreatePlaylist = Buttons.ButtonWithIconText("tabler-icon-playlist-add-inactive.png", 32, 32, "CREATE PLAYLIST", 216);

        Text space = new Text("");
        Text textLibrary = new Text("LIBRARY");
        textLibrary.getStyleClass().add("title");
        Button sbAllSong = Buttons.ButtonWithIconText("tabler-icon-music-inactive.png", 32, 32, "ALL SONG", 216);

        sideBarComponent.getChildren().addAll(textMenu, sbHome, sbAddFile, sbCreatePlaylist, space, textLibrary, sbAllSong);
        buttonList.add(sbHome);
        buttonList.add(sbAddFile);
        buttonList.add(sbCreatePlaylist);
        buttonList.add(sbAllSong);

        for(Playlist playlist : playlists) {
            Button sbPlaylist = Buttons.ButtonWithIconText("tabler-icon-playlist-inactive.png", 32, 32, playlist.getName(), 216);
            sbPlaylist.setOnAction(e -> handleSbPlaylist(sbPlaylist, playlist));
            sideBarComponent.getChildren().add(sbPlaylist);
            buttonList.add(sbPlaylist);
        }

        VBox.setMargin(sideBarComponent, new Insets(25, 25, 0, 25));
        File sidebarStyles = new File ("styles/sidebarStyles.css");
        sideBarComponent.getStylesheets().add(sidebarStyles.toURI().toString());
        sideBarComponent.getStyleClass().add("sidebar-component");
        sideBar.prefWidth(288);
        sideBar.maxWidth(288);
        sideBar.minWidth(288);
        sideBar.getChildren().add(sideBarComponent);

        sbHome.setOnAction(e -> handleSbHome(sbHome));
        sbAddFile.setOnAction(e -> handleSbAddFile(root, sbAddFile));
        sbCreatePlaylist.setOnAction(e -> handleSbCreatePlaylist(sbCreatePlaylist));
        sbAllSong.setOnAction(e -> handleSbAllSong(root, sbAllSong));

        root.setLeft(sideBar);
    }

    private static void handleSbHome(Button sbHome) {
        setButtonState(sbHome);
        SideBarController.displayHome();
    }

    private static void handleSbAddFile(BorderPane root, Button sbAddfile) {
        setButtonState(sbAddfile);
        SideBarController.displayAddFile(root);
    }

    private static void handleSbCreatePlaylist(Button sbCreatePlaylist) {
        setButtonState(sbCreatePlaylist);
        SideBarController.displayAddPlaylist();
    }

    private static void handleSbAllSong(BorderPane root, Button sbAllSong) {
        setButtonState(sbAllSong);
        SideBarController.displayAllSong(root);
    }

    private static void handleSbPlaylist(Button sbPlaylist, Playlist playlist) {
        setButtonState(sbPlaylist);
        SideBarController.displayPlaylist(playlist);
    }

    private static void setButtonState(Button pressedButton) {
        for (Button button : buttonList) {
            if (button == pressedButton) {
                pressedButton.getStyleClass().add("button-with-icon-text-clicked");
            } else {
                button.getStyleClass().remove("button-with-icon-text-clicked");
                button.getStyleClass().add("button-with-icon-text");
            }

        }
    }
}
