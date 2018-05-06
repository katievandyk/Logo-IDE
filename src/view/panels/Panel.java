package view.panels;


import java.io.File;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.factory.ButtonFactory;
import view.factory.ChooserFactory;
import view.factory.TextFactory;

/**
 * Panel superclass that contains instances of methods that multiple panels used and the construct
 * method used by all panels to get their display panes.
 * 
 * @author Katherine Van Dyk
 *
 */
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
    
    /**
     * Gets all files in directory and takes away file type for display
     * @param directory
     * @return
     */
    protected Set<String> getFiles(String directory) {
	File[] files = new File(directory).listFiles();
	Set<String> ret = new HashSet<String>();
	for(File file : files){
	    ret.add(file.getName().substring(0, file.getName().indexOf(".")));
	}
	return ret;
    }



}
