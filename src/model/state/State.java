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
    private boolean penUp;
    private double headAngle;

    public State(Turtle t) {
		this.xLocation = t.xLocation();
		this.yLocation = t.xLocation();
		this.penUp = t.penUp();
    }
    
    public State(State s) {
    	this.xLocation = s.xLocation;
    	this.yLocation = s.yLocation;
    	this.penUp = s.penUp;
    	this.headAngle = s.headAngle;
    }
    
    public State(double xi, double yi, double angle, boolean pen) {
    	xLocation = xi;
    	yLocation = yi;
    	penUp = pen;
    	headAngle = angle;
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
    
    public void setX(double x) {
	this.xLocation = x;
    }
    
    public void setY(double y) {
	this.yLocation = y;
    }
    
    public void setPen(boolean penState) {
	this.penUp = penState;
    }
    
    public void setAngle(double angle) {
	this.headAngle = angle;
    }
    
    public void move(double magnitude) {
    	xLocation += Math.cos(Math.toRadians(headAngle)) * magnitude;
    	yLocation += Math.sin(Math.toRadians(headAngle)) * magnitude;
    }
    
    public String toString() {
		return "<x="+xLocation+", y="+yLocation+", angle="+headAngle+", penUp="+penUp+">";
    }
    
    public void setXY(double x, double y) {
    	xLocation = x;
    	yLocation = y;
    }

}
