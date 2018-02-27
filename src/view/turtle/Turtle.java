package view.turtle;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.state.State;

/**
 * Turtle object that moves on the Turtle Panel according to user input
 * 
 * @author Katherine Van Dyk
 *
 */
public class Turtle extends ImageView {

    private ImageView image;
    private boolean penUp;
    private TurtlePen pen;
    private final int TURTLE_HEIGHT = 50;
    private final int TURTLE_WIDTH = 25;

    /**
     * Constructor for turtle object
     * 
     * @param img: Image for turtle object display
     * @param screenHeight: Height of turtle panel
     * @param screenWidth: Width of turtle panel
     */
    public Turtle(String img, double height, double width) {
	this.image = makeImage(img, height, width);
	this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
	this.penUp = true;
    }

    /**  
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
    }

    public boolean penUp() {
	return penUp;
    }

    public void setColor(String color) {
	pen.setColor(color);
    }

    /**
     * Changes images coordinates
     * 
     * @param x: new x-position of turtle
     * @param y: new y-position of turtle
     * @return ImageView of updated turtle image
     */
    public ImageView changeImage(double x, double y) {
	image.setX(x);
	image.setY(y);
	return image;
    }

    /**
     * Makes initial turtle image
     * 
     * @param img
     * @param height
     * @param width
     * @return
     */
    private ImageView makeImage(String img, double height, double width) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	image = new ImageView(temp);
	image.setX((width - TURTLE_WIDTH) / 2);
	image.setY((height + TURTLE_HEIGHT) / 2);
	return image;
    }

    /**
     * Sets state of turtle
     * 
     * @param newState
     */
    public void updateState(State newState, Group root) {
	if(penUp != newState.getPen() && !newState.getPen()) {
	    pen.setLocation(image.getX(), image.getY());
	}
	if(!newState.getPen()) {
	    Line line = pen.addLine(newState.getX(), newState.getY());
	    root.getChildren().add(line);
	}
	penUp = newState.getPen();
	image.setRotate(newState.getAngle());
	image.setX(newState.getX());
	image.setY(newState.getY());
	image.toFront();
    }


    /**
     * Update states for one command
     * 
     * @param states: All changes in state
     */
    public void updateStates(List<State> states, Group root) {
	for(State state : states) {
	    this.updateState(state, root);
	}
    }

    public void setPen(boolean newState) {
	penUp = newState;
    }

}
