package view.panels;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CommandPanel {
    
    private String currentInput;
    private Controller controller;
    private final String WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    private Button HelpButton;
    private TextField CommandLine;
    private HistoryPanel HISTORY_PANEL;
    


    public CommandPanel(Controller c, HistoryPanel hist) {
	currentInput = "";
	HISTORY_PANEL = hist;
	controller = c;
	initializeObjects();
    }

    
    /**
     * @return VBox containing settings panels
     */
    public HBox construct() {
	HBox box = new HBox(10, CommandLine, HelpButton);
	return box;
    }

    private void createHelpButton() {
	HelpButton = new Button();
	HelpButton.setText("Help");
	HelpButton.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI(WEBSITE));
	} catch (IOException e) {
	//   viewController.sendError("IOException");
	    e.printStackTrace();
	} catch (URISyntaxException e) {
	 //   controller.sendError("URISyntaxException");
	    e.printStackTrace();
	}});
	
    }

    private void createCommandLine() {
	CommandLine = new TextField();
	CommandLine.setOnAction(click->{ 
	    currentInput = CommandLine.getText();
	    controller.update(currentInput);
	    HISTORY_PANEL.appendPrev(CommandLine.getText()); 
	    CommandLine.setText("");
	});
	CommandLine.setPromptText("Command Line...");
    }   

    private void initializeObjects() {
	createHelpButton();
	createCommandLine();
    }
}
