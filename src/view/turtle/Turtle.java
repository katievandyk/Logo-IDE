package view.turtle;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	this.penUp = false;
    }

    /**  
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
    }

    /**  
     * @return Display for turtle lines
     */
    public List<Line> lines() {
	return pen.getLines();
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
    public void updateState(State newState) {
	if(penUp != newState.getPen() && newState.getPen()) {
	    pen.newLine(image.getX(), image.getY(), newState.getX(), newState.getY());
	}
	else if(newState.getPen()) {
	    pen.addLine(image.getX(), image.getY());
	}
	penUp = newState.getPen();
	image.setRotate(newState.getAngle());
	image.setX(newState.getX());
	image.setY(newState.getY());
    }


    /**
     * Update states for one command
     * 
     * @param states: All changes in state
     */
    public void updateStates(List<State> states) {
	for(State state : states) {
	    this.updateState(state);
	}
    }
    
    public void setPen(boolean newState) {
	penUp = newState;
    }

}
