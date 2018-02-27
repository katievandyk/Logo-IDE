package view.turtle;

import java.util.LinkedList;

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
    private LinkedList<Line> lines;
    
    public TurtlePen(Color color) {
	lines = new LinkedList<>(); 
	COLOR = color;
	
    }
    
    public void addLine(double x2, double y2) {
	Line previous = lines.get(lines.size()-1);
	double x1 = previous.getEndX();
	double y1 = previous.getEndY();
	Line l = newLine(x1, y1, x2, y2);
	lines.add(l);
    }
    
    public Line newLine(double x1, double y1, double x2, double y2) {
	Line l = new Line();
	l.setStartX(x1); 
	l.setStartY(y1); 
	l.setEndX(x2); 
	l.setEndY(y2);
	l.setFill(COLOR);
	return l;
    }
    
    public LinkedList<Line> getLines(){
	return lines;
    }
    
    public void reset() {
	lines.clear();
    }

}
