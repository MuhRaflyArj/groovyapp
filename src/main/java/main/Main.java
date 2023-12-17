package main;

import dao.PlaylistDAO;
import dao.SongDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import components.*;

import java.io.File;


public class Main extends Application{
    Stage window;

    public static void main(String[] args) {
        new PlaylistDAO();
        new SongDAO();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();

        SideBarController.display(root, PlaylistDAO.getAllPlaylist());
        MusicPlayerController.display(root, SongDAO.recentlyPlayed(1).get(0));

        BorderPane centerPane = new BorderPane();
        centerPane.getStyleClass().add("center-pane");

        Scene scene = new Scene(root, 1500, 720);

        File mainStyles = new File("styles/mainStyles.css");
        scene.getStylesheets().add(mainStyles.toURI().toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}