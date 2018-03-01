package view.panels;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class HistoryPanel {

    private TextArea PrevCommands;

    public HistoryPanel() {
	PrevCommands = new TextArea();
	PrevCommands.setEditable(false);
    }

    public VBox construct() {
	return new VBox(20, PrevCommands);
    }

    public void appendPrev(String toAdd) {
	String current = PrevCommands.getText();
	current = current+"\n"+toAdd;
	PrevCommands.setText(current);
    }
}
