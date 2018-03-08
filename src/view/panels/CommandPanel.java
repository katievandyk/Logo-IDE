package view.panels;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.ModelController;


public class CommandPanel extends Panel {
    
    private String currentInput;
    private ModelController controller;
    private TextField CommandLine;
    private HistoryPanel HISTORY_PANEL;
 
    public CommandPanel(ModelController c, HistoryPanel hist, StatePanel state) {
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
