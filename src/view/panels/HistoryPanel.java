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

    private ListView<Button> PrevCommands;
    private TextArea SavedCommands;
    private TextArea SavedVariables;
    private VariableDictionary VariableDictionary;
    private CommandDictionary CommandDictionary;
    private TextField commandLine;


    /**
     * Constructor for History Panel class that takes in command @param c and variable
     * @param v dictionaries
     */
    public HistoryPanel(CommandDictionary c, VariableDictionary v) {
	CommandDictionary = c;
	VariableDictionary = v;
	PrevCommands = new ListView<Button>();
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
    current.setOnAction(click->{commandLine.setText(toAdd);});
    PrevCommands.getItems().add(0,current);
	addCommands();
	addVariables();
    }
    
    public void setCommandLine(TextField cl) {
    	commandLine = cl;
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
