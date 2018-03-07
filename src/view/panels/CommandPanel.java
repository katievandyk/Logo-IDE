package view.panels;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class CommandPanel extends Panel {
    
    private String currentInput;
    private Controller controller;
    private Button RunButton;
    private TextField CommandLine;
    private HistoryPanel HISTORY_PANEL;
 
    
    public CommandPanel(Controller c, HistoryPanel hist, StatePanel state) {
	currentInput = "";
	HISTORY_PANEL = hist;
	controller = c;
	initializeObjects();
    }

    
    /**
     * @return VBox containing settings panels
     */
    public HBox construct() {
	HBox box = new HBox(12, CommandLine, RunButton);
	return box;
    }

    private void createRunButton() {
	RunButton = textButtonFactory("Run", "run-button");
	RunButton.setOnAction(click->{
	    currentInput = CommandLine.getText();
	    controller.update(currentInput);
	    HISTORY_PANEL.commandEntered(CommandLine.getText());
	    CommandLine.setText("");
	});
    }
    

    private void createCommandLine() {
	CommandLine = textFieldFactory("Command Line...", "commandLine");
	CommandLine.setOnAction(click->{ 
	    currentInput = CommandLine.getText();
	    controller.update(currentInput);
	    HISTORY_PANEL.commandEntered(CommandLine.getText());
	    CommandLine.setText("");
	});
    }   

    private void initializeObjects() {
	createRunButton();
	createCommandLine();
    }
}
