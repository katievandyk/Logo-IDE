package view;

import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import view.panels.ControlPanel;
import view.panels.SettingsPanel;
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
	private SettingsPanel SETTINGS_PANEL;
	private final int BUFFER_SIZE = 10;
	private final int MARGIN = 50;
	protected Pane ROOT;

	public MainScreen(int screenHeight, int screenWidth, Controller c) {
		ROOT = new Pane();
		SETTINGS_PANEL = new SettingsPanel(c,TURTLE_PANEL);	
		BorderPane borderPane = initBorderPane();
		borderPane.setPrefSize(screenWidth, screenHeight);
		TURTLE_PANEL = new TurtlePanel(screenWidth* 3/4- MARGIN, screenHeight* 3/4, borderPane);
		TURTLE_PANEL.construct(ROOT);
		SETTINGS_PANEL.setTP(TURTLE_PANEL);
		CONTROL_PANEL = new ControlPanel(screenWidth, screenHeight, borderPane, c);	
	}
	
	private BorderPane initBorderPane() {
		BorderPane borderPane = new BorderPane();
		borderPane.getStyleClass().add("pane");
		borderPane.setTop(SETTINGS_PANEL.construct());
		borderPane.setBottom(new HBox());
		borderPane.setRight(new VBox());
		for(Node n : borderPane.getChildren()) {
			BorderPane.setMargin(n, new Insets(BUFFER_SIZE,BUFFER_SIZE,BUFFER_SIZE,BUFFER_SIZE));
		}
		ROOT.getChildren().add(borderPane);
		return borderPane;
	}

	public Pane getRoot() {
		return ROOT;
	}

	public void updateTurtle(List<State> states) {
		TURTLE_PANEL.updateTurtle(states);
	}


}
