package view.turtle;

import java.util.Map;

import javafx.scene.paint.Color;
import model.state.State;
import view.panels.TurtlePanel;
import view.save.PaletteMap;

/**
 * Responsible for calculating values used in determining turtle's display and handling
 * palette commands
 * 
 * @author Katherine Van Dyk
 *
 */
public class TurtleFactory {

    private double HEIGHT;
    private double WIDTH;
    private PaletteMap paletteMap;
    private TurtlePen pen;
    private Turtle turtle;
    private TurtlePanel turtlePanel;
    private final int X_OFFSET = 20;
    private final int Y_OFFSET = 105; 

    /**
     * Constructor for turtle factory that takes in palette parameters
     * 
     * @param tp
     * @param t
     * @param pen
     */
    public TurtleFactory(TurtlePanel tp, Turtle t, TurtlePen pen) {
	this.paletteMap = new PaletteMap();
	this.pen = pen;
	this.turtle = t;
	this.HEIGHT = tp.height();
	this.WIDTH = tp.width();
	this.turtlePanel = tp;
    }
    /**
     * Sets palette attributes from palette comand
     * 
     * @param palette: Index of palette to be changed
     * @param rgb: Color of palette at index to be changed
     * @param background: Index of background of turtle panel
     * @param pencolor: Index of new pen color
     * @param pensize: Index of new pen size
     * @param shape: Index of new image
     */
    public void setPalettes(int palette, int[] rgb, int background, int pencolor, int pensize, int shape) {
	Map<String, Map<String, String>> map = paletteMap.getMap();
	if(map.containsKey(Integer.toString(palette))) {
	    Color newColor = Color.rgb(rgb[0], rgb[1], rgb[2]);
	    paletteMap.editMap(Integer.toString(palette), newColor );
	}
	else if(map.containsKey(Integer.toString(background))) {
	    turtlePanel.changeBack(paletteMap.getBackgroundColor(Integer.toString(background)));
	}
	else if(map.containsKey(Integer.toString(pencolor))) {
	    pen.setColor(paletteMap.getPenColor(Integer.toString(pencolor)));
	}
	else if(map.containsKey(Integer.toString(pensize)) && palette != 0) {
	    pen.setThickness(Integer.toString(paletteMap.getPenThickness(Integer.toString(pensize))));
	}
	else if(map.containsKey(Integer.toString(shape))) {
	    turtle.makeImage(paletteMap.getShape(Integer.toString(shape)));
	}
    }

    /**
     * @return true if palette input based on current state @param s
     */
    public boolean paletteInput(State s) {
	if(s.getPalette() > 0 || s.getBackground() > 0 || s.getPencolor() > 0 || s.getPensize() > 0 || s.getShape() != 0) {
	    return true;
	}
	return false;
    }

    /**
     * @return Palette map for display
     */
    public PaletteMap getPaletteMap() {
	return paletteMap;
    }

    /**
     * Determine if the new turtle location is in the desired boundaries.
     * 
     * @param x: New x-value.
     * @param y: New y-value.
     * @return If in boundaries.
     */
    public boolean inBounds(double x, double y, double zX, double zY) {
	if(x<=zX+WIDTH/2 && x>=zX-WIDTH/2 && y<=zY+HEIGHT/2 && y>=zY-HEIGHT/2) {
	    return true;
	}
	return false;
    }

    /**
     * Normalizes the angle to a simple readable format.
     * 
     * @return Normalized angle.
     */
    public double normalizeAngle(double angle) {
	while(angle > 360) {
	    angle -= 360;
	}
	while(angle <= 0) {
	    angle += 360;
	}
	return angle;
    }

    /**
     * Returns if turtle is able to toggle based on clicking coordinates (@param clickX and @param clickY),
     * and relative turtle coordinates, based on turtle's relative ( @param pastX and @param pastY) and 
     * absolute (taking into account @param zeroX and @param zeroY) positions
     */
    public boolean canToggle(double clickX, double clickY, double pastX, double pastY, double zeroX, double zeroY) {
	clickX -= X_OFFSET;   
	clickY -= Y_OFFSET;
	return (Math.abs(pastX+zeroX-clickX)<15 && Math.abs(pastY+zeroY-clickY) < 15);
    }

}
