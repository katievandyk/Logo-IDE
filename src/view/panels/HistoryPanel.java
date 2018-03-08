package view.panels;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.dictionaries.*;

public class HistoryPanel extends Panel {

    private TextArea PrevCommands;
    private TextArea SavedCommands;
    private TextArea SavedVariables;
    private VariableDictionary VariableDictionary;
    private CommandDictionary CommandDictionary;


    public HistoryPanel(CommandDictionary c, VariableDictionary v) {
	CommandDictionary = c;
	VariableDictionary = v;
	PrevCommands = new TextArea();
	PrevCommands.setPrefWidth(200);
	PrevCommands.setMinHeight(212);
	PrevCommands.setEditable(false);
	SavedCommands = TEXT.textArea("text-area");
	SavedVariables = TEXT.textArea("text-area");
    }

    public HBox construct() {
	VBox savedItems = new VBox(12, TEXT.styledText("Saved Commands", "label"), SavedCommands, TEXT.styledText("Saved Variables", "label"), SavedVariables);
	VBox commands = new VBox(12, TEXT.styledText("Previous Commands", "label"), PrevCommands);
	return new HBox(12, commands, savedItems);
    }

    public void commandEntered(String toAdd) {
	appendPrev(toAdd);
    }
 
    private void appendPrev(String toAdd) {
	String current = PrevCommands.getText();
	current = current+"\n"+toAdd;
	PrevCommands.setText(current);
	addCommands();
	addVariables();
    }

    public void addError(String toAdd) {
	String current = PrevCommands.getText();
	current = current+"\n"+toAdd;
	PrevCommands.setText(current);
    }


    private void addCommands() {
	SavedCommands.clear();
	for(String key : CommandDictionary) {
	    String current = SavedCommands.getText();
	    current = current+"\n"+ key;
	    SavedCommands.setText(current);
	}
    } 

    private void addVariables() {
	SavedVariables.clear();
	for(String key : VariableDictionary) {
	    String current = SavedVariables.getText();
	    current = current+"\n"+ key + "=" + VariableDictionary.get(key) ;
	    SavedVariables.setText(current);
	}
    } 
}
