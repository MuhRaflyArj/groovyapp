package main;

import components.Buttons;
import javafx.scene.text.Text;

public class AddFilePopupError extends PopUp {

    public AddFilePopupError(String title, String subtitle) {
        this.display();
        setTextBox(title, subtitle);
        setButton1();
    }

    public void setTextBox(String title, String subtitle) {
        Text textTitle = new Text(title);
        Text textSubTitle = new Text(subtitle);

        textTitle.getStyleClass().add("title-error");
        textSubTitle.getStyleClass().add("subtitle");

        this.textBox.getChildren().addAll(textTitle, textSubTitle);
    }

    public void handleButton1() {
        this.popup.hide();
    }

    @Override
    public void handleButton2() {

    }

    public void setButton1() {
        this.button1 = Buttons.Small("Continue");
        button1.setOnAction(e -> handleButton1());
        this.buttonBox.getChildren().add(button1);
    }

    @Override
    public void setButton2() {

    }
}
