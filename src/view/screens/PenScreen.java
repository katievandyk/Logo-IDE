package view.screens;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ModelController;
import view.turtle.Turtle;

/**
 * Pop-up screen that allows user to toggle pen settings such as color, thickness
 * and whether pen is up or down
 * 
 * @author Katherine Van Dyk
 *
 */
public class PenScreen {
    private ModelController CONTROLLER;
    private ArrayList<Turtle> TURTLES;
    private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");

    private ComboBox<String> ColorChooser;
    private ComboBox<String> ThicknessChooser;
    private ComboBox<String> UpDownChooser;

    // Source: https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java
    private final Set<Integer> thicks = IntStream.rangeClosed(1, 10)
	    .boxed().collect(Collectors.toSet());
    private final Set<String> upDown = new HashSet<String>();

    /**
     * Constructor that takes in arraylist of current turtles @param t and model controller @c
     */
    public PenScreen(ModelController c, ArrayList<Turtle> t ) {
	TURTLES = t;
	CONTROLLER = c;
	Stage stage = new Stage();
	stage.setTitle("CUSTOMIZE");
	Group root = new Group();
	stage.setScene(new Scene(root, 500, 50));
	initialize();
	HBox choosers = new HBox(12, ColorChooser, ThicknessChooser, UpDownChooser);
	root.getChildren().add(choosers);
	stage.show();
	stage.centerOnScreen();
    }

    /**
     * Initializes choosers to be displayed on pen screen
     */
    private void initialize() {
	ColorChooser = chooserFactory("Pen Color", COLOR_RESOURCES.keySet());
	ColorChooser.setOnAction(click->{ 
		for(Turtle t : TURTLES) {
			if(t.getActive()) {
				t.setPenColor(ColorChooser.getValue());
			}
		}
		});

	Set<String> strThicks = new HashSet<String>();
	for(int i : thicks) {
	    strThicks.add(Integer.toString(i));
	}

	ThicknessChooser  = chooserFactory("Pen Thickness", strThicks);
	ThicknessChooser.setOnAction(click->{ 
		for(Turtle t : TURTLES) {
			if(t.getActive()) {
				t.getPen().setThickness(ThicknessChooser.getValue());
			}
		}
		});

	upDown.add("true");
	upDown.add("false");
	UpDownChooser = chooserFactory("Pen Down", upDown);
	UpDownChooser.setOnAction(click->{
	    if(UpDownChooser.getValue().equals("true")){
		CONTROLLER.update("pd");
	    }
	    else CONTROLLER.update("pu");
	});
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
}
