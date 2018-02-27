package view.panels;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private Gobject commandLine;
    private Gobject backPicker;
    private Gobject penColor;
    private Gobject imagePick;
    private Gobject languagePick;
    private Gobject previousCom;
    private Gobject helpButton;
    private Controller controller;
    private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");

    public ControlPanel(double width, double height, Group root, Controller c, TurtlePanel tp) {
	currentInput = "";
	controller = c;
	commandLine = new Gobject(20,height-90,width*.75-50,60,3);
	TextField text = (TextField)commandLine.getObject();
	text.setOnAction(click->{ 
	    currentInput = text.getText();
	    controller.update(currentInput);
	    appendPrev(text.getText()); 
	    text.setText("");
	});
	text.setPromptText("Command Line...");
	root.getChildren().add(text);

	backPicker = new Gobject(20,5,width/5,40,2);
	ComboBox<String> colors1 = (ComboBox<String>)backPicker.getObject();
	colors1.setPromptText("BackGround");
	colors1.setOnAction(click->{handleBackColor(tp);});
	root.getChildren().add(colors1);

	penColor = new Gobject(30+width/5,5,width/5,40,2);
	ComboBox<String> colors2 = (ComboBox<String>)penColor.getObject();
	colors2.setPromptText("Pen Color");
	root.getChildren().add(colors2);

	imagePick = new Gobject(40+2*width/5,5,width/5,40,2);
	ComboBox<String> images = (ComboBox<String>)imagePick.getObject();
	images.setPromptText("Turtle");
	root.getChildren().add(images);

	languagePick = new Gobject(50+3*width/5,5,width/5,40,2);
	ComboBox<String> langs = (ComboBox<String>)languagePick.getObject();
	langs.setPromptText("Language");
	root.getChildren().add(langs);

	previousCom = new Gobject(width*.75-20,60,width/5+30,height*0.75,5);
	TextArea prev = (TextArea)previousCom.getObject();
	prev.setEditable(false);
	root.getChildren().add(prev);

	helpButton = new Gobject(width*.75-20,height-90,width/5+30,60,1);
	Button help = (Button)helpButton.getObject();
	help.setText("Help");
	help.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI("https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php"));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (URISyntaxException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}});
	root.getChildren().add(help);

    }

    public void update(Stage stage) {
	commandLine.updateObject(stage);
	backPicker.updateObject(stage);
	penColor.updateObject(stage);
	imagePick.updateObject(stage);
	languagePick.updateObject(stage);
	previousCom.updateObject(stage);
	helpButton.updateObject(stage);
    }
    private void appendPrev(String toAdd) {
	TextArea text = (TextArea)previousCom.getObject();
	String current = text.getText();
	current = current+"\n"+toAdd;
	text.setText(current);
    }

    public void addBackColor(String color) {
	ComboBox<String> toAdd = (ComboBox<String>) backPicker.getObject();
	toAdd.getItems().add(color);
    }

    public void addPenColor() {
	ComboBox<String> toAdd = (ComboBox<String>) penColor.getObject();
	for(String color : COLOR_RESOURCES.keySet()) {
	    toAdd.getItems().add(color);
	}
    }

    public void addLanguage(String language) {
	ComboBox<String> toAdd = (ComboBox<String>)languagePick.getObject();
	toAdd.getItems().add(language);
    }
    /**
     * This method needs heavy refactoring, but honestly this entire class does because I 
     * built it with basic functionality in mind. I'll fix it later.
     * 
     * @param tp
     */
    private void handleBackColor(TurtlePanel tp) {
	ComboBox<String> toGet = (ComboBox<String>) backPicker.getObject();
	if(toGet.getValue().equals("White")) {
	    tp.changeBack(Color.WHITE);
	}
	else if(toGet.getValue().equals("Black")) {
	    tp.changeBack(Color.BLACK);
	}
	else if(toGet.getValue().equals("Red")) {
	    tp.changeBack(Color.RED);
	}
	else if(toGet.getValue().equals("Blue")) {
	    tp.changeBack(Color.BLUE);
	}
	else if(toGet.getValue().equals("Yellow")) {
	    tp.changeBack(Color.YELLOW);
	}
	else if(toGet.getValue().equals("Green")) {
	    tp.changeBack(Color.GREEN);
	}
	else if(toGet.getValue().equals("Orange")) {
	    tp.changeBack(Color.ORANGE);
	}
	else if(toGet.getValue().equals("Pink")) {
	    tp.changeBack(Color.PINK);
	}
    }
    
    private void handlePenColor(TurtlePanel tp) {
	ComboBox<String> toGet = (ComboBox<String>) penColor.getObject();
	tp.setPenColor(toGet.getValue());
    }

}
