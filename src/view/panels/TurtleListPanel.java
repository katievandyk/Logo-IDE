package view.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import view.turtle.FrontendTurtle;

/**
 * USED FOR ADDITION.md
 * Panel class that creates a pane of all active Turtles. Clicking on a Turtle allows you to change that 
 * turtle's image, which is reflected both on the toolbar and on the screen.
 * 
 * @author Katherine Van Dyk
 *
 */
public class TurtleListPanel extends Panel {

    private Map<Button, FrontendTurtle> turtleButtonMap;
    private HBox turtles;
    private FrontendTurtle selectedTurtle;

    /**
     * Constructor for the TurtleList panel that takes in an initial list of all frontend Turtles. 
     * 
     * @param allTurtles: all available turtles
     */
    public TurtleListPanel(List<FrontendTurtle> allTurtles) {
	turtleButtonMap = new HashMap<Button, FrontendTurtle>();
	selectedTurtle = allTurtles.get(0);	
	createTurtleButtons(allTurtles);
    }
    
    /**
     * Constructs a ScrollPane of all turtle images, and creates an ImageChooser below to change Turtle image.
     */
    @Override
    public Pane construct() {
	ScrollPane turtleDisplay = new ScrollPane(turtles);
	turtleDisplay.setMinWidth(300);
	turtleDisplay.setMaxWidth(300);
	turtleDisplay.setMinHeight(75);
	turtleDisplay.setFitToWidth(true);
	turtleDisplay.setFitToHeight(true);
	VBox pane = new VBox(turtleDisplay, chooseTurtleImage());
	return pane;
    }

    /**
     * Creates a buttons to hold all available Turtles.
     * 
     * @param allTurtles: All available frontendTurtles
     * @return HBox containing all buttons
     */
    private HBox createTurtleButtons(List<FrontendTurtle> allTurtles) {
	turtles = new HBox();
	for(FrontendTurtle turtle : allTurtles) {
	    turtles.getChildren().add(constructButton(turtle));
	    turtles.setFillHeight(true);
	    HBox.setHgrow(turtles, Priority.ALWAYS);
	}
	turtles.setAlignment(Pos.CENTER);
	return turtles;
    }

    /**
     * Constructs a single button with the turtle's current image
     * 
     * @param turtle: Turtle to make a button with
     * @return Button containing Turtle's image
     */
    private Button constructButton(FrontendTurtle turtle) {
	Button turtleButton = BUTTON.imageButton("/" + turtle.image());
	turtleButtonMap.put(turtleButton, turtle);
	turtleButton.setOnMouseClicked(arg0 -> {
	    selectedTurtle = turtleButtonMap.get(turtleButton);

	});
	return turtleButton;
    }

    /**
     * Creates Combobox for selecting between different turtle images
     * 
     * @return Combobox containing all available turtle images
     */
    private ComboBox<String> chooseTurtleImage() {
	ComboBox<String> chooseImage = CHOOSER.chooser("Image", getFiles(IMAGES));
	chooseImage.setOnAction(click->{ 
	    selectedTurtle.changeImage("resources/turtles/" + chooseImage.getValue() + ".png");
	    updateImage();
	});
	return chooseImage;
    }

    /**
     * Method to add @param turtle to the scrollpane of all turtles
     */
    public void addTurtle(FrontendTurtle turtle) {
	constructButton(turtle);
	turtles.getChildren().add(constructButton(turtle));
    }

    /**
     * Updates the scrollpane image of a turtle when its new image is selected
     */
    private void updateImage() {
	for(Button button : turtleButtonMap.keySet()) {
	    if(turtleButtonMap.get(button) == selectedTurtle) {
		Image img = new Image(getClass().getResourceAsStream("/" + selectedTurtle.image()));
		button.setGraphic(new ImageView(img));
	    }
	}
    }
} 
