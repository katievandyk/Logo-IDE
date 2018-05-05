package view.turtle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.state.State;
import view.panels.TurtlePanel;
import view.save.PaletteMap;
import view.screens.MainScreen;

/**
 * Turtle object that moves on the Turtle Panel according to user input
 * 
 * @author Brandon Dalla Rosa
 */
public class Turtle {
    private ImageView image;
    private boolean penDown = false;
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
    private boolean isCLR = false;
    private Animation ANIMATION = new SequentialTransition();
    private Queue<Animation> animationQueue = new LinkedList<Animation>();
    private Queue<Double[]> instQueue = new LinkedList<Double[]>();
    private Group clearRoot;
    private double pastX;
    private double pastY;
    private TurtleFactory FACTORY;
    private boolean stamp;
    private MainScreen screen;

    /**
     * Constructor for turtle object.
     * 
     * @param img: Image for turtle object display
     * @param screenHeight: Height of turtle panel
     * @param screenWidth: Width of turtle panel
     */
    public Turtle(String img, double height, double width, int id, TurtlePanel tp, MainScreen screen) {
		this(img, height, width, id, tp, screen, false);
    }
    
    public Turtle(String img, double height, double width, int id, TurtlePanel tp, MainScreen screen, boolean stamp) {
    	this.TEMP_NODE = new Group();
		this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
		this.HEIGHT = height;
		this.WIDTH = width;
		this.zeroX = (width - TURTLE_WIDTH) / 2;
		this.zeroY = (height - TURTLE_HEIGHT) / 2; 
		this.IMAGE = img;
		this.zX = zeroX;
		this.zY = zeroY;
		this.TURTLE_ID = id;
		this.FACTORY = new TurtleFactory(tp, this, pen);
		this.MOVABLE = new Movable(TURTLE_WIDTH, TURTLE_HEIGHT);
		this.stamp = stamp;
		this.screen = screen;
		makeImage(img);
    }

    /**  
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
    }

    /**
     * @return The string of the image used to create the current turtle image.
     */
    public String image() {
	return IMAGE;
    }
    /**
     * @return Whether or not the pen is up or down as a boolean.
     */
    public boolean penUp() {
	return penDown;
    }

    /**
     * Method called to set the value of the pen being up or down based on @param bool.
     */
    public void penUp(boolean bool) {
	penDown = bool;
    }

    /**
     * Method called to set the pen color to @param color: The new color.
     */
    public void setColor(String color) {
	pen.setColor(color);
    }

