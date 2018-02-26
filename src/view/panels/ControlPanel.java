package view.panels;


import javafx.scene.Group;
import javafx.scene.control.TextField;
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
	private String currentInput;
	private Gobject commandLine;
	
    public ControlPanel(double width, double height) {
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

}
