package view.panels;

import java.util.List;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.turtle.Turtle;
import model.state.State;

/**
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @date 2/24/18
 * 
 * Class to generate the cell panel to be displayed on the center of the simulation screen.
 * The cell panel child nodes are held in a VBox object.
 */
public class TurtlePanel {
	private final Turtle TURTLE;
	private final String TURTLE_IMAGE = "resources/images/defaultTurtle.png";
	private Rectangle BOUNDS;
	private Pane ROOT;
	double currentWidth;
	double currentHeight;
	double currentxloc;
	double currentyloc;
	double screenWidth;
	double screenHeight;
	boolean init;

	/**
	 * Constructs turtle panel based on screen dimensions
	 * 
	 * @param panelWidth
	 * @param panelHeight
	 */
	public TurtlePanel(int width, int height, BorderPane pane) {
		init = true;
		currentWidth = width;
		currentHeight = height;
		currentxloc = 10;
		currentyloc = 40;
		BOUNDS = new Rectangle(currentWidth, currentHeight);
		BOUNDS = new Rectangle(currentWidth,currentHeight);
		BOUNDS.setLayoutY(currentyloc);
		BOUNDS.setLayoutX(currentxloc);
		BOUNDS.setStroke(Color.BLACK);
		BOUNDS.setFill(Color.WHITE);
		pane.setCenter(BOUNDS);
		TURTLE = new Turtle(TURTLE_IMAGE, height, width);
	}

	public void construct(Pane root) {
		ROOT = root;
		root.getChildren().add(TURTLE.display());
	}

	public void updateTurtle(List<State> states) {
		TURTLE.updateStates(states, ROOT);
	}

	public void changeBack(Color color) {
		BOUNDS.setFill(color);
	}

	public void setPenColor(String color) {
		TURTLE.setPenColor(color);
	}
	
	public void setTurtleImage(String image) {
		TURTLE.changeImage(image);
	}

}
