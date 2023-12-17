package main;

import components.Buttons;
import components.TextFields;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddFileView {
    static ArrayList<Button> buttonList = new ArrayList<>();
    public static void display(BorderPane root) {
        VBox addFile = new VBox();
        addFile.getStyleClass().add("add-file");
        VBox addFileComponent = new VBox(30);

        Text textTitle = new Text("Add Files");
        textTitle.getStyleClass().add("title");

        StackPane dragAndDropContainer = new StackPane();
        dragAndDropContainer.getStyleClass().add("drag-and-drop-container");

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

        dragAndDropContainer.getChildren().addAll(dragAndDropRegion, dragTextContainer);

        Text textImport = new Text("Import from Path");
        textImport.getStyleClass().add("normal");

        HBox browseFile = new HBox(30);
        TextField filePath = TextFields.Small("");
        Button sbBrowse = Buttons.Browse("Browse");
        browseFile.getChildren().addAll(filePath, sbBrowse);

        Button sbImport = Buttons.Small("Import");

        addFileComponent.getChildren().addAll(textTitle, dragAndDropContainer, textImport, browseFile, sbImport);
        buttonList.add(sbBrowse);
        buttonList.add(sbImport);

        VBox.setMargin(addFileComponent, new Insets(25, 25, 0, 25));
        File addFileStyles = new File ("styles/addfileStyles.css");
        addFileComponent.getStylesheets().add(addFileStyles.toURI().toString());

        addFile.getStylesheets().add(addFileStyles.toURI().toString());
        addFile.getChildren().add(addFileComponent);
        root.setCenter(addFile);

        dragAndDropRegion.setOnDragOver(event -> {
            if (event.getGestureSource() != dragAndDropRegion && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        dragAndDropRegion.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasFiles()) {
                success = true;
                List<File> files = dragboard.getFiles();
                if (!files.isEmpty()) {
                    File selectedFile = files.get(0);
                    filePath.setText(selectedFile.getAbsolutePath());
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        sbBrowse.setOnAction(e -> handleSbBrowse(root, sbBrowse, filePath));
        sbImport.setOnAction(e -> handleSbImport(root, sbImport));
    }

    private static void handleSbBrowse(BorderPane root, Button sbBrowse,TextField filePath) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");

        // Set initial directory (optional)
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show open dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Set the selected file path to the TextField
            filePath.setText(selectedFile.getAbsolutePath());
        }
    }

    private static void handleSbImport(BorderPane root, Button sbImport) {

    }


}
