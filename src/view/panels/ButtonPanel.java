package view.panels;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
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
     * @return HBox containing settings panels
     */
    public VBox construct() {
	HBox middleRow = new HBox(12, makeLeftButton(), animateButton(), makeRightButton());
	VBox allButtons = new VBox(12, makeUpButton(), middleRow, makeDownButton(), makeSlider()); 
	allButtons.setAlignment(Pos.CENTER);
	middleRow.setAlignment(Pos.CENTER);
	return allButtons;
    }

    private VBox animateButton() {
	HBox topRow = new HBox(12, makePlayButton(), makePauseButton());
	HBox bottomRow = new HBox(12, makeStepButton(), makeResetButton());
	VBox buttons = new VBox(topRow, bottomRow);
	buttons.getStyleClass().add("vbox");
	return buttons;
    }
    
    private Button makePlayButton() {
	Button playButton = buttonFactory("/resources/images/play.png");
	return playButton;
    }
    
    private Button makeUpButton() {
	return dirButtonFactory("/resources/images/up.png", "updownButton");
    }
    
    private Button makeDownButton() {
	return dirButtonFactory("/resources/images/down.png", "updownButton");
    }
    
    private Button makeLeftButton() {
	return dirButtonFactory("/resources/images/left.png", "leftrightButton");
    }
    
    private Button makeRightButton() {
	return dirButtonFactory("/resources/images/right.png", "leftrightButton");
    }

    private Button makePauseButton() {
	Button playButton = buttonFactory("/resources/images/pause.png");
	return playButton;

    }
    
    private Button makeResetButton() {
	Button playButton = buttonFactory("/resources/images/reset.png");
	return playButton;

    }

    private Button makeStepButton() {
	Button playButton = buttonFactory("/resources/images/step.png");
	return playButton;
    }
    
    private VBox makeSlider() {
	Slider slider = new Slider();
	slider.setMin(1);
	slider.setMax(2);
	slider.setBlockIncrement(.1);
	Text speed = new Text("Speed");
	speed.setId("label");
	VBox ret = new VBox(12, slider, speed);
	VBox.setMargin(speed, new Insets(0, 0, 0, 180));
	return ret;
    }
    

    private Button buttonFactory(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
    
    private Button dirButtonFactory(String stringImage, String ID) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	button.setId(ID);
	return button;
    }
}
