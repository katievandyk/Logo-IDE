package view.panels;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	PrevCommands.setMinHeight(212);
	PrevCommands.setEditable(false);
	SavedCommands = TextAreaFactory();
	SavedVariables = TextAreaFactory();
    }

    private TextArea TextAreaFactory() {
	TextArea t = new TextArea();
	t.getStyleClass().add("text-area");
	t.setEditable(false);
	return t;
    }

    public HBox construct() {
	VBox savedItems = new VBox(12, labelFactory("Saved Commands"), SavedCommands, labelFactory("Saved Variables"), SavedVariables);
	VBox commands = new VBox(12, labelFactory("Previous Commands"), PrevCommands);
	return new HBox(12, commands, savedItems);
    }

    public void commandEntered(String toAdd) {
	appendPrev(toAdd);
    }
    
    private Text labelFactory(String text) {
	Text label = new Text(text);
	label.setId("label");
	return label;
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
	for(String key : CommandDictionary.getMap().keySet()) {
	    String current = SavedCommands.getText();
	    current = current+"\n"+ key;
	    SavedCommands.setText(current);
	}
    } 

    private void addVariables() {
	SavedVariables.clear();
	for(String key : VariableDictionary.getMap().keySet()) {
	    String current = SavedVariables.getText();
	    current = current+"\n"+ key + "=" + VariableDictionary.get(key) ;
	    SavedVariables.setText(current);
	}
    } 
}
