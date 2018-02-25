package view.panels;


import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Gobject;

/**
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @date 2/24/18
 * 
 * Class to generate the cell panel to be displayed on the center of the simulation screen.
 * The cell panel child nodes are held in a VBox object.
 */
public class ControlPanel {
    //private Pane CONTROL_PANEL;
	private String currentInput;
	private Gobject commandLine;
	
    public ControlPanel(double width, double height) {
    	//CONTROL_PANEL = new Pane();
    	currentInput = "";
    	commandLine = new Gobject(10,height-100,400,40,3);
    	TextField text = (TextField)commandLine.getObject();
    	text.setOnAction(click->{currentInput = text.getText(); text.setText("");});
    	
    }
    public void addNode(Group root) {
    	root.getChildren().add(commandLine.getObject());
    }

    public String getInput() {
    	return currentInput;
    }
    
    public void update(Stage stage) {
    	commandLine.updateObject(stage);
    }
    
//  public Parent construct() {
//	//Will implement VBox later
//	//VBox ctrl = new VBox(20);
//	//CONTROL_PANEL.getChildren().add(ctrl);
//	//return CONTROL_PANEL;
//}
}
