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
	private boolean isActive = true;
	private int TURTLE_ID;
	
	/**
	 * Constructor for turtle object
	 * 
	 * @param img: Image for turtle object display
	 * @param screenHeight: Height of turtle panel
	 * @param screenWidth: Width of turtle panel
	 */
	public Turtle(String img, double height, double width, int id) {
		this.pen = new TurtlePen(Color.BLACK, TURTLE_WIDTH, TURTLE_HEIGHT);
		this.penDown = false;
		this.HEIGHT = height;
		this.WIDTH = width;
		this.zeroX = (width - TURTLE_WIDTH) / 2;
		this.zeroY = (height - TURTLE_HEIGHT) / 2; 
		this.image = makeImage(img);
		IMAGE = img;
		zX = zeroX;
		zY = zeroY;
		TURTLE_ID = id;
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
		if(TURTLE_ID == newState.getID()) {
			setPen(root, newState.getPen(), newState.getX(), newState.getY());
			setPosition(newState.getAngle(), newState.getX(), newState.getY());
			show(newState.getShowing());
			clear(newState.getClear(), root);
		}
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

	private void setPen(Group root, boolean newPenDown, double x, double y) {
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
			root.getChildren().add(line);
		}
		penDown = newPenDown;
	}


	/**
	 * Update states for one command
	 * 
	 * @param states: All changes in state
	 */
	public void updateStates(List<State> states, Group rOOT) {
		for(State state : states) {
			this.updateState(state, rOOT);
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
			image.setImage(null);
		}
		else {
			image.setImage(new Image(getClass().getClassLoader().getResourceAsStream(IMAGE)));
		}
	}

	public boolean inBounds(double x, double y) {
		if(x<=zX+WIDTH/2 && x>=zX-WIDTH/2 && y<=zY+HEIGHT/2+20 && y>=zY-HEIGHT/2+20) {
			return true;
		}
		return false;
	}

	public void clear(boolean clr, Group root) {
		if(clr) {
			image.setX(zeroX);
			image.setX(zeroY);
			image.setRotate(0);
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
	
	public TurtlePen getPen() {
	    return pen;
	}
	
	public void toggleTurtle(double x, double y) {
		System.out.println("x "+x);
		System.out.println("y "+y);
		System.out.println("imagex "+image.getX());
		System.out.println("imagey "+image.getY());
		//TODO fix this offset
		x = x-19;   
		y = y-215;
		if(Math.abs(image.getX()-x)<15 && Math.abs(image.getY()-y)<15) {
			if(isActive) {
				isActive = false;
				image.setOpacity(0.5);
			}
			else {
				isActive = true;
				image.setOpacity(1.0);
			}
		}
	}

}
