package view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Screen that displays error message on separate stage
 * 
 * @author Katherine Van Dyk
 */
public class ErrorScreen {
    
    private Group root;

    /**
     * Constructor that initializes stage
     */
    public ErrorScreen() {
	Stage errorStage = new Stage();
	root = new Group();
	errorStage.setScene(new Scene(root, 600, 100));
	errorStage.show();
	errorStage.centerOnScreen();
    }
    
    /**
     * Displays @param message on error screen for user
     */
    public void sendError(String message) {
	Text errorMessage = new Text(message);
	HBox errorBox = new HBox(10, errorMessage);
	root.getChildren().add(errorBox);
    }

}
