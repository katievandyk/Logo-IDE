package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import view.panels.ControlPanel;
import view.panels.TurtlePanel;


/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/2018
 *
 * Creates the root object to be placed in the simulation Scene. 
 * 
 */
public class MainScreen extends ViewController  {
    
    private TurtlePanel TURTLE_PANEL;
    private ControlPanel CONTROL_PANEL;
    private BorderPane ROOT_PANE;
    protected Parent ROOT;
    private final int GENERATIONS_PER_SEC = 60;


    // need to save the Engine to call functions on button clicks
    public MainScreen(int screenHeight, int screenWidth) {
        TURTLE_PANEL = new TurtlePanel(screenHeight/2, screenWidth/2);
        CONTROL_PANEL = new ControlPanel();
        makeRoot();
    }

    public void makeRoot() {
        Parent turtlePanel = TURTLE_PANEL.construct();
        Parent controlPanel = CONTROL_PANEL.construct();
        ROOT_PANE = new BorderPane();
        ROOT_PANE.setCenter(turtlePanel);
        ROOT_PANE.setRight(controlPanel);
        ROOT_PANE.setId("mainScreenRoot");
        ROOT = ROOT_PANE;
        // attach "animation loop" to time line to play it
        KeyFrame frame = new KeyFrame(Duration.millis(1000/ GENERATIONS_PER_SEC),
		e -> step(1/ GENERATIONS_PER_SEC));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
    
    
    public Parent getRoot() {
        return ROOT;
    }

    /**
     * Creates stacked cell grid and graph
     * 
     * @return
     */
    public BorderPane getRootPane() {
	if (ROOT_PANE == null) {
	    makeRoot();
	}
        return ROOT_PANE;
    }

    /**
     * Change properties of displayed items to reflect animation properties
     * 
     * @param elapsedTime: time since last animation update
     */
    private void step (double elapsedTime) {
        TURTLE_PANEL.update();
    }
    
    
}
