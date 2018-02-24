package view;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;

public class Gobject {
    double currentWidth;
    double currentHeight;
    double currentxloc;
    double currentyloc;
    Control control;
    double screenWidth;
    double screenHeight;

    /**
     * Constructor for the GUI object being created.
     * 
     * @param xloc
     * @param yloc
     * @param width
     * @param height
     * @param type
     */
    public Gobject(double xloc, double yloc, double width, double height, int type) {
	currentWidth = width;
	currentHeight = height;
	currentxloc = xloc;
	currentyloc = yloc;
	double[] screenBounds = getScreenBounds();
	screenWidth = screenBounds[0];
	screenHeight = screenBounds[1];
	control = makeObject(type);		
    }
    
    
    /**
     * Method to initialize the desired object
     * 
     * @param xloc
     * @param yloc
     * @param width
     * @param height
     * @param type 0:label, 1:button, 2:ComboBox, 3: TextField
     */
    private Control makeObject(int type) {
	Control current = setObject(type);
	current.setLayoutX(currentxloc);
	current.setLayoutY(currentyloc);
	current.setPrefSize(currentWidth,currentHeight);
	return current;
    }

    /**
     * Method used to update the relative sizes of the components
     * of the GUI on the screen.
     * 
     * @param current
     */
    public void updateObject(Control current) {
	double[] screenBounds = getScreenBounds();
	double screenWidth2 = screenBounds[0];
	double screenHeight2 = screenBounds[1];
	if(screenWidth!=screenWidth2 || screenHeight!=screenHeight2) {
	    double relWidth = currentWidth/screenWidth;
	    double relHeight = currentHeight/screenHeight;
	    double relX = currentxloc/screenWidth;
	    double relY = currentyloc/screenHeight;
	    currentWidth = relWidth*screenWidth2;
	    currentHeight = relHeight*screenHeight2;
	    currentxloc = relX*screenWidth2;
	    currentyloc = relY*screenHeight2;
	    control.setLayoutX(currentxloc);
	    control.setLayoutY(currentyloc);
	    control.setPrefSize(currentWidth, currentHeight);			
	    screenWidth = screenWidth2;
	    screenHeight = screenHeight2;
	}
    }
    
    
    /**
     * Method used to determine the desired type of GUI object
     * and then initialize said object.
     * 
     * @param type
     * @return
     */
    private Control setObject(int type) {
	if(type==0) {
	    return new Label();
	}
	else if(type==1) {
	    return new Button();
	}
	else if(type==2) {
	    return new ComboBox<String>();
	}
	else if(type==3) {
	    return new TextField();
	}
	else {
	    return null;
	}

    }
    
    /**
     * Method called to get screen bounds.
     * 
     * @return
     */
    private double[] getScreenBounds() {
	Screen screen = Screen.getPrimary();
	Rectangle2D screenBounds = screen.getBounds();
	double w = screenBounds.getWidth();
	double h = screenBounds.getHeight();
	double[] ret = {w,h};
	return ret;
    }
}
