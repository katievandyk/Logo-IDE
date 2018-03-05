package view.screens;

import java.util.ArrayList;
import java.util.List;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.ViewController;
import view.panels.ButtonPanel;
import view.panels.CommandPanel;
import view.panels.HistoryPanel;
import view.panels.SettingsPanel;
import view.panels.TurtlePanel;
import view.turtle.Turtle;
import model.dictionaries.*;
import model.state.State;

/**
 * 
 * @author Brandon Dalla Rosa
 * @author Katherine Van Dyk
 * @date 2/24/2018
 *
 * Creates the root object to be placed in the simulation Scene. 
 * 
 */
public class MainScreen extends ViewController  {
    private final String TURTLE_IMAGE = "resources/images/defaultTurtle.png";
    private ArrayList<Turtle> TURTLE = new ArrayList<Turtle>();
    private TurtlePanel TURTLE_PANEL;
    private CommandPanel COMMAND_PANEL;
    private SettingsPanel SETTINGS_PANEL;
    private ButtonPanel BUTTON_PANEL;
    private HistoryPanel HISTORY_PANEL;
    protected Group ROOT;
    protected BorderPane borderPane;

    public MainScreen(int screenHeight, int screenWidth, Controller c, VariableDictionary variables, CommandDictionary commands) {
    	borderPane = new BorderPane();
    	ROOT = new Group();
	HISTORY_PANEL = new HistoryPanel(commands, variables);
	TURTLE_PANEL = new TurtlePanel(700, 420);
	Turtle toAdd = new Turtle(TURTLE_IMAGE, 420, 700);
	TURTLE.add(toAdd);
	SETTINGS_PANEL = new SettingsPanel(c,TURTLE_PANEL, TURTLE.get(0));
	COMMAND_PANEL = new CommandPanel(c, HISTORY_PANEL);
	BUTTON_PANEL = new ButtonPanel();

	initBorderPane();
    }

    private void initBorderPane() {
	//borderPane.setLeft(TURTLE_PANEL.construct());
	VBox panelStuff = new VBox(12, TURTLE_PANEL.construct(), COMMAND_PANEL.construct());
	borderPane.setLeft(panelStuff);
	VBox rightStuff = new VBox(12, HISTORY_PANEL.construct(), SETTINGS_PANEL.construct(), BUTTON_PANEL.construct());
	borderPane.setCenter(rightStuff);
	
	for(Node n : borderPane.getChildren()) {
		BorderPane.setMargin(n, new Insets(0,12,12,12));
	}
	
	
	ROOT.getChildren().add(borderPane);
	ROOT.getChildren().add(TURTLE.get(0).display());

    }
    
    public Group getRoot() {
    	return ROOT;
    }
    
    public void makeTurtle() {
    	Turtle toAdd = new Turtle(TURTLE_IMAGE, 420, 700);
    	TURTLE.add(toAdd);
    }

    public void updateTurtle(List<State> states) {
    	for(Turtle current : TURTLE) {
    		if(current.getActive()) {
        		current.updateStates(states, ROOT);
    		}
    	}
    }
    
    public void toggleTurtle(double x, double y) {
    	for(Turtle current : TURTLE) {
    		current.toggleTurtle(x, y);
    	}
    }
}
