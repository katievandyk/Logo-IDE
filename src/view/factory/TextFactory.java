package view.factory;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TextFactory {
    
    public TextField textField(String text, String id) {
	TextField t = new TextField();
	t.setPromptText(text);
	t.setId(id);
	return t;
    }
    
    public Text styledText(String text, String id) {
	Text t = new Text();
	t.setText(text);
	t.setId(id);
	return t;
    }
    
    public Text text(String text) {
	Text label = new Text(text);
	label.setId("label");
	return label;
    }
    
    public TextArea textArea(String style) {
	TextArea t = new TextArea();
	t.getStyleClass().add(style);
	t.setEditable(false);
	return t;
    }

}
