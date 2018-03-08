package view.panels;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.ModelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class that generates the stylistic settings panel at the top of the turtle screen.
 * 
 */
public class ButtonPanel extends Panel {
    private ModelController CONTROLLER;
    
    public ButtonPanel(ModelController c) {
	CONTROLLER = c;
	makeUpButton();
	makeDownButton();
	makeLeftButton();
    }
    
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
	Button playButton = BUTTON.imageButton("/resources/images/play.png");
	return playButton;
    }
    
    private Button makeUpButton() {
	Button upButton = BUTTON.styledButton("/resources/images/up.png", "updownButton");
	upButton.setOnAction(click->{ 
	   CONTROLLER.update("fd 2");
	});
	return upButton;
    }
    
    private Button makeDownButton() {
	Button downButton = BUTTON.styledButton("/resources/images/down.png", "updownButton");
	downButton.setOnAction(click->{ 
	   CONTROLLER.update("bk 2");
	});
	return downButton;
    }
    
    private Button makeLeftButton() {
	Button leftButton = BUTTON.styledButton("/resources/images/left.png", "leftrightButton");
	leftButton.setOnAction(click->{ 
	   CONTROLLER.update("lt 15");
	});
	return leftButton;
    }
    
    private Button makeRightButton() {
	Button rightButton = BUTTON.styledButton("/resources/images/right.png", "leftrightButton");
	rightButton.setOnAction(click->{ 
	   CONTROLLER.update("rt 15");
	});
	return rightButton;
    }

    private Button makePauseButton() {
	Button playButton = BUTTON.imageButton("/resources/images/pause.png");
	return playButton;
    }
    
    private Button makeResetButton() {
	Button playButton = BUTTON.imageButton("/resources/images/reset.png");
	return playButton;

    }

    private Button makeStepButton() {
	Button playButton = BUTTON.imageButton("/resources/images/step.png");
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
   
  
}
