package view.turtle;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Movable class used to determine turtle animation path
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rossa
 *
 */
public class Movable {
    private double X_OFFSET;
    private double Y_OFFSET;
    private double moveSpeed;
    private double turnSpeed;

    /**
     * Constructor that takes in turtle width and height for animation offset
     * 
     * @param turtleWidth
     * @param turtleHeight
     */
    public Movable(double turtleWidth, double turtleHeight) {
	X_OFFSET = turtleWidth/2;
	Y_OFFSET = turtleHeight/2;
    }

    /**
     * Animation that moves turtle based on previous coordinates @param x1 and @param y1
     * and new turte coordinates @param x2 and @param y2
     *
     * @return Animation that moves turtle along pen path
     */
    public Animation move(ImageView agent,double x1, double y1, double x2, double y2) {
	Path path = new Path();
	path.getElements().addAll(new MoveTo(x1 + X_OFFSET, y1 + Y_OFFSET), new LineTo(x2 + X_OFFSET, y2 + Y_OFFSET));
	PathTransition pt = new PathTransition(Duration.millis(moveSpeed), path, agent);
	return new SequentialTransition(agent, pt);
    } 

    /**
     * Animation that rotates turtle based on angle difference, @param angle
     * 
     * @return Animation that rotates pen
     */
    public Animation rotate(ImageView agent, double angle) {
	RotateTransition rt = new RotateTransition(Duration.millis(turnSpeed));
	rt.setByAngle(angle);
	return new SequentialTransition(agent, rt);
    }
    
    /**
     * Sets animation speed to @param mSpeed
     */
    public void setMoveSpeed(double mSpeed) {
    	moveSpeed = 10100 - 1000*mSpeed;
    	turnSpeed = moveSpeed/2;
    }

}

