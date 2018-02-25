package view;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * 
 * @author Brandon Dalla Rosa
 * 
 */
public class Gobject {
    double currentWidth;
    double currentHeight;
    double currentxloc;
    double currentyloc;
    Region control;
    double screenWidth;
    double screenHeight;
    boolean init;

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
	control = makeObject(type);	
	init = true;
    }
    
    
    /**
     * Method to initialize the desired object
     * 
     * @param xloc
     * @param yloc
     * @param width
     * @param height
     * @param type 0:label, 1:button, 2:ComboBox, 3: TextField, 4: Pane
     */
    private Region makeObject(int type) {
    	Region current = setObject(type);
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
    public void updateObject(Stage current) {
    	if(init) {
    		screenWidth = current.getWidth();
        	screenHeight = current.getHeight();
    		init = false;
    	}
    	double screenWidth2 = current.getWidth();
    	double screenHeight2 = current.getHeight();
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
    private Region setObject(int type) {
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
	else if(type==4) {
	    return new Pane();
	}
	else {
	    return null;
	}

    }
    
    /**
     * Method called to return the region of the gobject.
     * 
     */
    public Region getObject() {
    	return control;
    }
}
