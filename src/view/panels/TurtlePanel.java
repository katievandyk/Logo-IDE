package view.panels;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import view.turtle.Turtle;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class to generate the cell panel to be displayed on the center of the simulation screen.
 * The cell panel child nodes are held in a VBox object.
 */
public class TurtlePanel {
    private final Turtle TURTLE;
    private Pane TURTLE_PANEL;
    private final String TURTLE_IMAGE = "view/panels/turtle.png";


    public TurtlePanel(int panelWidth, int panelHeight) {
        TURTLE = new Turtle(TURTLE_IMAGE, panelWidth, panelHeight);
        TURTLE_PANEL = new Pane();
        TURTLE_PANEL.setId("turtlePanel");
    }

    public Parent construct() {
	TURTLE_PANEL.getChildren().add(TURTLE.display());
        return TURTLE_PANEL;
    }
    
    public void update() {
    }
}
