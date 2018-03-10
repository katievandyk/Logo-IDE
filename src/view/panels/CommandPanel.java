package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.ModelController;

/**
 * Constructs command panel that handles user input and sends to the parser
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa 
 *
 */
public class CommandPanel extends Panel {
    
    private String currentInput;
    private ModelController controller;
    private TextField CommandLine;
    private HistoryPanel HISTORY_PANEL;
 
    /**
     * Constructor for command panel that takes in ModelController @param c, 
     * History Panel @param hist for updating, and 
     * 
     * @param c
     * @param hist
     * @param state
     */
    public CommandPanel(ModelController c, HistoryPanel hist) {
	currentInput = "";
	HISTORY_PANEL = hist;
	controller = c;
	CommandLine = createCommandLine();
    }
    
    /**
     * @return VBox containing settings panels
     */
    public HBox construct() {
	HBox box = new HBox(12, CommandLine, createRunButton());
	return box;
    }

    /**
     * @return Button object that runs user input when pressed
     */
    private Button createRunButton() {
	Button RunButton = BUTTON.textButton("Run", "run-button");
	RunButton.setOnAction(click->{
	    currentInput = CommandLine.getText();
	    controller.update(currentInput);
	    HISTORY_PANEL.commandEntered(CommandLine.getText());
	    CommandLine.setText("");
	});
	return RunButton;
    }
    
    /**
     * @return TextField containing command line
     */
    private TextField createCommandLine() {
	TextField CommandLine = TEXT.textField("Command Line...", "commandLine");
	CommandLine.setOnAction(click->{ 
	    currentInput = CommandLine.getText();
	    controller.update(currentInput);
	    HISTORY_PANEL.commandEntered(CommandLine.getText());
	    CommandLine.setText("");
	});
	return CommandLine;
    }   
}
