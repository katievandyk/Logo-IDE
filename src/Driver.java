import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/** 
 * Use the driver JavaFX program to start a SLogo IDE
 *
 * @author Katherine Van Dyk
 * @date 1/30/18
 *
 * 
 */
public class Driver extends Application {  
	/**
	 * Initialize the program and begin the animation loop 
	 * 
	 * @param stage: Primary stage to attach all scenes
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new Controller(primaryStage);
		primaryStage.show();	
	}


	/**
	 * Start the program
	 */
	public static void main (String[] args) {
		launch(args);
	}
}
