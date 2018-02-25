package view.panels;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.turtle.Turtle;
import view.Gobject;

/**
 * 
 * @author Brandon Dalla Rosa
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
    private Rectangle BOUNDS;
    private Group root;
    double currentWidth;
    double currentHeight;
    double currentxloc;
    double currentyloc;
    ScalingFactory ScalingFactory;


    public TurtlePanel(int panelWidth, int panelHeight) {
	init = true;
	currentWidth = panelWidth*1.5;
	currentHeight = panelHeight*1.5;
	currentxloc = 10;
	currentyloc = 10;
	ScalingFactory = new ScalingFactory();
	BOUNDS = new Rectangle(currentxloc,currentyloc,currentWidth,currentHeight);
	BOUNDS.setStroke(Color.BLACK);
	BOUNDS.setFill(Color.WHITE);
	TURTLE = new Turtle(TURTLE_IMAGE, currentWidth, currentHeight);
	TURTLE_PANEL = new Gobject(panelWidth/2,panelHeight/2,panelWidth,panelHeight,4);
	TURTLE_PANEL_PANE = (Pane) TURTLE_PANEL.getObject();
	TURTLE_PANEL_PANE.setId("turtlePanel");
    }

    public Parent construct(Group root) {
	root.getChildren().add(BOUNDS);
	return TURTLE_PANEL_PANE;
    }

    public void update(Stage current) {
	TURTLE_PANEL.updateObject(current);
    }

    public void changeDimensions(double width, double height) {
	BOUNDS.setLayoutX(ScalingFactory.changeXLocation(currentxloc, width));
	BOUNDS.setLayoutY(ScalingFactory.changeYLocation(currentyloc, height));
	BOUNDS.setWidth(ScalingFactory.changeWidth(width));
	BOUNDS.setHeight(ScalingFactory.changeHeight(height));
    } 


    public void addTurtle(double x, double y) {
	root.getChildren().remove(TURTLE.display());
	root.getChildren().add(TURTLE.changeImage(x, y));
    }

}
