package view.panels;

import java.io.File;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import controller.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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
    public SettingsPanel(Controller c, TurtlePanel tp) {
	CONTROLLER = c;
	TURTLE_PANEL = tp;
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
     * Factory method for constructing dropdown boxes 
     * 
     * @param text
     * @param options
     * @return
     */
    private ComboBox<String> chooserFactory(String text, Set<String> options) {
	ComboBox<String> chooser = new ComboBox<String>();
	chooser.getItems().addAll(options);
	chooser.setPromptText(text);
	return chooser;
    }

    /**
     * Initializes all chooser objects
     */
    private void initializeObjects() {
	BackgroundChooser = chooserFactory("Background", COLOR_RESOURCES.keySet());
	handleBackgroundPicker();
	PenChooser = chooserFactory("Pen Color", COLOR_RESOURCES.keySet());
	handlePenColorPicker();
	ImageChooser = chooserFactory("Image", getFiles(IMAGES));
	handleImagePicker();
	LanguageChooser = chooserFactory("Languages", getFiles(LANGUAGES));
	handleLanguagePicker();
    }


    private void handlePenColor() {
	TURTLE_PANEL.setPenColor(PenChooser.getValue());
    }

    private void handleBackgroundPicker() {
	BackgroundChooser.setOnAction(click->{
	    Color color = Color.web(COLOR_RESOURCES.getString(BackgroundChooser.getValue()));
	    TURTLE_PANEL.changeBack(color);});
    }

    private void handlePenColorPicker() {
	PenChooser.setOnAction(click->{ handlePenColor();});
    }

    private void handleLanguagePicker() {
	LanguageChooser.setOnAction(click->{CONTROLLER.updateLanguage(LanguageChooser.getValue());});
    }
    
    private void handleImagePicker() {
	ImageChooser.setOnAction(click->{ 
	    String imageName = "resources/images/" + ImageChooser.getValue() + ".png";
	    System.out.println(imageName);
	    TURTLE_PANEL.setTurtleImage(imageName);});
    }

    private Set<String> getFiles(String directory) {
	File[] files = new File(directory).listFiles();
	Set<String> ret = new HashSet<String>();
	for(File file : files){
	    ret.add(file.getName().substring(0, file.getName().indexOf(".")));
	}
	return ret;
    }

}
