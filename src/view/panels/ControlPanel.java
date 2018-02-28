package view.panels;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import view.Gobject;

/**
 * 
 * @author Brandon Dalla Rosa
 * @date 2/24/18
 * 
 * Class to generate the cell panel to be displayed on the center of the simulation screen.
 * The cell panel child nodes are held in a VBox object.
 */
public class ControlPanel {
    private String currentInput;
    private Controller controller;
    private BorderPane ROOT;
    private final String WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    private ArrayList<Gobject> gArray;
    private final int BUTTONWIDTH = 200;

    public ControlPanel(double width, double height, BorderPane root, Controller c) {
	currentInput = "";
	controller = c;
	gArray = new ArrayList<Gobject>();
	ROOT = root;


	createGobject(700,60,3,(HBox) ROOT.getBottom()); 				//0: Command line;
	createGobject(BUTTONWIDTH+30,550,5,(VBox)ROOT.getRight());		//5. Previous Commands
	createGobject(BUTTONWIDTH+30,60,1,(HBox)ROOT.getBottom());		//6. Help Button
	initializeObjects();
    }

    private void createGobject(double width, double height, int type, Pane box) {
	Gobject temporaryGobject = new Gobject(width,height,type);
	gArray.add(temporaryGobject);
	box.getChildren().add(temporaryGobject.getObject());
    }

    private void appendPrev(String toAdd) {
	TextArea text = (TextArea)gArray.get(1).getObject();
	String current = text.getText();
	current = current+"\n"+toAdd;
	text.setText(current);
    } 




    private void handleCommandLine() {
	TextField text = (TextField)gArray.get(0).getObject();
	text.setOnAction(click->{ 
	    currentInput = text.getText();
	    controller.update(currentInput);
	    appendPrev(text.getText()); 
	    text.setText("");
	});
	text.setPromptText("Command Line...");
    }   


    private void handlePreviousCommandBox() {
	TextArea prev = (TextArea)gArray.get(1).getObject();
	prev.setEditable(false);
    }

    private void handleHelpButton() {
	Button help = (Button)gArray.get(2).getObject();
	help.setText("Help");
	help.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI(WEBSITE));
	} catch (IOException e) {
	    controller.sendError("IOException");
	    e.printStackTrace();
	} catch (URISyntaxException e) {
	    controller.sendError("URISyntaxException");
	    e.printStackTrace();
	}});
    }  

    private void initializeObjects() {
	handleCommandLine();
	handlePreviousCommandBox();
	handleHelpButton(); 
    }

}
