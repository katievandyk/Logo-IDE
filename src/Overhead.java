import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Overhead extends Application{
	private final Paint background = Color.AZURE;
	private static final int stageSizeX = 600;
	private static final int stageSizeY = 600;
	
	public void start (Stage stage) {
		
		Scene scene = initialize(stage);

		KeyFrame frame = new KeyFrame(Duration.millis(1000/60),
				e -> step(1/60));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	/**
	 * Initialize the program.
	 * 
	 * @param stage
	 * @return
	 */
	private Scene initialize(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, stageSizeX,stageSizeY, background);
		stage.setScene(scene);
		stage.setTitle("Slogo");
		stage.show();
		return scene;
	}
	/**
	 * Method to handle actions per frame.
	 * 
	 * @param elapsedTime
	 */
	private void step (double elapsedTime) {
		
	}
}
