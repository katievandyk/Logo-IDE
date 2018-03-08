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
public class TurtlePanel  {
    private Rectangle BOUNDS;
    private final double width = 700;
    private final double height = 420;
    double screenWidth;

    /**
     * Constructs turtle panel based on screen dimensions
     * 
     * @param panelWidth
     * @param panelHeight
     */
    public TurtlePanel() {
	BOUNDS = new Rectangle(width, height);
	BOUNDS.getStyleClass().add("my-rect");
    }

    public Rectangle construct() {
	return BOUNDS;
    }
    
    public double height() {
	return height;
    }

    public double width() {
 	return width;
     }

    public void changeBack(Color color) {
	BOUNDS.setFill(color);
    }
}
