package main;

import components.Buttons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import components.Buttons;

import java.io.File;

public abstract class PopUp {
    Popup popup;
    Button button1;
    Button button2;
    Button button3;
    Button buttonClose;
    VBox popupComponent;
    HBox buttonBox;
    VBox textBox;
    public Popup display() {
        popup = new Popup();
        this.popupComponent = new VBox(50);
        popup.setX(500);
        popup.setY(250);

        File sidebarStyles = new File ("styles/popupStyles.css");
        popupComponent.getStylesheets().add(sidebarStyles.toURI().toString());
        popupComponent.getStyleClass().add("pop-up");

        this.textBox = new VBox(5);
        this.buttonBox = new HBox(25);
        this.textBox.setAlignment(Pos.CENTER);
        this.buttonBox.setAlignment(Pos.CENTER);

        HBox close = new HBox();
        this.buttonClose = Buttons.ButtonWithIcon("tabler-icon-x-inactive.png", 16, 16);
        buttonClose.setOnAction(e -> popup.hide());
        close.getChildren().add(buttonClose);
        close.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(close, new Insets(0, 50, 0, 0));


        // Set the alignment to center
        popupComponent.setAlignment(Pos.CENTER);

        // Set the layout parameters to center the popupComponent
        popupComponent.setLayoutX((popup.getWidth() - popupComponent.getBoundsInLocal().getWidth()) / 2);
        popupComponent.setLayoutY((popup.getHeight() - popupComponent.getBoundsInLocal().getHeight()) / 2);


        popupComponent.getChildren().addAll(close, textBox, buttonBox);
        popup.getContent().add(popupComponent);
        popup.show(Main.primaryStage);

        return popup;
    }

    public abstract void handleButton1();

    public abstract void handleButton2();
    public abstract void setButton1();

    public abstract void setButton2();

    public abstract void setTextBox(String title, String subtitle);
}
