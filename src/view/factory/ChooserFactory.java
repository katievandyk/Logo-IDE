package view.factory;

import java.util.Set;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;

/**
 * Factory class that handles the creation of different types of choosers.
 * 
 * @author Katherine Van Dyk
 */
public class ChooserFactory {
    
    /**
     * Factory method for constructing file chooser
     * 
     * @param prompt
     * @return
     */
    public FileChooser fileChooser(String prompt) {
	FileChooser fc = new FileChooser();
	fc.setTitle(prompt);
	return fc;
    }

    /**
     * Factory method for constructing drop down boxes 
     * 
     * @param text
     * @param options
     * @return
     */
    public ComboBox<String> chooser(String text, Set<String> options) {
 	ComboBox<String> chooser = new ComboBox<String>();
 	chooser.getStyleClass().add("combo-box");
 	chooser.getItems().addAll(options);
 	chooser.setPromptText(text);
 	return chooser;
    }
    
    /**
     * Factory method for constructing color chooser
     * 
     * @param style
     * @return
     */
    public ColorPicker colorChooser(String style) {
	ColorPicker chooser = new ColorPicker();
	chooser.getStyleClass().add(style);
	return chooser;
    }
    
}
