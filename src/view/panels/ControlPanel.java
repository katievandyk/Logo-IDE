package view.panels;


import javafx.scene.Group;
import javafx.scene.control.ComboBox;
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
	private Gobject backPicker;
	private Gobject penColor;
	
    public ControlPanel(double width, double height, Group root) {
    	currentInput = "";
    	
    	commandLine = new Gobject(20,height-90,width*.75,60,3);
    	TextField text = (TextField)commandLine.getObject();
    	text.setOnAction(click->{currentInput = text.getText(); text.setText("");});
    	root.getChildren().add(text);
    	
    	backPicker = new Gobject(20,5,width/5,40,2);
    	ComboBox colors1 = (ComboBox)backPicker.getObject();
    	colors1.setPromptText("BackGround");
    	root.getChildren().add(colors1);
    	
    	penColor = new Gobject(30+width/5,5,width/5,40,2);
    	ComboBox colors2 = (ComboBox)penColor.getObject();
    	colors2.setPromptText("Pen Color");
    	root.getChildren().add(colors2);
    }

    public String getInput() {
    	return currentInput;
    }
    
    public void update(Stage stage) {
    	commandLine.updateObject(stage);
    	backPicker.updateObject(stage);
    	penColor.updateObject(stage);
    }

}
