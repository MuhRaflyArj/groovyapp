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
    private static BorderPane root;
    private static Button sbHome = Buttons.ButtonWithIconText("tabler-icon-home-inactive.png", 32, 32, "HOME", 216);
    private static Button sbAddFile = Buttons.ButtonWithIconText("tabler-icon-file-music-inactive.png", 32, 32, "ADD FILE", 216);
    private static Button sbAddPlaylist = Buttons.ButtonWithIconText("tabler-icon-playlist-add-inactive.png", 32, 32, "CREATE PLAYLIST", 216);
    private static Button sbAllSong = Buttons.ButtonWithIconText("tabler-icon-music-inactive.png", 32, 32, "ALL SONG", 216);
    private static ArrayList<Button> buttonList = new ArrayList<>();
    private static ArrayList<Button> currentPlaylist = new ArrayList<>();
    private static VBox sideBarComponent; // this is update able
    public static void display(BorderPane root, List<Playlist> playlists) {
        SideBarView.root = root;
        VBox sideBar = new VBox();
        sideBar.getStyleClass().add("side-bar");
        sideBarComponent = new VBox(5);

        Text textMenu = new Text("MENU");
        textMenu.getStyleClass().add("title");
        Text space = new Text("");
        Text textLibrary = new Text("LIBRARY");
        textLibrary.getStyleClass().add("title");

        sideBarComponent.getChildren().addAll(textMenu, sbHome, sbAddFile, sbAddPlaylist, space, textLibrary, sbAllSong);
        buttonList.add(sbHome);
        buttonList.add(sbAddFile);
        buttonList.add(sbAddPlaylist);
        buttonList.add(sbAllSong);

        for(Playlist playlist : playlists) {
            Button sbPlaylist = Buttons.ButtonWithIconText("tabler-icon-playlist-inactive.png", 32, 32, playlist.getName(), 216);
            sbPlaylist.setOnAction(e -> handleSbPlaylist(sbPlaylist, playlist));
            sideBarComponent.getChildren().add(sbPlaylist);
            buttonList.add(sbPlaylist);
            currentPlaylist.add(sbPlaylist);
        }

        VBox.setMargin(sideBarComponent, new Insets(25, 25, 0, 25));
        File sidebarStyles = new File ("styles/sidebarStyles.css");
        sideBarComponent.getStylesheets().add(sidebarStyles.toURI().toString());
        sideBarComponent.getStyleClass().add("sidebar-component");
        sideBar.prefWidth(288);
        sideBar.maxWidth(288);
        sideBar.minWidth(288);
        sideBar.getChildren().add(sideBarComponent);

        sbHome.setOnAction(e -> handleSbHome());
        sbAddFile.setOnAction(e -> handleSbAddFile());
        sbAddPlaylist.setOnAction(e -> handleSbAddPlaylist());
        sbAllSong.setOnAction(e -> handleSbAllSong());

        SideBarView.root.setLeft(sideBar);
    }

    public static void updateDisplay(List<Playlist> playlists) {
        currentPlaylist.forEach((bt) -> {
            buttonList.remove(bt);
            sideBarComponent.getChildren().remove(bt);
        });
        for(Playlist playlist : playlists) {
            Button sbPlaylist = Buttons.ButtonWithIconText("tabler-icon-playlist-inactive.png", 32, 32, playlist.getName(), 216);
            sbPlaylist.setOnAction(e -> handleSbPlaylist(sbPlaylist, playlist));
            sideBarComponent.getChildren().add(sbPlaylist);
            buttonList.add(sbPlaylist);
            currentPlaylist.add(sbPlaylist);
        }
    }

    private static void handleSbHome() {
        setButtonState(sbHome);
        SideBarController.displayHome();
    }

    private static void handleSbAddFile() {
        setButtonState(sbAddFile);
        SideBarController.displayAddFile();
    }

    private static void handleSbAddPlaylist() {
        setButtonState(sbAddPlaylist);
        SideBarController.displayAddPlaylist();
    }

    private static void handleSbAllSong() {
        setButtonState(sbAllSong);
        SideBarController.displayAllSong();
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
