package view;

import java.util.List;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.state.State;

/**
 * 
 * @author Brandon Dalla Rosa
 * @author Katherine Van Dyk
 *
 */
public class ViewController {
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 700;
    private Scene PROGRAM_SCENE;
    private Stage PROGRAM_STAGE;
    private MainScreen mainScreen;
    private Controller controller;

    /**
     * Initialize the program.
     * 
     * @param stage
     * @return
     */
    public void initialize(Stage stage, Controller c) {
	controller = c;
	PROGRAM_STAGE = stage;
	PROGRAM_STAGE.setTitle("Slogo");
	generateMainScene(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	stage.show();
    }

    /**
     * Calls the Screen object to generate a start screen to display 
     * to the user upon application start up. Assigns the instance variable
     * @param PROGRAM_SCENE to allow for easy root changes to change scenes. 
     */
    private void generateMainScene(int width, int height) {
	mainScreen = new MainScreen(height, width, controller);
	Pane root = mainScreen.getRoot();
	PROGRAM_SCENE = new Scene(root, width, height);	
	PROGRAM_SCENE.getStylesheets().add(ViewController.class.getResource("default.css").toExternalForm());
	PROGRAM_STAGE.setScene(PROGRAM_SCENE);

    }

    public void sendError(String message) {
	Label errorLabel = new Label(message);
	errorLabel.getStyleClass().add("errorLabel");
	Stage errorStage = new Stage();
	errorStage.setScene(new Scene(errorLabel));
	errorStage.show();
    }

    public void updateTurtle(List<State> states) {
	mainScreen.updateTurtle(states);
    }



}
