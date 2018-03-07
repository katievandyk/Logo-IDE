package view.panels;


import java.util.Set;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import view.factory.ButtonFactory;
import view.factory.ChooserFactory;
import view.factory.TextFactory;


public abstract class Panel {
    
    protected ButtonFactory BUTTON;
    protected ChooserFactory CHOOSER;
    protected TextFactory TEXT; 
    
    public Panel() {
	BUTTON = new ButtonFactory();
	CHOOSER = new ChooserFactory();
	TEXT = new TextFactory();
    }
    
    public abstract Pane construct();
   
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
