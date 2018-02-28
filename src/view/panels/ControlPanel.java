package view.panels;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.Gobject;

/**
 * 
 * @author Katherine Van Dyk
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
	private TurtlePanel TURTLE_PANEL;
	private final String WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
	private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");
	private ArrayList<Gobject> gArray;
	private final int BUTTONWIDTH = 200;
	private final int BUTTONHEIGHT = 40;

	public ControlPanel(double width, double height, BorderPane root, Controller c, TurtlePanel tp) {
		currentInput = "";
		controller = c;
		gArray = new ArrayList<Gobject>();
		ROOT = root;
		TURTLE_PANEL = tp;
		
		createGobject(700,60,3,(HBox)ROOT.getBottom()); 				//0: Command line
		createGobject(BUTTONWIDTH,BUTTONHEIGHT,2,(HBox)ROOT.getTop());	//1: Background picker
		createGobject(BUTTONWIDTH,BUTTONHEIGHT,2,(HBox)ROOT.getTop());	//2: Pen Color
		createGobject(BUTTONWIDTH,BUTTONHEIGHT,2,(HBox)ROOT.getTop());	//3. Image Picker
		createGobject(BUTTONWIDTH,BUTTONHEIGHT,2,(HBox)ROOT.getTop());	//4. Language Picker
		createGobject(BUTTONWIDTH+30,550,5,(VBox)ROOT.getRight());		//5. Previous Commands
		createGobject(BUTTONWIDTH+30,60,1,(HBox)ROOT.getBottom());		//6. Help Button
		initializeObjects();
	}
	
	private void createGobject(double width, double height, int type, Pane box) {
		Gobject temporaryGobject = new Gobject(0,0,width,height,type);
		gArray.add(temporaryGobject);
		box.getChildren().add(temporaryGobject.getObject());
	}
	
	private void appendPrev(String toAdd) {
		TextArea text = (TextArea)gArray.get(5).getObject();
		String current = text.getText();
		current = current+"\n"+toAdd;
		text.setText(current);
	}

	public void addBackColor() {
		@SuppressWarnings("unchecked")
		ComboBox<String> toAdd = (ComboBox<String>) gArray.get(1).getObject();
		for(String color : COLOR_RESOURCES.keySet()) {
			toAdd.getItems().add(color);
		}
	}

	public void addPenColor() {
		@SuppressWarnings("unchecked")
		ComboBox<String> toAdd = (ComboBox<String>) gArray.get(2).getObject();
		for(String color : COLOR_RESOURCES.keySet()) {
			toAdd.getItems().add(color);
		}
	}

	public void addLanguage(String language) {
		@SuppressWarnings("unchecked")
		ComboBox<String> toAdd = (ComboBox<String>)gArray.get(4).getObject();
		toAdd.getItems().add(language);
	}
	
	public void addTurtle(String turtleName) {
		@SuppressWarnings("unchecked")
		ComboBox<String> toAdd = (ComboBox<String>)gArray.get(3).getObject();
		toAdd.getItems().add(turtleName);
	}
	
	private void handleBackColor(TurtlePanel tp) {
		@SuppressWarnings("unchecked")
		ComboBox<String> toGet = (ComboBox<String>) gArray.get(1).getObject();
		Color color = Color.web(COLOR_RESOURCES.getString(toGet.getValue()));
		tp.changeBack(color);
	}

	
	private void handlePenColor(TurtlePanel tp) {
		@SuppressWarnings("unchecked")
		ComboBox<String> toGet = (ComboBox<String>) gArray.get(2).getObject();
		tp.setPenColor(toGet.getValue());
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
	
	private void handleBackgroundPicker() {
		@SuppressWarnings("unchecked")
		ComboBox<String> colors1 = (ComboBox<String>) gArray.get(1).getObject();
		colors1.setPromptText("BackGround");
		colors1.setOnAction(click->{handleBackColor(TURTLE_PANEL);});
	}
	
	private void handlePenColorPicker() {
		@SuppressWarnings("unchecked")
		ComboBox<String> colors2 = (ComboBox<String>) gArray.get(2).getObject();
		colors2.setPromptText("Pen Color");
		colors2.setOnAction(click->{handlePenColor(TURTLE_PANEL);});
	}
	
	private void handleImagePicker() {
		@SuppressWarnings("unchecked")
		ComboBox<String> images = (ComboBox<String>)gArray.get(3).getObject();
		images.setPromptText("Turtle");
	}
	
	private void handleLanguagePicker() {
		@SuppressWarnings("unchecked")
		ComboBox<String> langs = (ComboBox<String>)gArray.get(4).getObject();
		langs.setPromptText("Language");
		langs.setOnAction(click->{controller.updateLanguage(langs.getValue());});
	}
	
	private void handlePreviousCommandBox() {
		TextArea prev = (TextArea)gArray.get(5).getObject();
		prev.setEditable(false);
	}
	
	private void handleHelpButton() {
		Button help = (Button)gArray.get(6).getObject();
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
		handleBackgroundPicker();
		handlePenColorPicker();
		handleImagePicker();
		handleLanguagePicker();
		handlePreviousCommandBox();
		handleHelpButton();
	}
	
}
