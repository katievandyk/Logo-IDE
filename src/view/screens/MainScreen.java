package view.screens;

import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import view.ViewController;
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
    private Turtle TURTLE;
    private TurtlePanel TURTLE_PANEL;
    private CommandPanel COMMAND_PANEL;
    private SettingsPanel SETTINGS_PANEL;
    private HistoryPanel HISTORY_PANEL;
    protected Group ROOT;
    protected BorderPane borderPane;

    public MainScreen(int screenHeight, int screenWidth, Controller c, VariableDictionary variables, CommandDictionary commands) {
    	borderPane = new BorderPane();
    	ROOT = new Group();
	HISTORY_PANEL = new HistoryPanel(commands, variables);
	TURTLE_PANEL = new TurtlePanel(700, 420);
	//TODO turtle is made inside turtle panel
	TURTLE = new Turtle(TURTLE_IMAGE, 420, 700);
	SETTINGS_PANEL = new SettingsPanel(c,TURTLE_PANEL, TURTLE);
	COMMAND_PANEL = new CommandPanel(c, HISTORY_PANEL);

	initBorderPane();
    }

    private void initBorderPane() {
	borderPane.setLeft(TURTLE_PANEL.construct());
	BorderPane stuff = new BorderPane();
	stuff.setRight(SETTINGS_PANEL.construct());
	stuff.setCenter(COMMAND_PANEL.construct());
	
	BorderPane commandStuff = new BorderPane();
	commandStuff.setCenter(HISTORY_PANEL.construct());
	commandStuff.setBottom(SETTINGS_PANEL.construct());
	BorderPane.setMargin(commandStuff.getCenter(), new Insets(0,0,12,0));
	borderPane.setCenter(commandStuff);
	
	
	
	BorderPane.setMargin(stuff.getCenter(), new Insets(0,12,12,12));
	BorderPane.setMargin(stuff.getRight(), new Insets(0,12,12,12));
	BorderPane.setMargin(borderPane.getLeft(), new Insets(0,12,12,12));
	BorderPane.setMargin(borderPane.getCenter(), new Insets(0,12,12,12));
	borderPane.setBottom(stuff);
	ROOT.getChildren().add(borderPane);
	ROOT.getChildren().add(TURTLE.display());

    }
    
    public Group getRoot() {
	return ROOT;
    }

    public void updateTurtle(List<State> states) {
	TURTLE.updateStates(states, ROOT);
    }
}
