package view.panels;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import controller.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import view.turtle.Turtle;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class that generates the stylistic settings panel at the top of the turtle screen.
 * 
 */
public class SettingsPanel extends Panel {
    private Controller CONTROLLER;
    private TurtlePanel TURTLE_PANEL;
    private Turtle TURTLE;
    private ColorPicker BackgroundChooser;
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

    /**
     * @return HBox containing settings panels
     */
    public HBox construct() {
	return new HBox(12, ImageChooser, LanguageChooser,  BackgroundChooser);
    }

    /**
     * Initializes all chooser objects
     */
    private void initializeObjects() {
	BackgroundChooser = CHOOSER.colorChooser("combo-box"); 
	BackgroundChooser.setOnAction(click ->{ TURTLE_PANEL.changeBack(BackgroundChooser.getValue());});

	ImageChooser = CHOOSER.chooser("Image", getFiles(IMAGES));
	ImageChooser.setOnAction(click->{ TURTLE.changeImage("resources/turtles/" + ImageChooser.getValue() + ".png");});

	LanguageChooser = CHOOSER.chooser("Languages", getFiles(LANGUAGES));
	LanguageChooser.setOnAction(click->{CONTROLLER.updateLanguage("resources.languages." + LanguageChooser.getValue());});
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
