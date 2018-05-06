package view.panels;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ModelController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.save.Writer;
import view.screens.PaletteScreen;
import view.screens.PenScreen;
import view.turtle.Turtle;

/**
 * Displays current turtle's state, as well as has options for loading/saving workspaces
 * and contains the help, pen, settings buttons (the panel under the turtle panel).
 * 
 * @author Katherine Van Dyk
 *
 */
public class StatePanel extends Panel {

    private final String WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    private ModelController CONTROLLER;
    private List<Turtle> TURTLES;
    private TextField fileText;
    private TurtlePanel TURTLE_PANEL;
    private Turtle TURTLE;

    /**
     * Contains current state of turtle and buttons to load and save preferences
     * @author Katherine Van Dyk
     * 
     * @param t
     * @param c
     */
    public StatePanel(Turtle t, ModelController c, List<Turtle> turtles) {
	TURTLE = t;
	CONTROLLER = c;
	TURTLES = turtles;
    }

    /**
     * Constructs all buttons and places them in HBox for display on main screen
     */
    public VBox construct() {
	HBox buttons = new HBox(12, makePaletteButton(), makePenButton(), createHelpButton(), makeOpenButton());
	HBox save = new HBox(12, getFileName(), makeSaveButton());
	return new VBox(12, save, buttons);
    }

    /**
     * Updates active turtle to @param t
     */
    public void updateTurtle(Turtle t) {
	TURTLE = t;
    }

    /**
     * @return Button that opens different pallette options
     */
    private Button makePaletteButton() {
	Button palletteButton = BUTTON.imageButton("/resources/images/palette.png");
	palletteButton.setOnAction(click->{
	    new PaletteScreen(TURTLE.getPaletteMap());
	});
	return palletteButton;
    }

    /**
     * @return Button that opens different pen settings
     */
    private Button makePenButton() {
	Button penButton = BUTTON.imageButton("/resources/images/pen.png");
	penButton.setOnAction(click->{
	    new PenScreen(CONTROLLER, (ArrayList) TURTLES);
	});
	return penButton;
    }

    /**
     * @return Button that opens .logo files and runs them 
     */
    private Button makeOpenButton() {
	FileChooser FileChooser = CHOOSER.fileChooser("Open Logo File");
	Button openButton = BUTTON.imageButton("/resources/images/open.png");
	openButton.setOnAction(click->{
	    File file = FileChooser.showOpenDialog(new Stage());
	    if (file != null)
		try {
		    CONTROLLER.openFile(file);
		} catch (IOException e) {
		    System.out.println("Invalid file");
		}
	});
	return openButton;
    }

    /**
     * @return Text field for entering file name to save
     */
    private TextField getFileName() {
	fileText = TEXT.textField("Workspace name...", "fileLine");
	return fileText;
    }

    /**
     * @return Button for saving current workspace preferences
     */
    private Button makeSaveButton() {
	Button saveButton =  BUTTON.imageButton("/resources/images/save.png");
	saveButton.setOnAction(click->{
	    Writer writer = new Writer(TURTLE, TURTLE_PANEL.getBack());
	    writer.write(fileText.getText());
	});
	return saveButton;
    }

    /**
     * @return Help button that navigates to website for text commands
     */
    private Button createHelpButton() {
	Button HelpButton = BUTTON.imageButton("/resources/images/help.png");
	HelpButton.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI(WEBSITE));
	} catch (IOException | URISyntaxException e) {
	    System.out.println("IOException");
	}});
	return HelpButton;
    }
}
