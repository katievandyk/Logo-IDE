package view.panels;


import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.factory.ButtonFactory;
import view.factory.ChooserFactory;
import view.factory.TextFactory;


public abstract class Panel {
    
    protected ButtonFactory BUTTON;
    protected ChooserFactory CHOOSER;
    protected TextFactory TEXT; 
    protected final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");
    protected final String LANGUAGES = "./src/resources/languages";
    protected final String IMAGES = "./src/resources/turtles";

    /**
     * Superclass constructor for panel, sets factory classes
     */
    public Panel() {
	BUTTON = new ButtonFactory();
	CHOOSER = new ChooserFactory();
	TEXT = new TextFactory();
    }
    
    /**
     * Abstract class for panel that returns pane to be displayed on main screen
     */
    public abstract Pane construct();
   
   /**
    * Method to make image from string input used by panels
    */
   protected ImageView makeImage(String img) {
	Image im = new Image(getClass().getClassLoader().getResourceAsStream((img)));
	return new ImageView(im);
   }



}
