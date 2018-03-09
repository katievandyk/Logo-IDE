package view.turtle;

import java.util.LinkedList;
import java.util.List;
<<<<<<< HEAD
import java.util.Map;
=======
import java.util.Queue;
>>>>>>> b33cacfe4df69f42c9d4c048ebc802f08a2b4195

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
 *
 */
public class Turtle {
<<<<<<< HEAD
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
    private Animation ANIMATION;
    private Group clearRoot;
    private PaletteMap paletteMap;
    private TurtlePanel TURTLE_PANEL;
=======
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
	private Double[] nextState = {0.,0.,0.};
	private Group clearRoot;
	private double pastX;
	private double pastY;
	private double pastA;

>>>>>>> b33cacfe4df69f42c9d4c048ebc802f08a2b4195

   
    /**
     * Constructor for turtle object
     * 
     * @param img: Image for turtle object display
     * @param screenHeight: Height of turtle panel
     * @param screenWidth: Width of turtle panel
     */
    public Turtle(String img, int id, TurtlePanel tp) {
	isCLR = false;
	TURTLE_PANEL = tp;
	paletteMap = new PaletteMap();
	TEMP_NODE = new Group();
	this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
	this.penDown = false;
	this.HEIGHT = tp.height();
	this.WIDTH = tp.width();
	this.zeroX = (tp.width() - TURTLE_WIDTH) / 2;
	this.zeroY = (tp.height() - TURTLE_HEIGHT) / 2; 
	this.image = makeImage(img);
	IMAGE = img;
	zX = zeroX;
	zY = zeroY;
	TURTLE_ID = id;
	MOVABLE = new Movable(TURTLE_WIDTH, TURTLE_HEIGHT);
    }

    /**  
     * @return Display for turtle image
     */
    public ImageView display() {
	return this.image;
    }

    public String image() {
	return IMAGE;
    }

    public boolean penUp() {
	return penDown;
    }
    
    public void penUp(boolean bool) {
	penDown = bool;
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
	IMAGE = img;
	image.setImage(temp);
    }

    /**
     * Sets state of turtle
     * 
     * @param newState
     */
    public void updateState(State newState, Group root) {
	clear(newState.getClear(), root);
	if(TURTLE_ID == newState.getID()) {
	    setPen(root, newState.getPen(), newState.getX(), newState.getY());
	    setPosition(newState.getAngle() + 90, newState.getX(), newState.getY());
	    show(newState.getShowing());
	    //TODO fix
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

    private void setPosition(double angle, double x, double y) {
<<<<<<< HEAD
	if(x < zeroX - WIDTH || x > zeroX + WIDTH || y < zeroY - HEIGHT || y > zeroY + HEIGHT ) {
	    show(false);
	    return;
	}
	if(angle != image.getRotate()) {
	    ANIMATION =  MOVABLE.rotate(image, angle - image.getRotate());
	    ANIMATION.play();
	    ANGLE = angle;
=======
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
			Animation animation = MOVABLE.move(image, x + zeroX, y + zeroY); 
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
>>>>>>> b33cacfe4df69f42c9d4c048ebc802f08a2b4195
	}
    
    public void handleAnimation() {
		image.toFront();
		if(!animationQueue.isEmpty()) {
			if(ANIMATION.getStatus()==Status.STOPPED) {
				ANIMATION = animationQueue.poll();
				ANIMATION.play();
				if(instQueue.size()>0) {
					nextState = instQueue.poll();
					image.setX(nextState[0]);
					image.setY(nextState[1]);
				}
				while(ANIMATION.getStatus()==Status.STOPPED) {
					int i = 1;
				}
			}
		}
	}


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
     * Update states for one command
     * 
     * @param states: All changes in state
     */
    public void updateStates(List<State> states, Group root) {
	boolean useInitial = paletteInput(states.get(0));
	for(State state : states) {
	    clear(state.getClear(), root);
	    if(useInitial) {
		this.updateState(state, root);
	    }
	    useInitial = true;
	}
    }
    
    private boolean paletteInput(State s) {
	if(s.getPalette() > 0 || s.getBackground() > 0 || s.getPencolor() > 0 || s.getPensize() > 0 || s.getShape() != 0) {
	    return true;
	}
	return false;
    }

    public void setPenColor(String color) {
	pen.setColor(color);
    }

    public void show(boolean show) {
	if(!show) image.setImage(null);
	else image.setImage(new Image(getClass().getClassLoader().getResourceAsStream(IMAGE)));
    }

    public boolean inBounds(double x, double y) {
	if(x<=zX+WIDTH/2 && x>=zX-WIDTH/2 && y<=zY+HEIGHT/2 && y>=zY-HEIGHT/2) {
	    return true;
	}
	return false;
    }

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

    public int xPos() {
	return (int) (image.getX() - zX);
    }

    public int yPos() {
	return (int) (zY - image.getY());
    }

    public boolean getActive() {
	return isActive;
    }

    public void setActive(boolean next) {
	isActive = next;
	if(isActive) {
	    image.setOpacity(1.0);
	}
	else {
	    image.setOpacity(0.5);
	}
    }

    public TurtlePen getPen() {
	return pen;
    }

    public boolean toggleTurtle(double x, double y) {
	x = x-20;   
	y = y-211;
	if(Math.abs(image.getX()-x)<15 && Math.abs(image.getY()-y)<15) {
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
    
    public void pauseAnimation() {
	ANIMATION.pause();
    }

    public void playAnimation() {
	ANIMATION.play();
    }
    
    public PaletteMap getPaletteMap(){
	return paletteMap;
    }
    
    public void setSpeed(double speed) {
    	MOVABLE.setMoveSpeed(speed);
    }

    public int getID() {
	return TURTLE_ID;
    }
}
