package view.turtle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.state.State;
import view.panels.TurtlePanel;
import view.save.PaletteMap;

/**
 * Turtle object that moves on the Turtle Panel according to user input
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 *
 */
public class Turtle {
    private PaletteMap paletteMap;
    private TurtlePanel TURTLE_PANEL;
    private ImageView image;
    private boolean penDown;
    private TurtlePen pen;
    private double zeroX;
    private double zeroY;
    private final int TURTLE_HEIGHT = 40;
    private final int TURTLE_WIDTH = 40;
    private double HEIGHT;
    private double WIDTH;
    private String IMAGE;
    private double zX;
    private double zY;
    private double ANGLE;
    private boolean isActive = true;
    private int TURTLE_ID;
    private Movable MOVABLE;
    private Group TEMP_NODE;
    private boolean isCLR;
    private Animation ANIMATION = new SequentialTransition();
    private Queue<Animation> animationQueue = new LinkedList<Animation>();
    private Queue<Double[]> instQueue = new LinkedList<Double[]>();
    private Double[] nextState = {zX,zY,0.};
    private Group clearRoot;
    private double pastX;
    private double pastY;
    private double pastA;

    /**
     * Constructor for turtle object.
     * 
     * @param img: Image for turtle object display
     * @param screenHeight: Height of turtle panel
     * @param screenWidth: Width of turtle panel
     */
    public Turtle(String img, double height, double width, int id, TurtlePanel tp) {
	paletteMap = new PaletteMap();
	isCLR = false;
	TEMP_NODE = new Group();
	this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
	this.penDown = false;
	this.HEIGHT = height;
	this.WIDTH = width;
	this.zeroX = (width - TURTLE_WIDTH) / 2;
	this.zeroY = (height - TURTLE_HEIGHT) / 2; 
	this.image = makeImage(img);
	TURTLE_PANEL = tp;
	IMAGE = img;
	zX = zeroX;
	zY = zeroY;
	TURTLE_ID = id;
	MOVABLE = new Movable(TURTLE_WIDTH, TURTLE_HEIGHT);
    }

    /**  
     * Getter method for the display of the turtle.
     * 
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
    }

    /**
     * Getter method for the image string used
     * to create the current turtle image.
     * 
     * @return The string of the image.
     */
    public String image() {
	return IMAGE;
    }
    /**
     * Getter to determine whether the turtle pen is up or down.
     * 
     * @return Whether or not the pen is up or down as a boolean.
     */
    public boolean penUp() {
	return penDown;
    }

    /**
     * Method called to set the value of the pen being up 
     * or down.
     * 
     * @param bool: The new value of the pen.
     */
    public void penUp(boolean bool) {
	penDown = bool;
    }

