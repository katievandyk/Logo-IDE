package view.panels;


import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class to generate the cell panel to be displayed on the center of the simulation screen.
 * The cell panel child nodes are held in a VBox object.
 */
public class ControlPanel {
    private Pane CONTROL_PANEL;

    public ControlPanel() {
	CONTROL_PANEL = new Pane();
    }

    public Parent construct() {
	VBox ctrl = new VBox(20);
	CONTROL_PANEL.getChildren().add(ctrl);
	return CONTROL_PANEL;
    }

}
