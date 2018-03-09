package view;

import java.io.File;
import java.util.List;

import javafx.scene.Group;
import model.ModelController;
import model.dictionaries.CommandDictionary;
import model.dictionaries.TurtleList;
import model.dictionaries.VariableDictionary;
import model.state.State;
import view.panels.TabPanel;
import view.screens.ErrorScreen;
import view.screens.MainScreen;

/**
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 *
 */
public class ViewController {

    private MainScreen mainScreen;
    private ModelController controller;
    private CommandDictionary commandDictionary;
    private VariableDictionary variableDictionary;
    protected TabPanel tabPane;
    private TurtleList turtleList;

    /**
     * Initialize the program.
     * 
     * @param stage
     * @return
     */
    public void initialize(ModelController c, CommandDictionary commandDict, VariableDictionary varDict, TurtleList turtList) {
	controller = c;
	variableDictionary= varDict;
	commandDictionary = commandDict;
	turtleList = turtList;
    }

    /**
     * Calls the Screen object to generate a start screen to display 
     * to the user upon application start up. Assigns the instance variable
     * @param PROGRAM_SCENE to allow for easy root changes to change scenes. 
     * @return 
     */
    public void constructMainScreen(int width, int height) {
	mainScreen = new MainScreen(width, height,  controller, variableDictionary, commandDictionary, turtleList);
    }
    
    public Group getPane(int width, int height) {
	constructMainScreen(width, height);
	return mainScreen.getRoot();
    }
    
    public void updateTurtle(List<State> states) {
	mainScreen.updateTurtle(states);
    }

    public void toggleTurtle(double x, double y) {
	mainScreen.toggleTurtle(x,y);
    }

    public void sendError(String message) {
	ErrorScreen error = new ErrorScreen();
	error.sendError(message);
    }
    
    public void readFile(File file) {
	mainScreen.readFile(file);
    }

}
