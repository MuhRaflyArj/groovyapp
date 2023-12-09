package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.File;

public class Images {

    public static StackPane ExtraLarge(String imageName) {
        File image = new File("images/"+imageName);
        Image imageSource = new Image(image.toURI().toString());

        ImageView imageView = new ImageView(imageSource);
        imageView.getStyleClass().add("extra-large");

        StackPane imageBox = new StackPane();
        imageBox.getChildren().add(imageView);

        File imageStyles = new File("styles/imageStyles.css");
        imageBox.getStylesheets().add(imageStyles.toURI().toString());

        return imageBox;
    }

    public static StackPane Large(String imageName) {
        File image = new File("images/"+imageName);
        Image imageSource = new Image(image.toURI().toString());

        ImageView imageView = new ImageView(imageSource);
        imageView.getStyleClass().add("large");

        StackPane imageBox = new StackPane();
        imageBox.getChildren().add(imageView);

        File imageStyles = new File("styles/imageStyles.css");
        imageBox.getStylesheets().add(imageStyles.toURI().toString());

        return imageBox;
    }

    public static StackPane Medium(String imageName) {
        File image = new File("images/"+imageName);
        Image imageSource = new Image(image.toURI().toString());

        ImageView imageView = new ImageView(imageSource);
        imageView.getStyleClass().add("medium");

        StackPane imageBox = new StackPane();
        imageBox.getChildren().add(imageView);

        File imageStyles = new File("styles/imageStyles.css");
        imageBox.getStylesheets().add(imageStyles.toURI().toString());

        return imageBox;
    }

    public static StackPane Small(String imageName) {
        File image = new File("images/"+imageName);
        Image imageSource = new Image(image.toURI().toString());

        ImageView imageView = new ImageView(imageSource);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        StackPane imageBox = new StackPane();
        imageBox.getChildren().add(imageView);

        File imageStyles = new File("styles/imageStyles.css");
        imageBox.getStylesheets().add(imageStyles.toURI().toString());
        imageBox.getStyleClass().add("small");

        return imageBox;
    }

    public static StackPane ExtraSmall(String imageName) {
        File image = new File("images/"+imageName);
        Image imageSource = new Image(image.toURI().toString());

        ImageView imageView = new ImageView(imageSource);
        imageView.getStyleClass().add("extra-small");

        StackPane imageBox = new StackPane();
        imageBox.getChildren().add(imageView);

        File imageStyles = new File("styles/imageStyles.css");
        imageBox.getStylesheets().add(imageStyles.toURI().toString());

        return imageBox;
    }

}