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
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
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
    private boolean init;
    double screenWidth;
    double screenHeight;
    double currentWidth;
    double currentHeight;
    double currentxloc;
    double currentyloc;


    public TurtlePanel(int panelWidth, int panelHeight) {
	init = true;
	currentWidth = panelWidth*1.5;
	currentHeight = panelHeight*1.5;
	currentxloc = 10;
	currentyloc = 10;
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
	root.getChildren().add(TURTLE.display());
	return TURTLE_PANEL_PANE;
    }

    public void update(Stage current) {
	TURTLE_PANEL.updateObject(current);
	if(init) {
	    screenWidth = current.getWidth();
	    screenHeight = current.getHeight();
	    init = false;
	}
    }

    public void changeDimensions(double width, double height) {
	if(screenWidth!= width || screenHeight!= height) {
	    double relWidth = currentWidth/screenWidth;
	    double relHeight = currentHeight/screenHeight;
	    double relX = currentxloc/screenWidth;
	    double relY = currentyloc/screenHeight;
	    currentWidth = relWidth*width;
	    currentHeight = relHeight*height;
	    currentxloc = relX*width;
	    currentyloc = relY*height;
	    BOUNDS.setLayoutX(currentxloc);
	    BOUNDS.setLayoutY(currentyloc);
	    BOUNDS.setWidth(currentWidth);
	    BOUNDS.setHeight(currentHeight);
	    screenWidth = width;
	    screenHeight = height;  
	} 
    }

}
