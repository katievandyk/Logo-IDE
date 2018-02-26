package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class ViewController {
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 700;
    private static final int GENERATIONS_PER_SEC = 60;
    private final Color BACKGROUND = Color.AZURE;
    private Scene PROGRAM_SCENE;
    private Stage PROGRAM_STAGE;
    private Parent root;
    private MainScreen mainScreen;

    /**
     * Initialize the program.
     * 
     * @param stage
     * @return
     */
    public void initialize(Stage stage) {
    	KeyFrame frame = new KeyFrame(Duration.millis(1000/ GENERATIONS_PER_SEC),
    			e -> step(1/ GENERATIONS_PER_SEC));
    	Timeline animation = new Timeline();
    	animation.setCycleCount(Timeline.INDEFINITE);
    	animation.getKeyFrames().add(frame);
    	animation.play();
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
    	mainScreen = new MainScreen(height, width, PROGRAM_STAGE);
    	root = mainScreen.getRoot();
    	PROGRAM_SCENE = new Scene(root, width, height);	
    	PROGRAM_SCENE.setFill(BACKGROUND);
    	PROGRAM_STAGE.setScene(PROGRAM_SCENE);
    }
    
    
    /**
     * Method to handle actions per frame.
     * 
     * @param elapsedTime
     */
    private void step (double elapsedTime) {
    	mainScreen.step(elapsedTime);
    }
    
    public void updateTurtle(List<State> states) {
	mainScreen.updateTurtle(List<State> states);
    }
    

}
