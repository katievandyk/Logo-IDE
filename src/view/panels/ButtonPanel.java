package view.panels;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.ModelController;
import view.turtle.Turtle;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private ArrayList<Turtle> TURTLE;
    private double sliderCurrent;

    /**
     * Constructor for button panel object
     * 
     * @param c: ModelController
     * @param t: List of all current turtles
     */
    public ButtonPanel(ModelController c, ArrayList<Turtle> t) {
	sliderCurrent = 5;
	CONTROLLER = c;
	TURTLE = t;
	makeUpButton();
	makeDownButton();
	makeLeftButton();
    }

    /**
     * @return VBox containing all arranged buttons
     */
    public VBox construct() {
	HBox middleRow = new HBox(12, makeLeftButton(), animateButtons(), makeRightButton());
	VBox allButtons = new VBox(12, makeUpButton(), middleRow, makeDownButton(), makeSlider()); 
	allButtons.setAlignment(Pos.CENTER);
	middleRow.setAlignment(Pos.CENTER);
	return allButtons;
    }

    /**
     * @return VBox containing all buttons used for animation of the turtle
     */
    private VBox animateButtons() {
	HBox topRow = new HBox(12, makePlayButton(), makePauseButton());
	HBox bottomRow = new HBox(12, makeStepButton(), makeResetButton());
	VBox buttons = new VBox(topRow, bottomRow);
	buttons.getStyleClass().add("vbox");
	return buttons;
    }

    /**
     * @return Play button for playing the animation
     */
    private Button makePlayButton() {
	Button playButton = BUTTON.imageButton("/resources/images/play.png");
	playButton.setOnAction(click->{
	    for(Turtle t : TURTLE) {
		if(t.getActive()) {
		    t.playAnimation();
		}
	    }
	});
	return playButton;
    }

    /**
     * @return Button for moving manually in the up direction
     */
    private Button makeUpButton() {
	Button upButton = BUTTON.styledButton("/resources/images/up.png", "updownButton");
	upButton.setOnAction(click->{ 
	    CONTROLLER.update("fd 2");
	});
	return upButton;
    }

    /**
     * @return Button for moving manually in the down direction
     */
    private Button makeDownButton() {
	Button downButton = BUTTON.styledButton("/resources/images/down.png", "updownButton");
	downButton.setOnAction(click->{ 
	    CONTROLLER.update("bk 2");
	});
	return downButton;
    }

    /**
     * @return Button for turning manually in the left direction
     */
    private Button makeLeftButton() {
	Button leftButton = BUTTON.styledButton("/resources/images/left.png", "leftrightButton");
	leftButton.setOnAction(click->{ 
	    CONTROLLER.update("lt 15");
	});
	return leftButton;
    }

    /**
     * @return Button for turning manually in the right direction
     */
    private Button makeRightButton() {
	Button rightButton = BUTTON.styledButton("/resources/images/right.png", "leftrightButton");
	rightButton.setOnAction(click->{ 
	    CONTROLLER.update("rt 15");
	});
	return rightButton;
    }

    /**
     * @return Pause button
     */
    private Button makePauseButton() {
	Button pauseButton = BUTTON.imageButton("/resources/images/pause.png");
	pauseButton.setOnAction(click->{
	    for(Turtle t : TURTLE) {
		if(t.getActive()) {
		    t.pauseAnimation();
		}
	    }
	});
	return pauseButton;
    }

    /**
     * @return Reset button
     */
    private Button makeResetButton() {
	Button resetButton = BUTTON.imageButton("/resources/images/reset.png");
	resetButton.setOnAction(click->{
	    CONTROLLER.update("cs");
	});
	return resetButton;
    }

    /**
     * @return Step button
     */
    private Button makeStepButton() {
	Button playButton = BUTTON.imageButton("/resources/images/step.png");
	playButton.setOnAction(click->{
	    for(Turtle t : TURTLE) {
		if(t.getActive()) {
		    t.playAnimation();
		}
	    }
	});
	return playButton;
    }

    /**
     * @return VBox containing slider and corresponding label, for changing turtle's animation speed
     */
    private VBox makeSlider() {
	Slider slider = new Slider();
	slider.setMin(1);
	slider.setMax(10);
	slider.setBlockIncrement(.1);
	slider.setValue(8);
	handleSlider(slider);
	Text speed = new Text("Speed");
	speed.setId("label");
	VBox ret = new VBox(12, slider, speed);
	VBox.setMargin(speed, new Insets(0, 0, 0, 180));
	return ret;
    }

    /**
     * Handles when the user changes the value of @param slider
     */
    private void handleSlider(Slider slider) {
	slider.valueProperty().addListener(new ChangeListener<Number>() {
	    public void changed(ObservableValue<? extends Number> ov,
		    Number old_val, Number new_val) {
		sliderCurrent = new_val.doubleValue();
	    }
	});
    }

    /**
     * @return Current value of slider
     */
    public double getSliderValue() {
	return sliderCurrent;
    }


}
