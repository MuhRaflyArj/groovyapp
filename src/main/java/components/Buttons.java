package components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.io.File;

public class Buttons {

    public static Button Small(String caption) {
        Button button = new Button(caption);
        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-small");
        return button;
    }

    public static Button Medium(String caption) {
        Button button = new Button(caption);
        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-medium");
        return button;
    }

    public static Button Large(String caption) {
        Button button = new Button(caption);
        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-large");
        return button;
    }

    public static Button ExtraLarge(String caption) {
        Button button = new Button(caption);
        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-extra-large");
        return button;
    }

    public static Button ButtonWithIcon(String iconName, int width, int height) {
        File iconFile = new File("icons/" + iconName);
        ImageView icon = new ImageView(new Image(iconFile.toURI().toString()));
        icon.setFitWidth(width);
        icon.setFitHeight(height);

        Button button = new Button("", icon);
        button.setPrefSize(width, height);
        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-with-icon");

        return button;
    }

    public static Button ButtonWithIconText(String iconName, int width, int height, String caption) {
        HBox buttonBox = new HBox(20);

        File iconFile = new File("icons/" + iconName);
        ImageView icon = new ImageView(new Image(iconFile.toURI().toString()));
        icon.setFitWidth(width);
        icon.setFitHeight(height);

        Text buttonCaption = new Text(caption);
        buttonCaption.setStyle("-fx-fill: #ffffff;");

        buttonBox.getChildren().addAll(icon, buttonCaption);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getStyleClass().add("button-box");

        Button button = new Button("", buttonBox);

        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-with-icon-text");

        return button;
    }

    public static Button ButtonWithIconText(String iconName, int width, int height, String caption, int buttonWidth) {
        HBox buttonBox = new HBox(20);

        File iconFile = new File("icons/" + iconName);
        ImageView icon = new ImageView(new Image(iconFile.toURI().toString()));
        icon.setFitWidth(width);
        icon.setFitHeight(height);

        Text buttonCaption = new Text(caption);
        buttonCaption.setStyle("-fx-fill: #ffffff;");

        buttonBox.getChildren().addAll(icon, buttonCaption);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getStyleClass().add("button-box");
        Button button = new Button("", buttonBox);

        File buttonStyles = new File("styles/buttonStyles.css");
        button.setMinWidth(buttonWidth);
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-with-icon-text");

        return button;
    }

    public static void ButtonUpdateIcon(Button button, String iconName, int width, int height) {
        File iconFile = new File("icons/" + iconName);
        ImageView icon = new ImageView(new Image(iconFile.toURI().toString()));
        icon.setFitWidth(width);
        icon.setFitHeight(height);
        button.setGraphic(icon);
    }
    public static Button Browse(String caption) {
        Button button = new Button(caption);
        File buttonStyles = new File("styles/buttonStyles.css");
        button.getStylesheets().add(buttonStyles.toURI().toString());
        button.getStyleClass().add("button-browse");
        return button;
    }

}