    /**
     * Method called to set the pen color.
     * 
     * @param color: The new color.
     */
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
     * @param img: The desired string image.
     * @return The desired image.
     */
    private ImageView makeImage(String img) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	image = new ImageView(temp);
	image.setX(zeroX);
	image.setY(zeroY);
	return image;
    }
    /**
     * Method called to update the image in the turtle.
     * 
     * @param img: The new image.
     */
    public void changeImage(String img) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	IMAGE = img;
	image.setImage(temp);
    }

    /**
     * Sets state of turtle.
     * 
     * @param newState: The new state the turtle must conform to.
     */
    public void updateState(State newState, Group root) {
	clear(newState.getClear(), root);
	if(TURTLE_ID == newState.getID()) {
	    setPen(root, newState.getPen(), newState.getX(), newState.getY());
	    setPosition(newState.getAngle() + 90, newState.getX(), newState.getY());
	    show(newState.getShowing());
	    setPalettes(newState.getPalette(), Color.ALICEBLUE, newState.getBackground(), newState.getPencolor(), newState.getPensize(), newState.getShape());
	}
    }

    private void setPalettes(int palette, Color color, int background, int pencolor, int pensize, int shape) {
	Map<String, Map<String, String>> map = paletteMap.getMap();
	if(map.containsKey(Integer.toString(palette))) {
	    paletteMap.editMap(Integer.toString(palette), color );
	}
	else if(map.containsKey(Integer.toString(background))) {
	    TURTLE_PANEL.changeBack(paletteMap.getBackgroundColor(Integer.toString(background)));
	}
	else if(map.containsKey(Integer.toString(pencolor))) {
	    pen.setColor(paletteMap.getPenColor(Integer.toString(pencolor)));
	}
	else if(map.containsKey(Integer.toString(pensize))) {
	    pen.setThickness(Integer.toString(paletteMap.getPenThickness(Integer.toString(pensize))));
	}
	else if(map.containsKey(Integer.toString(shape))) {
	    image = paletteMap.getShape(Integer.toString(shape));
	}
    }

    /**
     * Sets the position and angle of the turtle by adding the desired animations
     * to the queue.
     * 
     * @param angle: The new angle.
     * @param x: The new x-value.
     * @param y: The new y-value.
     */
    private void setPosition(double angle, double x, double y) {
	if(x < zeroX - WIDTH || x > zeroX + WIDTH || y < zeroY - HEIGHT || y > zeroY + HEIGHT ) {
	    show(false);
	    return;
	}
	boolean animAdd = false;
	if(angle != pastA && pastX==x && pastY==y) {
	    Animation animation =  MOVABLE.rotate(image, angle - pastA);
	    animationQueue.add(animation);
	    animAdd = true;

	}
	if(pastX!=x || pastY!=y) {
	    Animation animation = MOVABLE.move(image, pastX+zeroX, pastY+zeroY, x + zeroX, y + zeroY); 
	    animationQueue.add(animation);
	    animAdd = true;
	}
	if(pastX==x && pastY==y && pastA == angle) {
	    image.toFront();
	}
	if(animAdd) {
	    Double[] toAdd = {zeroX + x,zeroY + y,angle};
	    instQueue.add(toAdd);
	}
	ANGLE = angle;
	pastA = angle;
	pastX = x;
	pastY = y;
    }

    /**
     * Handles the queue of animations and plays them one
     * at a time per turtle.
     */
    public void handleAnimation() {
	image.toFront();
	if(!animationQueue.isEmpty()) {
	    if(ANIMATION.getStatus()==Status.STOPPED) {
		ANIMATION = animationQueue.poll();
		System.out.println("apolled");
		ANIMATION.play();
		if(instQueue.size()>0) {
		    System.out.println("ipolled");
		    nextState = instQueue.poll();
		    System.out.println("x: "+nextState[0]);
		    System.out.println("y: "+nextState[1]);
		}
		while(ANIMATION.getStatus()==Status.STOPPED);
	    }
	}
    }


    /**
     * Creates the lines for the turtle by calculating the 
     * change in location. Adds them to temporary nodes so
     * they can be removed.
     * 
     * @param root: The root node.
     * @param newPenDown: If the pen is down.
     * @param x: New x-value.
     * @param y: New y-value.
     */
    private void setPen(Group root, boolean newPenDown, double x, double y) {
	if(!root.getChildren().contains(TEMP_NODE)) {
	    root.getChildren().add(TEMP_NODE);
	}
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
	    if(!isCLR) {
		Line line = pen.addLine(zeroX+x, zeroY+y);
		TEMP_NODE.getChildren().add(line);
	    }
	    else {
		isCLR = false;
		pen.setLocation(zX, zY);
	    }
	}
	penDown = newPenDown;
    }

    /**
     * Normalizes the angle to a simple readable format.
     * 
     * @return Normalized angle.
     */
    public double getAngle() {
	while(ANGLE > 360) {
	    ANGLE -= 360;
	}
	while(ANGLE <= 0) {
	    ANGLE += 360;
	}
	return ANGLE;
    }


    /**
     * Update states for one command. The first state is
     * mainly ignored as a workaround.
     * 
     * @param states: All changes in state
     */
    public void updateStates(List<State> states, Group root) {
	boolean workAround = paletteInput(states.get(0));
	for(State state : states) {
	    clear(state.getClear(), root);
	    if(workAround) {
		this.updateState(state, root);
	    }
	    workAround = true;
	}
    }

    /**
     * Sets the color of the pen based on a string input.
     * 
     * @param color: The new color.
     */
    public void setPenColor(String color) {
	pen.setColor(color);
    }

    /**
     * Display the desired image.
     * 
     * @param show: Whether or not to display.
     */
    public void show(boolean show) {
	if(!show) image.setImage(null);
	else image.setImage(new Image(getClass().getClassLoader().getResourceAsStream(IMAGE)));
    }

    /**
     * Determine if the new turtle location is in the desired boundaries.
     * 
     * @param x: New x-value.
     * @param y: New y-value.
     * @return If in boundaries.
     */
    public boolean inBounds(double x, double y) {
	if(x<=zX+WIDTH/2 && x>=zX-WIDTH/2 && y<=zY+HEIGHT/2 && y>=zY-HEIGHT/2) {
	    return true;
	}
	return false;
    }

    /**
     * Method called to clear the existing lines of the 
     * turtle, and also set the root clear node for future use.
     * 
     * @param clr: To clear or not to clear.
     * @param root: Node to clear.
     */
    public void clear(boolean clr, Group root) {
	clearRoot = root;
	if(clr) {
	    isCLR = true;
	    image.setX(zeroX);
	    image.setY(zeroY);
	    image.setRotate(0);
	    root.getChildren().remove(TEMP_NODE);
	    TEMP_NODE.getChildren().removeAll();
	    TEMP_NODE = new Group();
	}
    }

    private boolean paletteInput(State s) {
	if(s.getPalette() > 0 || s.getBackground() > 0 || s.getPencolor() > 0 || s.getPensize() > 0 || s.getShape() != 0) {
	    return true;
	}
	return false;
    }


    /**
     * Method called to clear the existing lines of the
     * turtle from classes which do not have access to
     * the root clear node.
     * 
     * @param clr: To clear or not to clear.
     */
    public void clear(boolean clr) {
	if(clr) {
	    isCLR = true;
	    image.setX(zeroX);
	    image.setY(zeroY);
	    image.setRotate(0);
	    clearRoot.getChildren().remove(TEMP_NODE);
	    TEMP_NODE.getChildren().removeAll();
	    TEMP_NODE = new Group();
	}
    }

    /**
     * Getter for the image x-position.
     * 
     * @return The x-position.
     */
    public int xPos() {
	return (int) (image.getX() - zX);
    }
    /**
     * Getter for the image y-position.
     * 
     * @return The image y-position.
     */
    public int yPos() {
	return (int) (zY - image.getY());
    }
    /**
     * Method to check if the turtle is currently active.
     * 
     * @return If turtle is active.
     */
    public boolean getActive() {
	return isActive;
    }
    /**
     * Method called to set the turtle as active or inactive.
     * 
     * @param next: The new turtle active level.
     */
    public void setActive(boolean next) {
	isActive = next;
	if(isActive) {
	    image.setOpacity(1.0);
	}
	else {
	    image.setOpacity(0.5);
	}
    }

    /**
     * Getter for the pen of the turtle.
     * 
     * @return The pen.
     */
    public TurtlePen getPen() {
	return pen;
    }

    public PaletteMap getPaletteMap(){
	return paletteMap;
    }

    /**
     * Method called to check if the turtle is being clicked,
     * and toggle activity if so.
     * 
     * @param x: Click x-location.
     * @param y: Click y-location.
     * @return If turtle was clicked.
     */
    public boolean toggleTurtle(double x, double y) {
	System.out.println(""+x+" "+y+"\n"+zX+" "+zY);
	x = x-20;   
	y = y-211;
	if(Math.abs(pastX+zeroX-x)<15 && Math.abs(pastY+zeroY-y)<15) {
	    if(isActive) {
		isActive = false;
		image.setOpacity(0.5);
	    }
	    else {
		isActive = true;
		image.setOpacity(1.0);
	    }
	    return true;
	}
	return false;
    }
    
    /**
     * Pauses the current turtle animation.
     */
    public void pauseAnimation() {
	ANIMATION.pause();
    }
    
    /**
     * Plays the current turtle animation if paused.
     */
    public void playAnimation() {
	ANIMATION.play();
    }
    
    /**
     * Set the speed of the current turtle animation.
     * 
     * @param speed: New speed of the animation.
     */
    public void setSpeed(double speed) {
	MOVABLE.setMoveSpeed(speed);
    }
    
    /**
     * Getter for the turtle ID.
     * 
     * @return: The turtle ID.
     */
    public int getID() {
	return TURTLE_ID;
    }
}
