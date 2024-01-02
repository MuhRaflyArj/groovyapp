package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

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
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);
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
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        double borderRadius = 20.0; // Adjust the radius as needed
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(),
                imageView.getFitHeight()
        );
        clip.setArcWidth(borderRadius);
        clip.setArcHeight(borderRadius);

        imageView.setClip(clip);

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

        double borderRadius = 20.0; // Adjust the radius as needed
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(),
                imageView.getFitHeight()
        );
        clip.setArcWidth(borderRadius);
        clip.setArcHeight(borderRadius);

        imageView.setClip(clip);

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
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);

        double borderRadius = 30.0; // Adjust the radius as needed
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(),
                imageView.getFitHeight()
        );
        clip.setArcWidth(borderRadius);
        clip.setArcHeight(borderRadius);

        imageView.setClip(clip);

        StackPane imageBox = new StackPane();
        imageBox.getChildren().add(imageView);

        File imageStyles = new File("styles/imageStyles.css");
        imageBox.getStylesheets().add(imageStyles.toURI().toString());

        return imageBox;
    }

}
