package view;

import java.util.List;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.dictionaries.CommandDictionary;
import model.dictionaries.VariableDictionary;
import model.state.State;
import view.screens.ErrorScreen;
import view.screens.MainScreen;

/**
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 *
 */
public class ViewController {
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 1000;
    private Scene PROGRAM_SCENE;
    private Stage PROGRAM_STAGE;
    private MainScreen mainScreen;
    private BorderPane mainPane;
    private Controller controller;
    private CommandDictionary commandDictionary;
    private VariableDictionary variableDictionary;
    protected TabPane tabPane;

    /**
     * Initialize the program.
     * 
     * @param stage
     * @return
     */
    public void initialize(Stage stage, Controller c, CommandDictionary commandDict, VariableDictionary varDict) {
	controller = c;
	variableDictionary= varDict;
	commandDictionary = commandDict;
	PROGRAM_STAGE = stage;
	PROGRAM_STAGE.setTitle("Slogo");
	mainPane = new BorderPane();
	constructMainScreen(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	PROGRAM_SCENE.setOnMouseClicked(e -> toggleTurtle(e.getX(), e.getY()));
	stage.show();
    }

    /**
     * Calls the Screen object to generate a start screen to display 
     * to the user upon application start up. Assigns the instance variable
     * @param PROGRAM_SCENE to allow for easy root changes to change scenes. 
     */
    private void constructMainScreen(int width, int height) {
	mainScreen = new MainScreen(DEFAULT_HEIGHT, DEFAULT_WIDTH,  controller, variableDictionary, commandDictionary);
	mainPane.setTop(tabConstructor());
	mainPane.setCenter(mainScreen.getRoot());
	PROGRAM_SCENE = new Scene(mainPane, width, height);	
	PROGRAM_SCENE.getStylesheets().add(ViewController.class.getResource("default.css").toExternalForm());
	mainPane.getStyleClass().add("pane");
	PROGRAM_STAGE.setScene(PROGRAM_SCENE);
	PROGRAM_STAGE.setResizable(false);
    }

    public void updateTurtle(List<State> states) {
	mainScreen.updateTurtle(states);
    }
    
    private void toggleTurtle(double x, double y) {
    	mainScreen.toggleTurtle(x,y);
    }

    private TabPane tabConstructor() {
	tabPane = new TabPane();
	Tab tab = constructTab();
	tabPane.getTabs().add(tab);
	return tabPane;
    }

    public void sendError(String message) {
	ErrorScreen error = new ErrorScreen();
	error.sendError(message);
    }

    private Tab constructTab() {
	HBox hbox = new HBox();
	Tab tab = new Tab();
	tab.setGraphic(new Circle(0, 0, 10));
	tab.setContent(hbox);
	return tab;
    }

}
