package view.screens;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.ViewController;
import view.factory.TextFactory;
import view.panels.ButtonPanel;
import view.panels.CommandPanel;
import view.panels.HistoryPanel;
import view.panels.SettingsPanel;
import view.panels.StatePanel;
import view.panels.TurtlePanel;
import view.save.Reader;
import view.turtle.Turtle;
import model.ModelController;
import model.dictionaries.*;
import model.state.State;

/**
 * Arranges all components of the main screen, including the turtle panel,
 * settings panel, command/variable history and dictionaries, and current state
 * screen. 
 * 
 * @author Brandon Dalla Rosa
 * @author Katherine Van Dyk
 * @date 2/24/2018
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

    /**
     * Constructs all panels in main screen and connects to backend components, such as
     * @param variables and @param commands, dictionaries needed for display.
     * 
     * @param screenHeight, screenWidth: Screen height and width, respectively
     * @param c: ModelController
     * @param turtList: List of all turtles (active and inactive)
     */
    public MainScreen(int screenHeight, int screenWidth, ModelController c, VariableDictionary variables, CommandDictionary commands, TurtleList turtList) {
	ROOT = new Group();
	turtleList = turtList;
	HISTORY_PANEL = new HistoryPanel(commands, variables);
	TURTLE_PANEL = new TurtlePanel();
	Turtle toAdd = new Turtle(TURTLE_IMAGE, TURTLE_PANEL.height(), TURTLE_PANEL.width(),1, TURTLE_PANEL);
	TURTLES.add(toAdd);
	currentTurtle = toAdd;
	TURTLE_IDS.add(1);
	SETTINGS_PANEL = new SettingsPanel(c, TURTLE_PANEL, TURTLES.get(0));
	STATE_PANEL = new StatePanel(TURTLES.get(0), c, TURTLES);
	COMMAND_PANEL = new CommandPanel(c, HISTORY_PANEL);
	BUTTON_PANEL = new ButtonPanel(c, TURTLES);
	TEXT = new TextFactory();
	KeyFrame frame = new KeyFrame(Duration.millis(1000/60),
			e -> step(1/60));
	Timeline animation = new Timeline();
	animation.setCycleCount(Timeline.INDEFINITE);
	animation.getKeyFrames().add(frame);
	animation.play();

    }

    /**
     * @return Borderpane containing all panels constructed in correct arrangement and appropriate
     * margins
     */
    public BorderPane initializeBorderPane() {
	Text settingsTitle = TEXT.styledText("Settings", "titleText");
	BorderPane borderPane = new BorderPane();
	borderPane.setCenter(new VBox(12, TURTLE_PANEL.construct(), COMMAND_PANEL.construct(), STATE_PANEL.construct()));
	borderPane.getCenter().setId("centerpane");
	borderPane.setRight(new VBox(12, settingsTitle, HISTORY_PANEL.construct(), SETTINGS_PANEL.construct(), BUTTON_PANEL.construct()));
	borderPane.getRight().setId("rightpane");
	for(Node n : borderPane.getChildren()) BorderPane.setMargin(n, new Insets(0,12,12,12));
	return borderPane;
    }

    /**
     * @return Root collection containing all components of the main screen
     */
    public Group getRoot() {
	ROOT.getChildren().add(initializeBorderPane());
	ROOT.getChildren().add(TURTLES.get(0).display());
	return ROOT;
    }

    /**
     * Updates active turtle's states and speeds based on user input/button values from button panel
     */
    public void updateTurtle(List<State> states) {
	checkForNewTurtle(states);
	updateActiveTurtle();
	for(Turtle current : TURTLES) {
	    if(current.getActive()) {
		current.setSpeed(BUTTON_PANEL.getSliderValue());
		current.updateStates(states, ROOT);
	    }
	}
	STATE_PANEL.updatePane(currentTurtle, TURTLE_PANEL);
    }


    /**
     * Toggles turtles and updates turtle list to reflect all currently active turtles
     * @param x,y: coordinates of mouse click, which if on turtle, sets from active -> inactive or vice versa
     */
    public void toggleTurtle(double x, double y) {
	boolean hitTurtle = false;
	List<Integer> active = new ArrayList<Integer>();
	for(Turtle current : TURTLES) {
	    hitTurtle = current.toggleTurtle(x, y);
	    if(hitTurtle) {
		currentTurtle = current;
		SETTINGS_PANEL.updateTurtle(currentTurtle);
		STATE_PANEL.updatePane(currentTurtle, TURTLE_PANEL);
	    }
	}
	for(Turtle current : TURTLES) {
	    if(current.getActive()) active.add(current.getID());
	}
	turtleList.setActiveTurtles(active);
    }

    /**
     * Updates active turtle's active list
     */
    private void updateActiveTurtle() {
	List<Integer> active = turtleList.getActiveTurtles();
	for(Turtle current : TURTLES) {
	    if(active.contains(current.getID())) current.setActive(true);
	    else current.setActive(false);
	}
    }

    /**
     * If given a state object with a new ID, creates a new turtle to reflect new ID
     * @param states: List of state objects from parser, which could contain new turtles
     */
    private void checkForNewTurtle(List<State> states) {
	for(State s : states) {
	    if(!TURTLE_IDS.contains(s.getID())){
		makeTurtle(s.getID());
	    }
	}
    }
    
    private void makeTurtle(int id) {
 	Turtle toAdd = new Turtle(TURTLE_IMAGE, TURTLE_PANEL.height(), TURTLE_PANEL.width(), id, TURTLE_PANEL);
 	TURTLES.add(toAdd);
 	TURTLE_IDS.add(id);
 	ROOT.getChildren().add(toAdd.display());
     }

    
    /**
     * Prompts turtle panel to reflect user-uploaded workspace preferences
     */
    public void readFile(File file) {
	Reader reader = new Reader(TURTLES.get(0), TURTLE_PANEL);
	reader.read(file);
    }
    
    private void step(int na) {
    	for(Turtle t : TURTLES) {
    		t.handleAnimation();
    	}
    }

}
