package view.panels;

import java.io.File;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import controller.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.turtle.Turtle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class that generates the stylistic settings panel at the top of the turtle screen.
 * 
 */
public class ButtonPanel {

    /**
     * Constructs settings panel 
     * 
     * @param c: Controller for changing style features
     * @param tp: Controller for changing turtle image/pen color
     */
    public ButtonPanel() {

	initializeObjects();
    }

    /**
     * @return HBox containing settings panels
     */
    public HBox construct() {
	HBox box1 = new HBox(12, ImageChooser, LanguageChooser);
	HBox box2 = new HBox(12, BackgroundChooser, PenChooser);
	VBox box = new VBox(12, box1, box2);
	return box;
    }

    private VBox animateButtons{
	HBox topRow = new HBox(12, makePlayButton(), makePauseButton());
    }

    private Button makePlayButton() {
	Button playButton = buttonFactory("play.png");

    }

    private Button makePauseButton() {
	Button playButton = buttonFactory("pause.png");

    }

    private Button buttonFactory(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
}
