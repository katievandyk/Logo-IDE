package view.panels;

import java.util.List;
import java.util.Map;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import model.commands.Command;

public class HistoryPanel {

    private TextArea PrevCommands;
    private TextArea SavedCommands;
    private TextArea SavedVariables;
    private Map<String, Double> VariableDictionary;
    private Map<String, List<Command>[]> CommandDictionary;


    public HistoryPanel(Map<String, Double> variables, Map<String, List<Command>[]> commands) {
	CommandDictionary = commands;
	VariableDictionary = variables;
	PrevCommands = TextAreaFactory();
	SavedCommands = TextAreaFactory();
	SavedVariables = TextAreaFactory();
    }

    private TextArea TextAreaFactory() {
	TextArea t = new TextArea();
	t.setEditable(false);
	return t;
    }

    public VBox construct() {
	return new VBox(20, PrevCommands, SavedCommands, SavedVariables);
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
	for(String key : CommandDictionary.keySet()) {
	    String current = PrevCommands.getText();
	    current = current+"\n"+ key;
	    SavedCommands.setText(current);
	}
    } 

    private void addVariables() {
	SavedVariables.clear();
	for(String key : VariableDictionary.keySet()) {
	    String current = PrevCommands.getText();
	    current = current+"\n"+ key + "=" + VariableDictionary.get(key) ;
	    SavedCommands.setText(current);
	}
    } 
}
