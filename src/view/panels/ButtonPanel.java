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
    
    public ButtonPanel(ModelController c, ArrayList<Turtle> t) {
    sliderCurrent = 5;
	CONTROLLER = c;
	TURTLE = t;
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
	playButton.setOnAction(click->{
		for(Turtle t : TURTLE) {
			if(t.getActive()) {
				t.playAnimation();
			}
		}
	});
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
    
    private Button makeResetButton() {
	Button resetButton = BUTTON.imageButton("/resources/images/reset.png");
	resetButton.setOnAction(click->{
		for(Turtle t : TURTLE) {
			if(t.getActive()) {
				t.clear(true);;
			}
		}
	});
	return resetButton;
    }

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
    
    private VBox makeSlider() {
	Slider slider = new Slider();
	slider.setMin(1);
	slider.setMax(10);
	slider.setBlockIncrement(.1);
	slider.setValue(5);
	handleSlider(slider);
	Text speed = new Text("Speed");
	speed.setId("label");
	VBox ret = new VBox(12, slider, speed);
	VBox.setMargin(speed, new Insets(0, 0, 0, 180));
	return ret;
    }
    
    private void handleSlider(Slider slider) {
    	slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				sliderCurrent = new_val.doubleValue();
			}
		});
    }
    public double getSliderValue() {
    	return sliderCurrent;
    }
   
  
}
