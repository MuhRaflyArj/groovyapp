package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import object.Playlist;
import javafx.scene.Scene;

import java.io.File;

public class AddPlaylistView {

    private static VBox addPlaylist;

    public static void display(BorderPane root) {
        showCreatePlaylistPopup(root);
    }

    private static void showCreatePlaylistPopup(BorderPane root) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        // Create the main content
        BorderPane popupContent = new BorderPane();
        popupContent.getStyleClass().add("popup-root");

        // Left side for adding photo (in an HBox)
        StackPane photoPane = createPhotoPane();

        // Right side for text fields and button (in a VBox)
        VBox rightPane = createTextFieldsAndButtonPane(popupStage);

        // Set the content in the BorderPane
        popupContent.setLeft(photoPane);
        BorderPane.setMargin(rightPane, new Insets(0, 0, 0, 20));
        popupContent.setCenter(rightPane);

        // Set up the scene
        Scene scene = new Scene(popupContent, 600, 300);

        // Import the CSS file for the popup
        File popupStyles = new File("styles/addPlaylistPopupStyles.css");
        scene.getStylesheets().add(popupStyles.toURI().toString());

        // Set the scene to the popup stage
        popupStage.setScene(scene);

        // Set the owner of the popup stage (the main stage)
        popupStage.initOwner(root.getScene().getWindow());

        // Show the popup and wait for it to be closed
        popupStage.showAndWait();
    }

    private static StackPane createPhotoPane() {
        StackPane photoPane = new StackPane(); // Use StackPane to center the button
        photoPane.getStyleClass().add("image-container");

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(250);
        imageView.setFitHeight(255);

        // Use the specified icon for the addPhotoButton
        File iconFile = new File("icons/Property 1=Active.png");
        Image iconImage = new Image(iconFile.toURI().toString());
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(24);
        iconImageView.setFitHeight(24);

        Button addPhotoButton = new Button();
        addPhotoButton.getStyleClass().add("add-photo-button");
        addPhotoButton.setGraphic(iconImageView); // Set the icon as a graphic

        addPhotoButton.setOnAction(e -> handleAddPhoto(imageView));

        // Center the button in the StackPane
        photoPane.getChildren().addAll(imageView, addPhotoButton);

        return photoPane;
    }

    private static VBox createTextFieldsAndButtonPane(Stage popupStage) {
        VBox textFieldsAndButtonPane = new VBox(10);
        textFieldsAndButtonPane.getStyleClass().add("text-fields-container");

        TextField playlistNameField = new TextField();
        playlistNameField.getStyleClass().add("text-field");
        playlistNameField.setPromptText("Enter Playlist Name");

        TextField playlistDescriptionField = new TextField();
        playlistDescriptionField.getStyleClass().add("text-field-description");
        playlistDescriptionField.setPromptText("Enter Playlist Description");

        Button confirmButton = new Button("Confirm");
        confirmButton.getStyleClass().add("button");
        confirmButton.setOnAction(e -> {
            handleCreatePlaylist(playlistNameField.getText(), playlistDescriptionField.getText());
            popupStage.close();
        });

        textFieldsAndButtonPane.getChildren().addAll(playlistNameField, playlistDescriptionField, confirmButton);

        return textFieldsAndButtonPane;
    }

    private static void handleAddPhoto(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }

    private static void handleCreatePlaylist(String name, String description) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDesc(description);
        System.out.println("Playlist created: " + playlist.getName() + " - " + playlist.getDesc());
    }
}