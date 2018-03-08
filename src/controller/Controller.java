package controller;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ModelController;
import view.ViewController;
import view.panels.TabPanel;

/**
 * Handles updating turtles state from user input
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @date 2/25/18
 *
 */
public class Controller{
    public ModelController modelController;
    private TabPanel tabPanel;
    private Stage PROGRAM_STAGE;
    private BorderPane BP;
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 1000;
    private List<Scene> PROGRAM_SCENES;

    public Controller(Stage stage) {
	PROGRAM_STAGE = stage;
	PROGRAM_STAGE.setTitle("Slogo");
	PROGRAM_STAGE.setResizable(false);
	PROGRAM_SCENES = new LinkedList<Scene>();
	modelController = new ModelController();
	tabPanel = new TabPanel(this, modelController);
	modelController.initialize();
	setScene();
    }

    private void setScene() {
	PROGRAM_STAGE.show();
	BP = modelController.getPane(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	BP.setTop(tabPanel.construct());
	Scene s = new Scene(BP, DEFAULT_WIDTH, DEFAULT_HEIGHT);	
	s.getStylesheets().add(ViewController.class.getResource("default.css").toExternalForm());
	s.setOnMouseClicked(e -> toggleTurtle(e.getX(), e.getY()));
	PROGRAM_SCENES.add(s);
	PROGRAM_STAGE.setScene(s);
	PROGRAM_STAGE.centerOnScreen();	
    }
    
    private void changeScene() {
	Scene s = PROGRAM_SCENES.get(0);
	PROGRAM_STAGE.setScene(s);
	PROGRAM_STAGE.centerOnScreen();	
    }

    public void addContext(ModelController c) {
	modelController = new ModelController();
	modelController.initialize();
	setScene();
    }

    public void switchContext(ModelController c) {
	modelController = c;
	changeScene();
    }

    private void toggleTurtle(double x, double y) {
	modelController.toggleTurtle(x,y);
    }

}
