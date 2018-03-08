package view.screens;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.ViewController;
import view.factory.TextFactory;
import view.panels.ButtonPanel;
import view.panels.CommandPanel;
import view.panels.HistoryPanel;
import view.panels.SettingsPanel;
import view.panels.StatePanel;
import view.panels.TurtlePanel;
import view.turtle.Turtle;
import model.ModelController;
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
    private final String TURTLE_IMAGE = "resources/turtles/defaultTurtle.png";
    private ArrayList<Turtle> TURTLES = new ArrayList<Turtle>();
    private TurtlePanel TURTLE_PANEL;
    private CommandPanel COMMAND_PANEL;
    private SettingsPanel SETTINGS_PANEL;
    private ButtonPanel BUTTON_PANEL;
    private HistoryPanel HISTORY_PANEL;
    private StatePanel STATE_PANEL;
    private Group ROOT;
    private TextFactory TEXT;
    private ArrayList<Integer> TURTLE_IDS = new ArrayList<Integer>();
    private TurtleList turtleList;
    private Turtle currentTurtle;

    
    public MainScreen(int screenHeight, int screenWidth, ModelController c, VariableDictionary variables, CommandDictionary commands, TurtleList turtList) {
	ROOT = new Group();
	turtleList = turtList;
	HISTORY_PANEL = new HistoryPanel(commands, variables);
	TURTLE_PANEL = new TurtlePanel();
	Turtle toAdd = new Turtle(TURTLE_IMAGE, TURTLE_PANEL.height(), TURTLE_PANEL.width(),1);
	TURTLES.add(toAdd);
	currentTurtle = toAdd;
	TURTLE_IDS.add(1);
	SETTINGS_PANEL = new SettingsPanel(c, TURTLE_PANEL, TURTLES.get(0));
	STATE_PANEL = new StatePanel(TURTLES.get(0), c, TURTLES);
	COMMAND_PANEL = new CommandPanel(c, HISTORY_PANEL, STATE_PANEL);
	BUTTON_PANEL = new ButtonPanel(c, TURTLES);
	TEXT = new TextFactory();
    }

    public BorderPane initBorderPane() {
	Text settingsTitle = TEXT.styledText("Settings", "titleText");
	BorderPane borderPane = new BorderPane();
	borderPane.setCenter(new VBox(12, TURTLE_PANEL.construct(), COMMAND_PANEL.construct(), STATE_PANEL.construct()));
	borderPane.setRight(new VBox(12, settingsTitle, HISTORY_PANEL.construct(), SETTINGS_PANEL.construct(), BUTTON_PANEL.construct()));
	borderPane.getRight().setId("rightpane");
	borderPane.getCenter().setId("centerpane");
	for(Node n : borderPane.getChildren()) BorderPane.setMargin(n, new Insets(0,12,12,12));

	return borderPane;
    }


    public Group getRoot() {
	ROOT.getChildren().add(initBorderPane());
	ROOT.getChildren().add(TURTLES.get(0).display());
	return ROOT;
    }

    private void makeTurtle(int id) {
	Turtle toAdd = new Turtle(TURTLE_IMAGE, TURTLE_PANEL.height(), TURTLE_PANEL.width(), id);
	TURTLES.add(toAdd);
	TURTLE_IDS.add(id);
	ROOT.getChildren().add(toAdd.display());
    }

    public void updateTurtle(List<State> states) {
	checkForNewTurtle(states);
	updateActiveTurtle();
	for(Turtle current : TURTLES) {
	    if(current.getActive()) {
	    current.setSpeed(BUTTON_PANEL.getSliderValue());
		current.updateStates(states, ROOT);
	    }
	}
	STATE_PANEL.updatePane(currentTurtle);
    }


    public void toggleTurtle(double x, double y) {
	boolean hitTurtle = false;
	List<Integer> active = new ArrayList<Integer>();
	for(Turtle current : TURTLES) {
	    hitTurtle = current.toggleTurtle(x, y);
	    if(hitTurtle) {
		currentTurtle = current;
		SETTINGS_PANEL.updateTurtle(currentTurtle);
		STATE_PANEL.updatePane(currentTurtle);
	    }
	}
	for(Turtle current : TURTLES) {
	    if(current.getActive()) {
		active.add(current.getID());
	    }
	}
	turtleList.setActiveTurtles(active);
    }

    private void updateActiveTurtle() {
	List<Integer> active = turtleList.getActiveTurtles();
	for(Turtle current : TURTLES) {
	    if(active.contains(current.getID())) {
		current.setActive(true);
	    }
	    else {
		current.setActive(false);
	    }
	}
    }

    private void checkForNewTurtle(List<State> states) {
	for(State s : states) {
	    if(!TURTLE_IDS.contains(s.getID())){
		makeTurtle(s.getID());
	    }
	}
    }
}
