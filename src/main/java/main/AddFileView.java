package main;

import components.Buttons;
import components.TextFields;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddFileView {
    static ArrayList<Button> buttonList = new ArrayList<>();
    static List<File> droppedFiles = new ArrayList<>();
    public static void display(BorderPane root) {
        VBox addFile = new VBox();
        addFile.getStyleClass().add("add-file");
        addFile.setPadding(new Insets(25,50,0,40));
        VBox addFileComponent = new VBox(30);

        Text textTitle = new Text("Add Files");
        textTitle.getStyleClass().add("title");

        Region dragAndDropRegion = new Region();
        dragAndDropRegion.getStyleClass().add("drag-and-drop-region");

        Text dragText = new Text("Drag Songs Here to Import Them");
        dragText.getStyleClass().add("normal");

        File iconFile = new File("icons/Property 1=Active.png");
        ImageView icon = new ImageView(new Image(iconFile.toURI().toString()));
        icon.setFitWidth(24);
        icon.setFitHeight(24);

        VBox dragTextContainer = new VBox(5);
        dragTextContainer.getStyleClass().add("drag-text-container");
        dragTextContainer.getChildren().addAll(dragText, icon);

        VBox droppedSong = new VBox(5);
        droppedSong.getStyleClass().add("dropped-song");

        StackPane dragAndDropContainer = new StackPane();
        dragAndDropContainer.getStyleClass().add("drag-and-drop-container");
        dragAndDropContainer.getChildren().addAll(dragAndDropRegion, droppedSong, dragTextContainer);

        Text textImport = new Text("Import Songs from Folder");
        textImport.getStyleClass().add("normal");

        HBox browseFile = new HBox(30);
        TextField filePath = TextFields.Small("");
        Button sbBrowse = Buttons.Browse("Browse");
        browseFile.getChildren().addAll(filePath, sbBrowse);

        Button sbImport = Buttons.Small("Import");

        droppedSong.setMouseTransparent(true);
        dragTextContainer.setMouseTransparent(true);
        dragAndDropContainer.setMouseTransparent(false);

        addFileComponent.getChildren().addAll(textTitle, dragAndDropContainer, textImport, browseFile, sbImport);
        buttonList.add(sbBrowse);
        buttonList.add(sbImport);

        VBox.setMargin(addFileComponent, new Insets(25, 25, 0, 25));
        File addFileStyles = new File ("styles/addfileStyles.css");
        addFileComponent.getStylesheets().add(addFileStyles.toURI().toString());

        addFile.getStylesheets().add(addFileStyles.toURI().toString());
        addFile.getChildren().add(addFileComponent);
        root.setCenter(addFile);

        dragAndDropRegion.setOnDragEntered(event -> {
            if (event.getGestureSource() != dragAndDropRegion && event.getDragboard().hasFiles()) {
                dragTextContainer.setVisible(false);
            }
            event.consume();
        });

        dragAndDropRegion.setOnDragOver(event -> {
            if (event.getGestureSource() != dragAndDropRegion && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        dragAndDropRegion.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                handleDroppedFiles(db.getFiles(), droppedSong);
            }
            event.setDropCompleted(success);
            event.consume();
        });

        sbBrowse.setOnAction(e -> handleSbBrowse(filePath));
        sbImport.setOnAction(e -> handleSbImport(root, filePath, droppedSong, dragTextContainer));
    }

    private static void handleSbBrowse(TextField filePath) {
        AddFileController.browseFile(filePath);
    }

    private static void handleSbImport(BorderPane root, TextField filePath, VBox droppedSong, VBox dragTextContainer) {

        if (!droppedFiles.isEmpty() && !filePath.getText().isEmpty()) {
            // Import file dari drag and drop
            for (File file : droppedFiles) {
                System.out.println("Handling dropped file: " + file.getAbsolutePath());
                AddFileController.importFile(file);
            }
            // Import Folder
            String selectedFilePath = filePath.getText();
            System.out.println("Handling selected folder: " + selectedFilePath);
            new AddFilePopup("Song Added", "Check all song for more information");
            AddFileController.importFolder(selectedFilePath);
        } else if (!droppedFiles.isEmpty()) {
            new AddFilePopup("Song Added", "Check all song for more information");
            for (File file : droppedFiles) {
                System.out.println("Handling dropped file: " + file.getAbsolutePath());
                AddFileController.importFile(file);
            }
        } else if(!filePath.getText().isEmpty()){
            String selectedFilePath = filePath.getText();
            System.out.println("Handling selected folder: " + selectedFilePath);
            AddFileController.importFolder(selectedFilePath);
        }else if (droppedFiles.isEmpty()){
            new AddFilePopupError("No Song Added", "please add song first to import");
        }

        filePath.setText("");
        droppedSong.getChildren().clear();
        dragTextContainer.setVisible(true);
    }

    private static void handleDroppedFiles(List<File> files, VBox droppedSong) {
        for (File file : files) {
            System.out.println("Dropped file: " + file.getAbsolutePath());

            HBox fileEntry = new HBox(5);

            File fileIcon = new File("icons/tabler-icon-file-music-inactive.png");
            ImageView iconView = new ImageView(new Image(fileIcon.toURI().toString()));
            iconView.setFitWidth(15);
            iconView.setFitHeight(15);

            Label fileNameLabel = new Label(file.getName());
            fileNameLabel.getStyleClass().add("hbox-label");
            fileEntry.getChildren().addAll(iconView, fileNameLabel);

            droppedSong.getChildren().add(fileEntry);

            droppedFiles.add(file);
        }
    }

}
