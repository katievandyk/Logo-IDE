package view.factory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * 
 * @author Brandon Dalla Rosa
 * 
 */
public class ObjectFactory {
	double currentWidth;
	double currentHeight;
	Region control;
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
	public ObjectFactory(double width, double height, int type) {
		currentWidth = width;
		currentHeight = height;
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
	 * @param type 0:label, 1:button, 2:ComboBox, 3: TextField, 4: Pane, 5: TextArea
	 */
	private Region makeObject(int type) {
		Region current = setObject(type);
		current.setPrefSize(currentWidth,currentHeight);
		return current;
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
		else if(type==5) {
			return new TextArea();
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
