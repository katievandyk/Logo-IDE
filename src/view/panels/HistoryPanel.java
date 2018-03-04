package view.panels;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.dictionaries.*;

public class HistoryPanel {

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
	PrevCommands.setMaxHeight(420);
	PrevCommands.setEditable(false);
	SavedCommands = TextAreaFactory();
	SavedVariables = TextAreaFactory();
    }

    private TextArea TextAreaFactory() {
	TextArea t = new TextArea();
	t.setEditable(false);
	return t;
    }

    public HBox construct() {
	VBox savedItems = new VBox(12, SavedCommands, SavedVariables);
	return new HBox(12, PrevCommands, savedItems);
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
	if(CommandDictionary == null) return;
	for(String key : CommandDictionary.getMap().keySet()) {
	    String current = PrevCommands.getText();
	    current = current+"\n"+ key;
	    SavedCommands.setText(current);
	}
    } 

    private void addVariables() {
	SavedVariables.clear();
	if(VariableDictionary == null) return;
	for(String key : VariableDictionary.getMap().keySet()) {
	    String current = PrevCommands.getText();
	    System.out.println(key); 
	    current = current+"\n"+ key + "=" + VariableDictionary.get(key) ;
	    SavedCommands.setText(current);
	}
    } 
}
