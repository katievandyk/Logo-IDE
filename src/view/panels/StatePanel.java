package view.panels;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");
    private Text COLOR;
    private Text xPOS;
    private Text yPOS;
    private Rectangle LINE;
    private ImageView IMAGE;
    private Turtle TURTLE;
    private Controller CONTROLLER;

    public StatePanel(Turtle t, Controller c) {
	TURTLE = t;
	CONTROLLER = c;
	xPOS = textFactory("0", "position");
	yPOS = textFactory("0", "position");
	LINE = createLine(1, TURTLE.getPen().getColor());
	COLOR = colorText(TURTLE.getPen().getColor());
	IMAGE = makeImage(TURTLE.image());
    }

    public void updateTurtle(Turtle t) {
	TURTLE = t;
    }

    public HBox construct() {
	HBox buttons = new HBox(12, makePaletteButton(), makePenButton(), createHelpButton(), makeOpenButton());
	HBox save = new HBox(12, getFileName(), makeSaveButton());
	VBox rightSide = new VBox(12, save, buttons);
	HBox currState = new HBox(24, turtleInfo(), penColor(), xPosition(),yPosition());
	currState.setId("bottompane");
	return new HBox(12, currState, rightSide);
    }

    public void updatePane(String img, Color pC, double xPos, double yPos) {
	xPOS.setText(""+xPos);
	yPOS.setText(""+yPos);
	LINE.setFill(pC);
	COLOR.setText("Pen: " + getColor(pC) + " " + "1 pt");
	IMAGE.setImage(new Image(getClass().getClassLoader().getResourceAsStream((img))));
    }

    private Button makePaletteButton() {
	return buttonFactory("/resources/images/palette.png");
    }

    private Button makePenButton() {
	Button penButton = buttonFactory("/resources/images/pen.png");
	penButton.setOnAction(click->{
	    new PenScreen(CONTROLLER, TURTLE);
	});
	return penButton;
    }

    private Button makeOpenButton() {
	FileChooser FileChooser = new FileChooser();
	FileChooser.setTitle("Open Logo File");
	Button openButton = buttonFactory("/resources/images/open.png");
	openButton.setOnAction(click->{
	    File file = FileChooser.showOpenDialog(new Stage());
	    if (file != null) openFile(file);
	});
	return openButton;
    }

    private void openFile(File file) {
	try (Scanner scanner = new Scanner(file)) {
	    while (scanner.hasNextLine())
		CONTROLLER.update(scanner.nextLine());
	} catch (FileNotFoundException e) {
	    //TODO
	    e.printStackTrace();
	}
    }

    private TextField getFileName() {
	TextField file = new TextField();
	file.setPromptText("Workspace name...");
	file.setId("fileLine");
	return file;
    }

    private Button makeSaveButton() {
	return buttonFactory("/resources/images/save.png");
    }

    private VBox turtleInfo() {
	Text text = textFactory("ID: " + "1", "label");
	return new VBox(12, IMAGE, text);
    }

    private ImageView makeImage(String img) {
	Image im = new Image(getClass().getClassLoader().getResourceAsStream((img)));
	return new ImageView(im);
    }

    private VBox penColor() {
	VBox res = new VBox(12, LINE, COLOR);
	VBox.setMargin(LINE, new Insets(0, 40, 0, 40));
	return res;
    }

    private Text colorText(Color c) {
	String color = getColor(c);
	Text text = textFactory("Pen: " + color + " " + "1 pt", "label");
	return text;
    }

    private VBox xPosition() {
	Text text = textFactory("X-Pos", "label");
	return new VBox(12, xPOS, text);
    }

    private VBox yPosition() {
	Text text = textFactory("Y-Pos", "label");
	return new VBox(12, yPOS, text);
    }

    private String getColor(Color c) {
	String hex = String.format( "#%02X%02X%02X", (int)( c.getRed() * 255 ), (int)( c.getGreen() * 255 ), (int)( c.getBlue() * 255 ) );
	for(String key : COLOR_RESOURCES.keySet()) {
	    if(COLOR_RESOURCES.getString(key).equals(hex)) return key;
	}
	return null;
    }

    private Rectangle createLine(int thickness, Color c) {
	Rectangle pen = new Rectangle(thickness, 45);
	pen.setFill(c);
	return pen;
    }

    private Button createHelpButton() {
	Button HelpButton = buttonFactory("/resources/images/help.png");
	HelpButton.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI(WEBSITE));
	} catch (IOException | URISyntaxException e) {
	    //   viewController.sendError("IOException");
	}});
	return HelpButton;
    }
}
