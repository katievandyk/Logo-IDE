package model.state;

/**
 * Holds attributes of turtle object
 * 
 * @author Katherine Van Dyk
 *
 */
public class State {
    private double xLocation;
    private double yLocation;
    private boolean penUp;
    private double headAngle;
    private boolean showing;

    public State(State s) {
    	this.xLocation = s.xLocation;
    	this.yLocation = s.yLocation;
    	this.penUp = s.penUp;
    	this.headAngle = s.headAngle;
    	this.showing = s.showing;
    }
    
    public State(double xi, double yi, double angle, boolean pen, boolean show) {
    	xLocation = xi;
    	yLocation = yi;
    	penUp = pen;
    	headAngle = angle;
    	showing = show;
    }
    
    public double getX() {
    	return this.xLocation;
    }
    
    public double getY() {
    	return this.yLocation;
    }
    
    public boolean getPen() {
    	return this.penUp;
    }
    
    public double getAngle() {
    	return this.headAngle;
    }
    
    public void setPen(boolean penState) {
	this.penUp = penState;
    }
    
    public void setShowing(boolean showState) {
    	this.showing = showState;
    }
    
    public void setAngle(double angle) {
	this.headAngle = angle;
    }
    
    public void move(double magnitude) {
    	xLocation += Math.cos(Math.toRadians(headAngle)) * magnitude;
    	yLocation += Math.sin(Math.toRadians(headAngle)) * magnitude;
    }
    
    public String toString() {
		return "<x="+xLocation+", y="+yLocation+", angle="+headAngle+", penUp="+penUp+", showing="+showing+">";
    }
    
    public void setXY(double x, double y) {
    	xLocation = x;
    	yLocation = y;
    }

}
