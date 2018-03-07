package view.panels;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import controller.Controller;
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
    private Controller CONTROLLER;
    private Button upButton;
    private Button downButton;
    private Button leftButton;
    private Button rightButton;
    
    public ButtonPanel(Controller c) {
	CONTROLLER = c;
	makeUpButton();
	makeDownButton();
	makeLeftButton();
	makeRightButton();
    }
    
    /**
     * @return HBox containing settings panels
     */
    public VBox construct() {
	HBox middleRow = new HBox(12, leftButton, animateButton(), rightButton);
	VBox allButtons = new VBox(12, upButton, middleRow, downButton, makeSlider()); 
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
    
    private void makeUpButton() {
	upButton = styledButtonFactory("/resources/images/up.png", "updownButton");
	upButton.setOnAction(click->{ 
	   CONTROLLER.update("fd 2");
	});
    }
    
    private void makeDownButton() {
	downButton = styledButtonFactory("/resources/images/down.png", "updownButton");
	downButton.setOnAction(click->{ 
	   CONTROLLER.update("bk 2");
	});
    }
    
    private void makeLeftButton() {
	leftButton = styledButtonFactory("/resources/images/left.png", "leftrightButton");
	leftButton.setOnAction(click->{ 
	   CONTROLLER.update("lt 15");
	});
    }
    
    private void makeRightButton() {
	rightButton = styledButtonFactory("/resources/images/right.png", "leftrightButton");
	rightButton.setOnAction(click->{ 
	   CONTROLLER.update("rt 15");
	});
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
   
  
}
