package view;

import java.util.List;
import java.util.Map;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.panels.CommandPanel;
import view.panels.HistoryPanel;
import view.panels.SettingsPanel;
import view.panels.TurtlePanel;
import view.turtle.Turtle;
import model.commands.Command;
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
    private final String TURTLE_IMAGE = "resources/images/defaultTurtle.png";
    private Turtle TURTLE;
    private TurtlePanel TURTLE_PANEL;
    private CommandPanel COMMAND_PANEL;
    private SettingsPanel SETTINGS_PANEL;
    private HistoryPanel HISTORY_PANEL;
    private final int BUFFER_SIZE = 10;
    protected Pane ROOT;

    public MainScreen(int screenHeight, int screenWidth, Controller c, Map<String, Double> variables, Map<String, List<Command>[]> commands) {
	ROOT = new Pane();
	HISTORY_PANEL = new HistoryPanel(null, null);
	TURTLE_PANEL = new TurtlePanel(screenWidth* 3/4, screenHeight* 3/4);
	TURTLE = new Turtle(TURTLE_IMAGE,  screenHeight* 3/4, screenWidth* 3/4);
	SETTINGS_PANEL = new SettingsPanel(c,TURTLE_PANEL, TURTLE);
	COMMAND_PANEL = new CommandPanel(c, HISTORY_PANEL);

	initBorderPane();
    }

    private void initBorderPane() {
	BorderPane borderPane = new BorderPane();
	borderPane.getStyleClass().add("pane");
	borderPane.setTop(SETTINGS_PANEL.construct());
	borderPane.setBottom(COMMAND_PANEL.construct());
	borderPane.setRight(HISTORY_PANEL.construct());
	borderPane.setCenter(TURTLE_PANEL.construct());

	for(Node n : borderPane.getChildren()) {
	    BorderPane.setMargin(n, new Insets(BUFFER_SIZE,BUFFER_SIZE,BUFFER_SIZE,BUFFER_SIZE));
	}
	ROOT.getChildren().add(borderPane);
	ROOT.getChildren().add(TURTLE.display());

    }

    public Pane getRoot() {
	return ROOT;
    }

    public void updateTurtle(List<State> states) {
	TURTLE.updateStates(states, ROOT);
    }


}
