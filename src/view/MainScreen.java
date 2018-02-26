package view;

import java.util.List;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.ControlPanel;
import view.panels.TurtlePanel;
import model.state.State;


/**
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @date 2/24/2018
 *
 * Creates the root object to be placed in the simulation Scene. 
 * 
 */
public class MainScreen extends ViewController  {
    private TurtlePanel TURTLE_PANEL;
    private ControlPanel CONTROL_PANEL;
    private Stage STAGE;
    protected Group ROOT;


    // need to save the Engine to call functions on button clicks
    public MainScreen(int screenHeight, int screenWidth, Stage stage, Controller c) {
    	ROOT = new Group();
        TURTLE_PANEL = new TurtlePanel(screenWidth* 3/4-50, screenHeight* 3/4);
        CONTROL_PANEL = new ControlPanel(screenWidth, screenHeight, ROOT, c);
        STAGE = stage;
        makeRoot();
    }

    public void makeRoot() {  	
    	TURTLE_PANEL.construct(ROOT);
    }
    
    
    public Parent getRoot() {
        return ROOT;
    }


    /**
     * Change properties of displayed items to reflect animation properties
     * 
     * @param elapsedTime: time since last animation update
     */
    public void step (double elapsedTime) {
    	TURTLE_PANEL.update(STAGE);
        CONTROL_PANEL.update(STAGE);
    }
    
    public void updateTurtle(List<State> states) {
    	TURTLE_PANEL.updateTurtle(states);
    }
    
    
}