    /**
     * @return initial turtle image based on @param img: The desired string image.
     */
    public void makeImage(String img) {
	image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(img)));
	image.setX(zeroX);
	image.setY(zeroY);
    }

    /**
     * Method called to update the image to @param img in the turtle.
     */
    public void changeImage(String img) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	IMAGE = img;
	image.setImage(temp);
    }

    /**
     * @return Normalized angle
     */
    public double getAngle() {
	return FACTORY.normalizeAngle(ANGLE);
    }

    /**
     * Sets state of turtle to @param newState: The new state the turtle must conform to.
     */
    public void updateState(State newState, Group root) {
	clear(newState.getClear(), root);
	stamp(newState.getStamp(), newState);
	clearStamp(newState.getClearStamp());
	if(TURTLE_ID == newState.getID()) {
	    setPen(root, newState.getPen(), newState.getX(), newState.getY());
	    setPosition(newState.getAngle() + 90, newState.getX(), newState.getY());
	    show(newState.getShowing());
	    FACTORY.setPalettes(newState.getPalette(), newState.getPaletteRGB(), newState.getBackground(), newState.getPencolor(), newState.getPensize(), newState.getShape());
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
	if(angle != ANGLE && pastX==x && pastY==y) {
	    animationQueue.add(MOVABLE.rotate(image, angle - ANGLE));
	    animAdd = true;
	}
	if(pastX != x || pastY != y) {
	    animationQueue.add(MOVABLE.move(image, pastX+zeroX, pastY+zeroY, x + zeroX, y + zeroY));
	    image.setX(zeroX + x);
	    image.setY(zeroY + y);
	    animAdd = true;
	}
	image.toFront();
	if(animAdd) {
	    Double[] toAdd = {zeroX + x,zeroY + y,angle};
	    instQueue.add(toAdd);
	}
	ANGLE = angle;
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
		ANIMATION.play();
		while(ANIMATION.getStatus()==Status.STOPPED);
	    }
	}
    }

    /**
     * Creates the lines for the turtle by calculating the 
     * change in location based on the new @param x and new @param y
     * values. Adds them to temporary nodes so they can be removed.
     * 
     * @param root: The root node.
     * @param newPenDown: If the pen is down.
     */
    private void setPen(Group root, boolean newPenDown, double x, double y) {
	if(!root.getChildren().contains(TEMP_NODE)) {
	    root.getChildren().add(TEMP_NODE);
	}
	if(penDown != newPenDown && newPenDown) {
	    pen.setLocation(image.getX(), image.getY());
	}
	if(newPenDown) {
	    if(!FACTORY.inBounds(zeroX + x, zeroY + y, zX, zY)) {
		zeroX = zX - x;
		zeroY = zY - y;
	    }
	    if(!isCLR) TEMP_NODE.getChildren().add(pen.addLine(zeroX+x, zeroY+y));
	    else {
		isCLR = false;
		pen.setLocation(zX, zY);
	    }
	}
	penDown = newPenDown;
    }


    /**
     * Update states for one command. First state is duplicate of previous unless palette state.
     * 
     * @param states: All changes in state
     */
    public void updateStates(List<State> states, Group root) {
	boolean useFirst = FACTORY.paletteInput(states.get(0));
	for(State state : states) {
	    clear(state.getClear(), root);
	    stamp(state.getStamp(), state);
	    clearStamp(state.getClearStamp());
	    if(useFirst) {
		this.updateState(state, root);
	    }
	    useFirst = true;
	}
    }

    /**
     * Sets the color of the pen to @param color based on a string input.
     */
    public void setPenColor(String color) {
	pen.setColor(color);
    }

    /**
     * Display the desired image to @param show: Whether or not to display.
     */
    public void show(boolean show) {
	if(!show) image.setImage(null);
	else image.setImage(new Image(getClass().getClassLoader().getResourceAsStream(IMAGE)));
    }

    /**
     * Method called to clear the existing lines of the turtle, and also set @param root clear for future use.
     * 
     * @param clr: To clear or not to clear.
     */
    public void clear(boolean clr, Group root) {
	clearRoot = root;
	if(clr) {
	    root.getChildren().remove(TEMP_NODE);
	    clearHelper();
	}
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
	    clearRoot.getChildren().remove(TEMP_NODE);
	    clearHelper();
	}
    }

    /**
     * Helper method to clear objects from screen
     */
    public void clearHelper() {
	isCLR = true;
	image.setX(zeroX);
	image.setY(zeroY);
	TEMP_NODE.getChildren().removeAll();
	TEMP_NODE = new Group();
    }

    /**
     * @return The image x-position.
     */
    public int xPos() {
	return (int) (image.getX() - zX);
    }

    /**
     * @return The image y-position.
     */
    public int yPos() {
	return (int) (zY - image.getY());
    }

    /**
     * @return If turtle is currently active.
     */
    public boolean getActive() {
	return isActive;
    }

    /**
     * Method called to set the turtle as active or inactive based on @param next: The new turtle active level.
     */
    public void setActive(boolean next) {
	isActive = next;
	if(isActive) image.setOpacity(1.0);
	else image.setOpacity(0.5);
    }

    /**
     * @return The pen of the turtle.
     */
    public TurtlePen getPen() {
	return pen;
    }

    /**
     * @return Palette map for display
     */
    public PaletteMap getPaletteMap(){
	return FACTORY.getPaletteMap();
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
	if(FACTORY.canToggle(x,  y, pastX, pastY, zeroX, zeroY)) {
	    if(isActive) image.setOpacity(0.5);
	    else image.setOpacity(1.0);
	    isActive = !isActive;  
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
     * Sets new speed of animation to @param speed.
     */
    public void setSpeed(double speed) {
	MOVABLE.setMoveSpeed(speed);
    }

    /**
     * @return the turtle ID.
     */
    public int getID() {
	return TURTLE_ID;
    }
    
    public void stamp(boolean st, State state) {
    	if (st) {
    		screen.addStamp(zeroX+state.getX(), zeroY+state.getY(), ANGLE);
    	}
    }
    
    public void clearStamp(boolean clear) {
    	if (clear) {
    		screen.removeStamps();
    	}
    }
}
