package main;

import components.Buttons;
import components.TextFields;
import dao.SongDAO;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.mpatric.mp3agic.*;
import object.Song;

public class AddFileView {
    static ArrayList<Button> buttonList = new ArrayList<>();
    static List<File> droppedFiles = new ArrayList<>();
    public static void display(BorderPane root) {
        VBox addFile = new VBox();
        addFile.getStyleClass().add("add-file");
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

//        dragAndDropRegion.setOnDragExited(event -> {
//            dragTextContainer.setVisible(true);
//            event.consume();
//        });

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
//        for (int i = 7; i <= 10; i++) {
//            SongDAO.deleteSong(String.format("S%03d", i));
//        }
        if (droppedFiles.isEmpty() && filePath.getText().isEmpty()) {
            return;
        }

        if (!droppedFiles.isEmpty()) {
            for (File file : droppedFiles) {
                System.out.println("Handling dropped file: " + file.getAbsolutePath());
                AddFileController.importFile(file);
            }
        } else if(!filePath.getText().isEmpty()){
            String selectedFilePath = filePath.getText();
            System.out.println("Handling selected folder: " + selectedFilePath);
            AddFileController.importFolder(selectedFilePath);
        }

        List<Song> songs = SongDAO.getAllSong();
        System.out.println("File yang ada di Database: \n");
        for (Song song : songs){
            System.out.println(song.getSongID());
        }

        filePath.setText("");
        droppedSong.getChildren().clear();
        dragTextContainer.setVisible(true);
    }

    private static void handleDroppedFiles(List<File> files, VBox droppedSong) {
        // Process the dropped files here
        for (File file : files) {
            // Perform actions with the dropped file(s)
            System.out.println("Dropped file: " + file.getAbsolutePath());
            Label fileNameLabel = new Label(file.getName());
            droppedSong.getChildren().add(fileNameLabel);
            droppedFiles.add(file);
        }
    }

}
