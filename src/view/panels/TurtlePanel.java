package view.panels;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.turtle.Turtle;
import view.Gobject;

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
    private Gobject TURTLE_PANEL;
    private Pane TURTLE_PANEL_PANE;
    private final String TURTLE_IMAGE = "view/panels/turtle.png";


    public TurtlePanel(int panelWidth, int panelHeight) {
        TURTLE = new Turtle(TURTLE_IMAGE, panelWidth, panelHeight);
        TURTLE_PANEL = new Gobject(panelWidth/2,panelHeight/2,panelWidth,panelHeight,4);
        TURTLE_PANEL_PANE = (Pane) TURTLE_PANEL.getObject();
        TURTLE_PANEL_PANE.setId("turtlePanel");
    }

    public Parent construct() {
    	TURTLE_PANEL_PANE.getChildren().add(TURTLE.display());
        return TURTLE_PANEL_PANE;
    }
    
    public void update(Stage stage) {
    	TURTLE_PANEL.updateObject(stage);
    }
}
