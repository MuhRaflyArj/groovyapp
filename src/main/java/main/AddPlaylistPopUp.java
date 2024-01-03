package main;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class AddPlaylistPopUp extends PopUp {

    @Override
    public void handleButton1() {
        // Handle button 1 action for Add Playlist popup
    }

    @Override
    public void handleButton2() {
        // Handle button 2 action for Add Playlist popup
    }

    @Override
    public void setButton1() {

    }

    @Override
    public void setButton2() {

    }

    @Override
    public void setTextBox(String title, String subtitle) {
        // Set properties for text box in Add Playlist popup
        this.textBox.getChildren().addAll(new Text(title), new Text(subtitle));
    }
}
