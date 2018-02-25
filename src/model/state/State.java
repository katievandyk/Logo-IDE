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

}
