package components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class Dropdowns {

    public static ComboBox<String> Short(ArrayList<String> arrayOptions, String placeholder) {
        ObservableList<String> options = FXCollections.observableArrayList(arrayOptions);

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPromptText(placeholder);
        comboBox.getStyleClass().add("dropdown-short");
        comboBox.getStylesheets().add(Dropdowns.class.getResource("dropdownStyles.css").toExternalForm());

        return comboBox;
    }

    public static ComboBox<String> Medium(ArrayList<String> arrayOptions, String placeholder) {
        ObservableList<String> options = FXCollections.observableArrayList(arrayOptions);

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPromptText(placeholder);
        comboBox.getStylesheets().add(Dropdowns.class.getResource("dropdownStyles.css").toExternalForm());
        comboBox.getStyleClass().add("dropdown-medium");

        return comboBox;
    }
    public static ComboBox<String> Long(ArrayList<String> arrayOptions, String placeholder) {
        ObservableList<String> options = FXCollections.observableArrayList(arrayOptions);

        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setPromptText(placeholder);
        comboBox.getStylesheets().add(Dropdowns.class.getResource("dropdownStyles.css").toExternalForm());
        comboBox.getStyleClass().add("dropdown-long");

        return comboBox;
    }

}
