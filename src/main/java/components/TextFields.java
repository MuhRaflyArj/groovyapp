package components;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TextFields {

    public static TextField Small(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(TextFields.class.getResource("textFieldStyles.css").toExternalForm());
        textField.getStyleClass().add("text-field-small");
        return textField;
    }

    public static TextField Medium(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(TextFields.class.getResource("textFieldStyles.css").toExternalForm());
        textField.getStyleClass().add("text-field-medium");

        return textField;
    }

    public static TextField Large(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.getStylesheets().add(TextFields.class.getResource("textFieldStyles.css").toExternalForm());
        textField.getStyleClass().add("text-field-large");

        return textField;
    }

}
