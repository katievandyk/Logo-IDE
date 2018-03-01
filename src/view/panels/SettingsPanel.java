package view.panels;

import java.io.File;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import controller.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import view.turtle.Turtle;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class that generates the stylistic settings panel at the top of the turtle screen.
 * 
 */
public class SettingsPanel {
    private Controller CONTROLLER;
    private TurtlePanel TURTLE_PANEL;
    private Turtle TURTLE;
    private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");
    private final String LANGUAGES = "./src/resources/languages";
    private final String IMAGES = "./src/resources/images";
  
    private ComboBox<String> BackgroundChooser;
    private ComboBox<String> PenChooser;
    private ComboBox<String> ImageChooser;
    private ComboBox<String> LanguageChooser;
    
    /**
     * Constructs settings panel 
     * 
     * @param c: Controller for changing style features
     * @param tp: Controller for changing turtle image/pen color
     */
    public SettingsPanel(Controller c, TurtlePanel tp, Turtle t) {
	CONTROLLER = c;
	TURTLE_PANEL = tp;
	TURTLE = t;
	initializeObjects();
    }
    
    public void setTP(TurtlePanel TP) {
	TURTLE_PANEL = TP;
    }

    /**
     * @return HBox containing settings panels
     */
    public HBox construct() {
	HBox box = new HBox(20, BackgroundChooser, PenChooser, ImageChooser, LanguageChooser);
	return box;
    }

    /**
     * Factory method for constructing drop down boxes 
     * 
     * @param text
     * @param options
     * @return
     */
    private ComboBox<String> chooserFactory(String text, Set<String> options) {
	ComboBox<String> chooser = new ComboBox<String>();
	chooser.getStyleClass().add("combo-box");
	chooser.getItems().addAll(options);
	chooser.setPromptText(text);
	return chooser;
    }

    /**
     * Initializes all chooser objects
     */
    private void initializeObjects() {
	BackgroundChooser = chooserFactory("Background", COLOR_RESOURCES.keySet());
	BackgroundChooser.setOnAction(click->{ TURTLE_PANEL.changeBack(Color.web(COLOR_RESOURCES.getString(BackgroundChooser.getValue())));});
	
	PenChooser = chooserFactory("Pen Color", COLOR_RESOURCES.keySet());
	PenChooser.setOnAction(click->{ TURTLE.setPenColor(PenChooser.getValue());});
	
	ImageChooser = chooserFactory("Image", getFiles(IMAGES));
	ImageChooser.setOnAction(click->{ TURTLE.changeImage("resources/images/" + ImageChooser.getValue() + ".png");});
	
	LanguageChooser = chooserFactory("Languages", getFiles(LANGUAGES));
	LanguageChooser.setOnAction(click->{CONTROLLER.updateLanguage(LanguageChooser.getValue());});
    }

    /**
     * Gets all files in directory and takes away file type for display
     * @param directory
     * @return
     */
    private Set<String> getFiles(String directory) {
	File[] files = new File(directory).listFiles();
	Set<String> ret = new HashSet<String>();
	for(File file : files){
	    ret.add(file.getName().substring(0, file.getName().indexOf(".")));
	}
	return ret;
    }
}
