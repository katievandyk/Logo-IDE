package view.turtle;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Encapsulates line drawing for turtle object
 * 
 * @author Katherine Van Dyk
 *
 */
public class TurtlePen {

    private Color COLOR;
    private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");
    private int TURTLE_WIDTH;
    private int TURTLE_HEIGHT;
    private double previousX;
    private double previousY;
    private int thickness;
    private ArrayList<Line> lines;

    public TurtlePen(Color color, int turtleWidth, int turtleHeight) {
	COLOR = color;
	thickness = 1;
	TURTLE_WIDTH = turtleWidth;
	TURTLE_HEIGHT = turtleHeight;
	lines = new ArrayList<Line>();

    }

    public void setLocation(double x, double y) {
	previousX = x + TURTLE_WIDTH / 2;
	previousY = y + TURTLE_HEIGHT / 2;
    }

    public void setColor(String color) {
	COLOR = Color.web(COLOR_RESOURCES.getString(color));
    }
    
    public Color getColor() {
	return COLOR;
    }
    
    public void setThickness(String t) {
	thickness = Integer.parseInt(t);
    }

    public Line addLine(double x2, double y2) {
	Line l = new Line();
	l.setStartX(previousX); 
	l.setStartY(previousY); 
	l.setEndX(x2 + TURTLE_WIDTH/2); 
	l.setEndY(y2 + TURTLE_HEIGHT/2); 
	l.setStroke(COLOR);
	l.setStrokeWidth(thickness);
	lines.add(l);
	setLocation(x2, y2);
	return l;
    }


}
