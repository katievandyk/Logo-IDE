package view.turtle;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private boolean penDown;
    private TurtlePen pen;
    private double zeroX;
    private double zeroY;
    private final int TURTLE_HEIGHT = 60;
    private final int TURTLE_WIDTH = 40;
    private double HEIGHT;
    private double WIDTH;
    private final String IMAGE;
    private double zX;
    private double zY;

    /**
     * Constructor for turtle object
     * 
     * @param img: Image for turtle object display
     * @param screenHeight: Height of turtle panel
     * @param screenWidth: Width of turtle panel
     */
    public Turtle(String img, double height, double width) {
	this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
	this.penDown = false;
	this.HEIGHT = height;
	this.WIDTH = width;
	this.zeroX = (width - TURTLE_WIDTH) / 2;
	this.zeroY = (height + TURTLE_HEIGHT) / 2; 
	this.image = makeImage(img);
	IMAGE = img;
	zX = zeroX+0;
	zY = zeroY+0;
    }

    /**  
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
    }

    public boolean penUp() {
	return penDown;
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
    
    public void changeImage(String img) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	image.setImage(temp);
    }

    /**
     * Sets state of turtle
     * 
     * @param newState
     */
    public void updateState(State newState, Pane root) {
	setPen(root, newState.getPen(), newState.getX(), newState.getY());
	setPosition(newState.getAngle(), newState.getX(), newState.getY());
	show(newState.getShowing());
    }


    private void setPosition(double angle, double x, double y) {
	image.setRotate(angle + 90);
	//TODO lines bounds
	if(x < zeroX - WIDTH || x > zeroX + WIDTH || y < zeroY - HEIGHT || y > zeroY + HEIGHT ) {
	    show(false);
	}
	image.setX(zeroX + x);
	image.setY(zeroY + y);
	image.toFront();
    }

    private void setPen(Pane rOOT, boolean newPenDown, double x, double y) {
		if(penDown != newPenDown && newPenDown) {
			pen.setLocation(image.getX(), image.getY());
		}
		if(newPenDown) {
			double newX = zeroX + x;
			double newY = zeroY + y;
			if(!inBounds(newX,newY)) {
				newX = zeroX;
				newY = zeroY;
				zeroX = zX - x;
				zeroY = zY - y;
			}
			Line line = pen.addLine(zeroX+x, zeroY+y);
			rOOT.getChildren().add(line);
		}
		penDown = newPenDown;
	}


    /**
     * Update states for one command
     * 
     * @param states: All changes in state
     */
    public void updateStates(List<State> states, Pane root) {
	for(State state : states) {
	    this.updateState(state, root);
	}
    }

    public void setPen(boolean newState) {
	penDown = newState;
    }

    public void setPenColor(String color) {
	pen.setColor(color);
    }

    public void show(boolean show) {
	if(!show) {
	    System.out.println("HERE");
	    image.setImage(null);
	}
	else {
	    image.setImage(new Image(getClass().getClassLoader().getResourceAsStream(IMAGE)));
	}
    }
    
    public boolean inBounds(double x, double y) {
    	if(x<=zX+WIDTH/2 && x>=zX-WIDTH/2 && y<=zY+HEIGHT/2 && y>=zY-HEIGHT/2) {
    		return true;
    	}
    	return false;
    }

}
