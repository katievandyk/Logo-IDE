package view.panels;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
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

    }

    /**
     * @return HBox containing settings panels
     */
    public VBox construct() {
	HBox middleRow = new HBox(12, makeLeftButton(), animateButton(), makeRightButton());
	VBox allButtons = new VBox(12, makeUpButton(), middleRow, makeDownButton()); 
	allButtons.setAlignment(Pos.CENTER);
	middleRow.setAlignment(Pos.CENTER);
	return allButtons;
    }

    private VBox animateButton() {
	HBox topRow = new HBox(12, makePlayButton(), makePauseButton());
	HBox bottomRow = new HBox(12, makeStepButton(), makeSaveButton());
	VBox buttons = new VBox(12, topRow, bottomRow);
	buttons.getStyleClass().add("vbox");
	return buttons;
    }
    
    private Button makePlayButton() {
	Button playButton = buttonFactory("/resources/images/play.png");
	return playButton;
    }
    
    private Button makeUpButton() {
	return buttonFactory("/resources/images/up.png");
    }
    
    private Button makeDownButton() {
	return buttonFactory("/resources/images/down.png");
    }
    private Button makeLeftButton() {
	return buttonFactory("/resources/images/left.png");
    }
    private Button makeRightButton() {
	return buttonFactory("/resources/images/right.png");
    }

    private Button makePauseButton() {
	Button playButton = buttonFactory("/resources/images/pause.png");
	return playButton;

    }
    
    private Button makeSaveButton() {
	Button playButton = buttonFactory("/resources/images/save.png");
	return playButton;

    }

    private Button makeStepButton() {
	Button playButton = buttonFactory("/resources/images/step.png");
	return playButton;

    }

    private Button buttonFactory(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
}
