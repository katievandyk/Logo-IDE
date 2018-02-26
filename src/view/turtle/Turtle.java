package view.turtle;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public void test(double width, double height) {
	LinkedList<State> states = new LinkedList<State>();
	for(int i=1; i <= 10; i++) {
	    states.add(new State(width/2 + i*100, height/2 + i*100, 10, true, true));    
	}
	updateStates(states);
    }

    /**  
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
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
	image.setX(width / 2);
	image.setY(height / 2);
	return image;
    }

    /**
     * Sets state of turtle
     * 
     * @param newState
     */
    public void updateState(State newState) {
	image.setRotate(newState.getAngle());
	System.out.println(newState.getX());
	image.setX(newState.getX());
	System.out.println("IMAGE X:" + image.getX());
	image.setY(newState.getY());
	penUp = newState.getPen();
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

}
