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

public class Movable {
    private double X_OFFSET;
    private double Y_OFFSET;
    private double moveSpeed;
    private double turnSpeed;

    public Movable(double turtleWidth, double turtleHeight) {
	X_OFFSET = turtleWidth/2;
	Y_OFFSET = turtleHeight/2;
    }

    public Animation move(ImageView agent,double x1, double y1, double x2, double y2) {
	Path path = new Path();
	path.getElements().addAll(new MoveTo(x1 + X_OFFSET, y1 + Y_OFFSET), new LineTo(x2 + X_OFFSET, y2 + Y_OFFSET));
	PathTransition pt = new PathTransition(Duration.millis(moveSpeed), path, agent);
	return new SequentialTransition(agent, pt);
    } 

    public Animation rotate(ImageView agent, double angle) {
	RotateTransition rt = new RotateTransition(Duration.millis(turnSpeed));
	rt.setByAngle(angle);
	return new SequentialTransition(agent, rt);
    }
    
    public void setMoveSpeed(double mSpeed) {
    	moveSpeed = 10500 - 1000*mSpeed;
    	turnSpeed = moveSpeed/2;
    }

}

