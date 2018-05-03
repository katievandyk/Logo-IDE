package view.panels;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.dictionaries.*;

/**
 * Handles displaying past commands, saved variables, and saved commands
 * 
 * @author Katherine Van Dyk
 *
 */
public class HistoryPanel extends Panel {
	private static final String EDIT_MESSAGE = "Edit";
    private ListView<HBox> PrevCommands;
    private TextArea SavedCommands;
    private TextArea SavedVariables;
    private VariableDictionary VariableDictionary;
    private CommandDictionary CommandDictionary;
    private CommandPanel commandPanel;


    /**
     * Constructor for History Panel class that takes in command @param c and variable
     * @param v dictionaries
     */
    public HistoryPanel(CommandDictionary c, VariableDictionary v) {
	CommandDictionary = c;
	VariableDictionary = v;
	PrevCommands = new ListView<HBox>();
	PrevCommands.setPrefWidth(200);
	PrevCommands.setMinHeight(212);
	PrevCommands.setEditable(false);
	SavedCommands = TEXT.textArea("text-area");
	SavedVariables = TEXT.textArea("text-area");
    }

    /**
     * Constructs HBox containing previous command, variable, and saved command panels
     */
    public HBox construct() {
	VBox savedItems = new VBox(12, TEXT.styledText("Saved Commands", "label"), SavedCommands, TEXT.styledText("Saved Variables", "label"), SavedVariables);
	VBox commands = new VBox(12, TEXT.styledText("Previous Commands", "label"), PrevCommands);
	return new HBox(12, commands, savedItems);
    }

    /**
     * Appends @param toAdd to list of previous commands
     */
    public void commandEntered(String toAdd) {
    Button current = new Button(toAdd);
    Button editButton = new Button(EDIT_MESSAGE);
    current.setOnAction(click->{
    	commandPanel.getCommandLine().setText(toAdd);
    	commandPanel.runFromPrev(toAdd);
    	});
    editButton.setOnAction(click->{
    	commandPanel.getCommandLine().setText(toAdd);
    });
    HBox currentBox = new HBox(0);
    currentBox.getChildren().addAll(current,editButton);
    PrevCommands.getItems().add(0,currentBox);
	addCommands();
	addVariables();
    }
    
    /**
     * Set the command panel for access through previous methods.
     * @param cp
     */
    public void setCommandPanel(CommandPanel cp) {
    	commandPanel = cp;
    }

    /**
     * Adds saved commands to command window
     */
    private void addCommands() {
	SavedCommands.clear();
	for(String key : CommandDictionary) {
	    String current = SavedCommands.getText();
	    current = current+"\n"+ key;
	    SavedCommands.setText(current);
	}
    } 

    /**
     * Adds saved variables to variable window
     */
    private void addVariables() {
	SavedVariables.clear();
	for(String key : VariableDictionary) {
	    String current = SavedVariables.getText();
	    current = current+"\n"+ key + "=" + VariableDictionary.get(key) ;
	    SavedVariables.setText(current);
	}
    } 
}
