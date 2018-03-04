package view.panels;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
	private Rectangle BOUNDS;
	double currentWidth;
	double currentHeight;
	double screenWidth;
	double screenHeight;
	boolean init;

	/**
	 * Constructs turtle panel based on screen dimensions
	 * 
	 * @param panelWidth
	 * @param panelHeight
	 */
	public TurtlePanel(int width, int height) {
		init = true;
		currentWidth = width;
		currentHeight = height;

	}
	
	public Rectangle construct() {
		BOUNDS = new Rectangle(currentWidth, currentHeight);
		BOUNDS.getStyleClass().add("rectangle");
		BOUNDS.setStroke(Color.BLACK);
		BOUNDS.setFill(Color.WHITE);
		return BOUNDS;
	}

	public void changeBack(Color color) {
		BOUNDS.setFill(color);
	}

}
