package model.state;

import view.turtle.Turtle;

/**
 * Holds attributes of turtle object
 * 
 * @author Katherine Van Dyk
 *
 */
public class State {
    private double xLocation;
    private double yLocation;
    private boolean penDown;
    private double headAngle;
    private boolean showing;

    public State() {
		this.xLocation = 0;
		this.yLocation = 0;
		this.penDown = true;
		this.showing = true;
		this.headAngle = 0;
    }
    
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
    	penDown = pen;
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
    	return this.penDown;
    }
    
    public boolean getShowing() {
    	return this.showing;
    }
    
    public double getAngle() {
    	return this.headAngle;
    }
    
    public void setPen(boolean penState) {
	this.penDown = penState;
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
