package model.state;

/**
 * Holds attributes of a turtle object
 * 
 * @author Katherine Van Dyk
 * @author Martin Muenster
 *
 */
public class State {

	private double xLocation;
    private double yLocation;
    private boolean penDown;
    private double headAngle;
    private boolean showing;
    private boolean clear;
    private int id;
    private int background;
    private int pencolor;
    private int pensize;
    private int shape;
    private int palette;
    private int[] paletteRGB;

    public State() {
		this.xLocation = 0;
		this.yLocation = 0;
		this.penDown = true;
		this.showing = true;
		this.headAngle = -90;
		this.clear = false;
		this.background = 0;
		this.pencolor = 0;
		this.pensize = 1;
		this.shape = 0;
		this.palette = 0;
		this.paletteRGB = new int[] {0,0,0};
		this.id = 1;
    }
    
    public State(int id) {
    	this();
		this.id = id;
    }

    public State(State s) {
		this.xLocation = s.xLocation;
		this.yLocation = s.yLocation;
		this.penDown = s.penDown;
		this.headAngle = s.headAngle;
		this.showing = s.showing;
		this.clear = false;
		this.background = s.background;
		this.pencolor = s.pencolor;
		this.pensize = s.pensize;
		this.shape = s.shape;
		this.palette = s.palette;
		this.paletteRGB = s.paletteRGB;
		this.id = s.id;
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
    
    public boolean getClear() {
    	return this.clear;
    }

    public double getAngle() {
    	return this.headAngle;
    }
    
    public int getID() {
    	return id;
    }
    
    public void setID(int id) {
    	this.id = id;
    }

    public void setPen(boolean penState) {
    	penDown = penState;
    }

    public void setShowing(boolean showState) {
    	showing = showState;
    }
    
    public void clearScreen() {
    	clear = true;
    }
    
    public double setAngle(double angle) {
    	double change = angle-headAngle;
    	headAngle = angle;
    //	normalizeAngle();
		return change;
    }

    public void addAngle(double angle) {
		headAngle += angle;
    }

    public void move(double magnitude) {
		xLocation += Math.cos(Math.toRadians(headAngle)) * magnitude;
		yLocation += Math.sin(Math.toRadians(headAngle)) * magnitude;
    }

    public String toString() {
    	return "<x="+xLocation+", y="+yLocation+", angle="+headAngle+", penUp="+penDown+", showing="+showing+", clear="+clear+">";
    }

    public void setXY(double x, double y) {
		xLocation = x;
		yLocation = y;
    }
    
    public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public int getPencolor() {
		return pencolor;
	}

	public void setPencolor(int pencolor) {
		this.pencolor = pencolor;
	}

	public int getPensize() {
		return pensize;
	}

	public void setPensize(int pensize) {
		this.pensize = pensize;
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int getPalette() {
		return palette;
	}

	public void setPalette(int palette) {
		this.palette = palette;
	}
	
	public int[] getPaletteRGB() {
		return paletteRGB;
	}

	public void setPaletteRGB(int[] rgb) {
		this.paletteRGB = rgb;
	}
}
