package view.panels;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import view.screens.PenScreen;
import view.turtle.Turtle;

public class StatePanel extends Panel {
    
    private final String WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";   
    private Text COLOR;
    private Text xPOS;
    private Text yPOS;
    private Rectangle LINE;
    private ImageView IMAGE;
    private Turtle TURTLE;
    private Controller CONTROLLER;

    /**
     * Contains current state of turtle and buttons to load and save preferences
     * @author Katherine Van Dyk
     * 
     * @param t
     * @param c
     */
    public StatePanel(Turtle t, Controller c) {
	TURTLE = t;
	CONTROLLER = c;
	xPOS = TEXT.styledText("0", "position");
	yPOS = TEXT.styledText("0", "position");
	LINE = createLine(1, TURTLE.getPen().getColor());
	COLOR = TEXT.styledText("Pen: " + getColor(TURTLE.getPen().getColor()) + " " + "1 pt", "label");
	IMAGE = makeImage(TURTLE.image());
    }

    /**
     * Constructs all buttons and places them in HBox for display on main screen
     */
    public HBox construct() {
	HBox buttons = new HBox(12, makePaletteButton(), makePenButton(), createHelpButton(), makeOpenButton());
	HBox save = new HBox(12, getFileName(), makeSaveButton());
	VBox rightSide = new VBox(12, save, buttons);
	HBox currState = new HBox(24, turtleInfo(), penColor(), xyPosition());
	currState.setId("bottompane");
	return new HBox(12, currState, rightSide);
    }
    
    /**
     * Updates active turtle to @param t
     */
    public void updateTurtle(Turtle t) {
	TURTLE = t;
    }

    /**
     * Updates states pane based on new parameters
     * 
     * @param img: New turtle image
     * @param pC: new pen color
     * @param xPos: new x-position
     * @param yPos: new y-position
     */
    public void updatePane(String img, Color pC, double xPos, double yPos) {
	xPOS.setText(""+xPos);
	yPOS.setText(""+yPos);
	LINE.setFill(pC);
	COLOR.setText("Pen: " + getColor(pC) + " " + "1 pt");
	IMAGE.setImage(new Image(getClass().getClassLoader().getResourceAsStream((img))));
    }

    /**
     * @return Button that opens different pallette options
     */
    private Button makePaletteButton() {
	return BUTTON.imageButton("/resources/images/palette.png");
    }

    /**
     * @return Button that opens different pen settings
     */
    private Button makePenButton() {
	Button penButton = BUTTON.imageButton("/resources/images/pen.png");
	penButton.setOnAction(click->{
	    new PenScreen(CONTROLLER, TURTLE);
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
	    if (file != null) CONTROLLER.openFile(file);
	});
	return openButton;
    }

    /**
     * @return Text field for entering file name to save
     */
    private TextField getFileName() {
	return TEXT.textField("Workspace name...", "fileLine");
    }

    /**
     * @return Button for saving current workspace preferences
     */
    private Button makeSaveButton() {
	return BUTTON.imageButton("/resources/images/save.png");
    }

    /**
     * @return VBox of turtle image and ID
     */
    private VBox turtleInfo() {
	Text text = TEXT.styledText("ID: " + "1", "label");
	return new VBox(12, IMAGE, text);
    }

    /**
     * @return Rectangle representing pen color
     */
    private VBox penColor() {
	VBox res = new VBox(12, LINE, COLOR);
	VBox.setMargin(LINE, new Insets(0, 40, 0, 40));
	return res;
    }

    /**
     * @return Position coordinates and labels for current state
     */
    private HBox xyPosition() {
	VBox x = new VBox(12, xPOS, TEXT.styledText("X-Pos", "label"));
	VBox y = new VBox(12, yPOS, TEXT.styledText("Y-Pos", "label"));
	return new HBox(x, y);
    }

    /**
     * @return string representing color value from Color object
     * 
     * @param c: color object to be transformed
     */
    private String getColor(Color c) {
	String hex = String.format( "#%02X%02X%02X", (int)( c.getRed() * 255 ), (int)( c.getGreen() * 255 ), (int)( c.getBlue() * 255 ) );
	for(String key : COLOR_RESOURCES.keySet()) {
	    if(COLOR_RESOURCES.getString(key).equals(hex)) return key;
	}
	return null;
    }

    /**
     * Creates a line based on pen thickness and color to be displayed on current state
     * 
     * @param thickness: thickness of current pen
     * @param c: color of current pen
     */
    private Rectangle createLine(int thickness, Color c) {
	Rectangle pen = new Rectangle(thickness, 45);
	pen.setFill(c);
	return pen;
    }
    
    /**
     * @return Help button that navigates to website for text commands
     */
    private Button createHelpButton() {
	Button HelpButton = BUTTON.imageButton("/resources/images/help.png");
	HelpButton.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI(WEBSITE));
	} catch (IOException | URISyntaxException e) {
	    //   viewController.sendError("IOException");
	}});
	return HelpButton;
    }
}
