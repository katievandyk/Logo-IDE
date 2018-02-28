package view;

import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.panels.ControlPanel;
import view.panels.TurtlePanel;
import model.state.State;


/**
 * 
 * @author Brandon Dalla Rosa
 * @author Katherine Van Dyk
 * @date 2/24/2018
 *
 * Creates the root object to be placed in the simulation Scene. 
 * 
 */
public class MainScreen extends ViewController  {
	private TurtlePanel TURTLE_PANEL;
	private ControlPanel CONTROL_PANEL;
	private String[] languages = {"English","Chinese","French","German","Italian","Portuguese","Russian","Spanish",};
	private final int BUFFER_SIZE = 10;
	private final int MARGIN = 50;
	protected Group ROOT;

	// need to save the Engine to call functions on button clicks
	@SuppressWarnings("static-access")
	public MainScreen(int screenHeight, int screenWidth, Controller c) {
		ROOT = new Group();
		BorderPane borderPane = initBorderPane();
		borderPane.setPrefSize(screenWidth, screenHeight);

		TURTLE_PANEL = new TurtlePanel(screenWidth* 3/4- MARGIN, screenHeight* 3/4, borderPane);
		CONTROL_PANEL = new ControlPanel(screenWidth, screenHeight, borderPane, c,TURTLE_PANEL);	
		makeRoot();
		CONTROL_PANEL.addBackColor();
		CONTROL_PANEL.addPenColor();
		CONTROL_PANEL.addTurtle("Standard");
		for(int i=0;i<languages.length;i++) {
			CONTROL_PANEL.addLanguage(languages[i]);
		}
		
		for(Node n : borderPane.getChildren()) {
			borderPane.setMargin(n, new Insets(BUFFER_SIZE,BUFFER_SIZE,BUFFER_SIZE,BUFFER_SIZE));
		}
	}
	
	private BorderPane initBorderPane() {
		BorderPane borderPane = new BorderPane();
		HBox topHBox = new HBox(BUFFER_SIZE);
		topHBox.setAlignment(Pos.CENTER);
		HBox bottomHBox = new HBox(BUFFER_SIZE);
		bottomHBox.setAlignment(Pos.CENTER);
		VBox rightVBox = new VBox(BUFFER_SIZE);
		rightVBox.setAlignment(Pos.CENTER);
		borderPane.setTop(topHBox);
		borderPane.setBottom(bottomHBox);
		borderPane.setRight(rightVBox);
		ROOT.getChildren().add(borderPane);
		return borderPane;
	}

	public void makeRoot() {  
		TURTLE_PANEL.construct(ROOT);
	}

	public Group getRoot() {
		return ROOT;
	}

	public void updateTurtle(List<State> states) {
		TURTLE_PANEL.updateTurtle(states);
	}


}
