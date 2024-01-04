package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class DeletePlaylistConfirmationPopup {
    public static boolean show(String playlistTitle) {
        AtomicBoolean status = new AtomicBoolean(false);
        Stage stage = new Stage();
        stage.setTitle("");

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #2E2E2E;");

        GridPane contentGrid = new GridPane();
        contentGrid.setHgap(10);
        contentGrid.setVgap(10);
        contentGrid.setPadding(new Insets(50, 100, 50, 100));

        Text contentText = new Text("Are you sure?\nWant to delete '" + playlistTitle + "'?");
        contentText.setStyle("-fx-font-family: 'Lexend'; -fx-font-size: 24px; -fx-fill: #FFFFFF;");
        contentText.setTextAlignment(TextAlignment.CENTER);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button cancelButton = new Button("Back");
        cancelButton.setOnAction(e -> stage.close());
        cancelButton.setStyle("-fx-background-color: #1B1A1A; -fx-text-fill: #FFFFFF; -fx-font-family: 'Lexend';");
        cancelButton.setPrefWidth(100);
        cancelButton.setPrefHeight(40);

        Button okButton = new Button("Delete");
        okButton.setOnAction(e -> {
            System.out.println("Deleted: " + playlistTitle);
            stage.close();
            status.set(true);
        });

        okButton.setStyle("-fx-background-color: #FF2222; -fx-text-fill: #FFFFFF; -fx-font-family: 'Lexend';");
        okButton.setPrefWidth(100);
        okButton.setPrefHeight(40);
        buttonBox.getChildren().addAll(cancelButton, okButton);

        contentGrid.setStyle("-fx-background-color: transparent;");

        contentGrid.add(buttonBox, 0, 1);
        contentGrid.add(contentText, 0, 0);

        root.getChildren().add(contentGrid);

        Scene scene = new Scene(root);
        scene.setFill(null);

        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);

        stage.showAndWait();

        return status.get();
    }
}
