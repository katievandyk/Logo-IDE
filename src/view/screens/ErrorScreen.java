package view.screens;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorScreen {

    
    public void sendError(String message) {
	Text errorMessage = new Text(message);
	HBox errorBox = new HBox(10, errorMessage);
	Stage errorStage = new Stage();
	Group root = new Group();
	root.getChildren().add(errorBox);
	errorStage.setScene(new Scene(root, 300, 100));
	errorStage.show();
	errorStage.centerOnScreen();
    }

}
