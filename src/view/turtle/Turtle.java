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
    private double zeroX;
    private double zeroY;
    private final int TURTLE_HEIGHT = 50;
    private final int TURTLE_WIDTH = 25;
    private double HEIGHT;
    private double WIDTH;
    private final String IMAGE;

    /**
     * Constructor for turtle object
     * 
     * @param img: Image for turtle object display
     * @param screenHeight: Height of turtle panel
     * @param screenWidth: Width of turtle panel
     */
    public Turtle(String img, double height, double width) {
	this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
	this.penUp = true;
	this.HEIGHT = height;
	this.WIDTH = width;
	this.zeroX = (width - TURTLE_WIDTH) / 2;
	this.zeroY = (height + TURTLE_HEIGHT) / 2; 
	this.image = makeImage(img);
	IMAGE = img;
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
    private ImageView makeImage(String img) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	image = new ImageView(temp);
	image.setX(zeroX);
	image.setY(zeroY);
	return image;
    }

    /**
     * Sets state of turtle
     * 
     * @param newState
     */
    public void updateState(State newState, Group root) {
	setPen(root, newState.getPen(), newState.getX(), newState.getY());
	setPosition(newState.getAngle(), newState.getX(), newState.getY());
    }


    private void setPosition(double angle, double x, double y) {
	image.setRotate(angle);
	if(x < 0 || x > WIDTH || y < 0 || y > HEIGHT ) {
	    show(false);
	}
	image.setX(zeroX + x);
	image.setX(zeroY + y);
	image.toFront();
    }

    private void setPen(Group root, boolean newPenUp, double x, double y) {
	if(penUp != newPenUp && !newPenUp) {
	    pen.setLocation(image.getX(), image.getY());
	}
	if(!newPenUp) {
	    Line line = pen.addLine(x, y);
	    root.getChildren().add(line);
	}
	penUp = newPenUp;
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

    public void setPenColor(String color) {
	pen.setColor(color);
    }

    public void show(boolean show) {
	if(!show) {
	    image.setImage(null);
	}
	else {
	    image.setImage(new Image(getClass().getClassLoader().getResourceAsStream(IMAGE)));
	}
    }
    
    private void wrap() {
	show(false);
    }

}
