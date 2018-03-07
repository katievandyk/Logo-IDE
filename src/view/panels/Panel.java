package view.panels;


import java.util.Set;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public abstract class Panel {
    
    public abstract Pane construct();
    
    protected TextField textFieldFactory(String text, String id) {
	TextField t = new TextField();
	t.setPromptText(text);
	t.setId(id);
	return t;
    }
    
    protected Text textFactory(String text, String id) {
	Text t = new Text();
	t.setText(text);
	t.setId(id);
	return t;
    }
    
    protected Text labelFactory(String text) {
	Text label = new Text(text);
	label.setId("label");
	return label;
    }
    
    protected Button styledButtonFactory(String stringImage, String ID) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	button.setId(ID);
	return button;
    }
    
    protected Button textButtonFactory(String text, String ID) {
	Button button = new Button();
	button.setText(text);
	button.setId(ID);
	return button;
    }
    
   protected Button buttonFactory(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
   
   /**
    * Factory method for constructing drop down boxes 
    * 
    * @param text
    * @param options
    * @return
    */
   protected ComboBox<String> chooserFactory(String text, Set<String> options) {
	ComboBox<String> chooser = new ComboBox<String>();
	chooser.getStyleClass().add("combo-box");
	chooser.getItems().addAll(options);
	chooser.setPromptText(text);
	return chooser;
   }


}
