package view.panels;

/**
 * Scales dimensions based on window sizing
 * 
 * @author Brandon Dalla Rossa
 * @author Katherine Van Dyk
 *
 */
public class ScalingFactory {

    private double screenWidth;
    private double screenHeight;

    public double changeWidth(double width) {
	if(screenWidth!= width) {
	    double relWidth = width/screenWidth;
	    width = relWidth*width;
	    screenWidth = width;
	    return width;
	} 
	else {
	    return screenWidth;
	}
    }

    public double changeHeight(double height) {
	if(screenHeight!= height) {
	    double relHeight = height/screenHeight;
	    height = relHeight*height;
	    screenHeight = height;  
	    return height;
	} 
	else {
	    return screenHeight;
	}
    }

    public double changeXLocation(double currentX, double width) {
	if(screenWidth!= width) {
	    double relX = currentX/screenWidth;
	    currentX = relX * width;
	    return currentX;
	} 
	else {
	    return screenWidth;
	}
    }
    
    public double changeYLocation(double currentY, double height) {
	if(screenHeight!= height) {
	    double relY = currentY/screenWidth;
	    currentY = relY * height;
	    return currentY;
	} 
	else {
	    return screenHeight;
	}
    }

}
