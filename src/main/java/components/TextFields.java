package components;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;

public class TextFields {

    public static TextField Small(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(String.valueOf(TextFields.class.getResource("textfieldStyles.css")));
        textField.setPrefColumnCount(50);

        File textFieldStyles = new File("styles/textfieldStyles.css");
        textField.getStylesheets().add(textFieldStyles.toURI().toString());
        textField.getStyleClass().add("text-field-small");
        return textField;
    }

    public static TextField Medium(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(TextFields.class.getResource("textfieldStyles.css").toExternalForm());
        textField.getStyleClass().add("text-field-medium");

        return textField;
    }

    public static TextField Large(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(TextFields.class.getResource("textfieldStyles.css").toExternalForm());
        textField.getStyleClass().add("text-field-large");

        return textField;
    }

}
