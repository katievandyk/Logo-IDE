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
    
    public Movable(double turtleWidth, double turtleHeight) {
	 X_OFFSET = turtleWidth/2;
	 Y_OFFSET = turtleHeight/2;
    }
    
    public Animation move(ImageView agent, double x1, double y1, double x2, double y2) {
        // create something to follow
        Path path = new Path();
	System.out.println("X: " + x1 + "Y: " + y1);
	System.out.println("newX: " + x2 + "newY: " + y2);
        path.getElements().addAll(new MoveTo(x2 + X_OFFSET, y2 + Y_OFFSET), new LineTo(x2 + X_OFFSET, y2 + Y_OFFSET));
        // create an animation where the shape follows a path
        PathTransition pt = new PathTransition(Duration.millis(4000), path, agent);
        // put them together in order
        return new SequentialTransition(agent, pt);
    } 
  

    
    public Animation rotate(ImageView agent, double angle) {
        RotateTransition rt = new RotateTransition(Duration.seconds(3));
        rt.setByAngle(angle);
        // put them together in order
        return new SequentialTransition(agent, rt);
    }
    
}

