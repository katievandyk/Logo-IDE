package view.turtle;

import view.panels.TurtlePanel;

public class TurtleFactory {

    private final int TURTLE_HEIGHT = 40;
    private final int TURTLE_WIDTH = 40;
    private double zeroX;
    private double zeroY;
    private double HEIGHT;
    private double WIDTH;
    private double zX;
    private double zY;
    private double pastX;
    private double pastY;
    private double ANGLE;

    public TurtleFactory(TurtlePanel tp) {
	this.zeroX = (tp.width() - TURTLE_WIDTH) / 2;
	this.zeroY = (tp.height() - TURTLE_HEIGHT) / 2; 
	this.HEIGHT = tp.height();
	this.WIDTH = tp.width();
	this.zX = zeroX;
	this.zY = zeroY;
    }

    public int getX(double x) {
	return (int) (x - zX);
    }
    
    public int getY(double y) {
	return (int) (y - zY);
    }

    public double zeroX() {
	return zeroX;
    }

    public double zeroY() {
	return zeroY;
    }

    public boolean changeAngle(double angle, double x, double y) {
	return (angle != ANGLE && pastX==x && pastY==y);
    }

    public boolean canMove(double x, double y) {
	return (pastX!=x || pastY!=y);
    }


    public double moveX(double x) {
	return zeroX + x;
    }

    public double moveY(double y) {
	return zeroY + y;
    }

    public void setPastValues(double angle, double x, double y) {	
	ANGLE = angle;
	pastX = x;
	pastY = y;
    }

    public boolean inBounds(double x, double y) {
	if(x<=zX+WIDTH/2 && x>=zX-WIDTH/2 && y<=zY+HEIGHT/2 && y>=zY-HEIGHT/2) {
	    return true;
	}
	return false;
    }

    public void resetZeroes(double x, double y) {
	if(!inBounds(zeroX + x, zeroY + y)) {
	    zeroX = zX - x;
	    zeroY = zY - y;
	}
    }

    public void setPenLocation(TurtlePen p) {
	p.setLocation(zX, zY);
    }
    
    public double getAngle() {
	while(ANGLE > 360) {
	    ANGLE -= 360;
	}
	while(ANGLE <= 0) {
	    ANGLE += 360;
	}
	return ANGLE;
    }
    
    public boolean canToggle(double imX, double imY, double x, double y) {
	x = x-20;   
	y = y-211;
	return (Math.abs(imX-x)<15 && Math.abs(imY-y)<15);
    }

}
