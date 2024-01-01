package main;

import components.Buttons;
import components.TextFields;
import dao.SongDAO;
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
import object.Song;

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
        sbImport.setOnAction(e -> handleSbImport(filePath, droppedSong, dragTextContainer));
    }

    private static void handleSbBrowse(TextField filePath) {
        AddFileController.browseFile(filePath);
    }

    private static void handleSbImport(TextField filePath, VBox droppedSong, VBox dragTextContainer) {
        String importStatus = "";
        if (!droppedFiles.isEmpty() && !filePath.getText().isEmpty()) {
            AddFileController.importDragAndDrop(droppedFiles);

            String selectedFilePath = filePath.getText();
            importStatus = AddFileController.importFolder(selectedFilePath);

        } else if (!droppedFiles.isEmpty()) {
            importStatus = AddFileController.importDragAndDrop(droppedFiles);

        } else if(!filePath.getText().isEmpty()){
            String selectedFilePath = filePath.getText();
            importStatus = AddFileController.importFolder(selectedFilePath);

        } else if (droppedFiles.isEmpty() && filePath.getText().isEmpty()){
            importStatus = "no_file_or_folder_added";
        }

        switch (importStatus){
            case "no_file_or_folder_added":
                new AddFilePopupError("No File or Folder Added", "Please add File or Folder first to import");
                break;
            case "no_song_found_in_folder":
                new AddFilePopupError("No Song Found In Folder", "Please check content of the folder");
                break;
            case "format_not_supported":
                new AddFilePopupError("Format not Supported", "Please check the dragged file");
                break;
            case "import_success":
                new AddFilePopup("Song Added", "Check all song for more information");
                break;
            case "import_failure":
                new AddFilePopupError("Import Failed", "Import process Failed");
                break;
            default:
                System.out.println("tes");
        }

        System.out.printf(importStatus);
        filePath.setText("");
        droppedFiles.clear();
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
