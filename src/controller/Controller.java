package controller;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;
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
    private Stage PROGRAM_STAGE;
    private BorderPane BP;
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 1000;
    private List<Node> PROGRAM_SCENES;
    private Scene SCENE;

    public Controller(Stage stage) {
	modelController = new ModelController();
	initBorderPane();
	initStage(stage);
	initScene();
	modelController.initialize();
	PROGRAM_STAGE.setScene(SCENE);
	PROGRAM_STAGE.centerOnScreen();	
	setScene();
    }

    private void initBorderPane() {
	TabPanel tp = new TabPanel(this);
	BP = new BorderPane();
	BP.setTop(tp.construct());
    }

    private void initStage(Stage stage) {
	PROGRAM_STAGE = stage;
	PROGRAM_STAGE.setTitle("Slogo");
	PROGRAM_STAGE.setResizable(false);
	PROGRAM_STAGE.show();
    }

    private void initScene() {
	PROGRAM_SCENES = new LinkedList<Node>();
	SCENE = new Scene(BP, DEFAULT_WIDTH, DEFAULT_HEIGHT);	
	SCENE.getStylesheets().add(ViewController.class.getResource("default.css").toExternalForm());
	SCENE.setOnMouseClicked(e -> toggleTurtle(e.getX(), e.getY()));
    }

    private void setScene() {
	BP.setCenter(modelController.getScreen(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	PROGRAM_SCENES.add(BP.getCenter());
	BP.getStyleClass().add("pane");  
    }

    public void addContext() {
	modelController = new ModelController();
	modelController.initialize();
	setScene();
    }

    public void switchContext(int index) {
	Node s = PROGRAM_SCENES.get(index);
	BP.setCenter(s);
    }


    private void toggleTurtle(double x, double y) {
	modelController.toggleTurtle(x,y);
    }

}
