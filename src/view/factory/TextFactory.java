package view.factory;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Factory class that handles the creation of different types of text,
 * styled and not style, editable and not-editable
 * 
 * @author Katherine Van Dyk
 */
public class TextFactory {
    
    /**
     * Creates editable textfield with prompt text @param text, CSS
     * tag @param id 
     * 
     * @return TextField object
     */
    public TextField textField(String text, String id) {
	TextField t = new TextField();
	t.setPromptText(text);
	t.setId(id);
	return t;
    }
    
    /**
     * Creates non-editable text object with text @param text
     * and CSS tag @param id
     * 
     * @return Text object
     */
    public Text styledText(String text, String id) {
	Text t = new Text();
	t.setText(text);
	t.setId(id);
	return t;
    }
    
    /**
     * Creates unstyled, uneditable text object with content
     * @param text
     *
     * @return Text object
     */
    public Text text(String text) {
	Text label = new Text(text);
	label.setId("label");
	return label;
    }
    
    /**
     * Creates styled TextArea with CSS tag @param style
     * 
     * @return TextArea object
     */
    public TextArea textArea(String style) {
	TextArea t = new TextArea();
	t.getStyleClass().add(style);
	t.setEditable(false);
	return t;
    }

}
