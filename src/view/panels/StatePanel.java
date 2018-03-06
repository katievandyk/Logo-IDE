package view.panels;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

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
import view.turtle.Turtle;
import view.turtle.TurtlePen;

public class StatePanel {

    private final String WEBSITE = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    private Turtle TURTLE;
    private TurtlePen PEN;    
    private final ResourceBundle COLOR_RESOURCES = ResourceBundle.getBundle("resources/settings/colors");

    public StatePanel(Turtle t) {
	TURTLE = t;
	PEN = TURTLE.getPen();
    }

    public HBox construct() {
	HBox currState = new HBox(24, turtleInfo(), penColor(), xPosition(), yPosition());
	currState.setId("bottompane");
	HBox buttons = new HBox(12, makePaletteButton(), makePenButton(), createHelpButton(), makeOpenButton());
	HBox save = new HBox(12, getFileName(), makeSaveButton());
	VBox rightSide = new VBox(12, save, buttons);
	HBox ret = new HBox(12, currState, rightSide);
	return ret;
    }
    
    private Button makePaletteButton() {
	return buttonFactory("/resources/images/palette.png");
    }
    
    private Button makePenButton() {
	return buttonFactory("/resources/images/pen.png");
    }
    
    private Button makeOpenButton() {
	return buttonFactory("/resources/images/open.png");
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
	Text text = new Text("ID: " + "1");
	text.setId("label");
	Image img = new Image(getClass().getClassLoader().getResourceAsStream(TURTLE.image()));
	return new VBox(12, new ImageView(img), text);
    }

    private VBox penColor() {
	String color = getColor(PEN.getColor());
	Text text = new Text("Pen: " + color + " " + "1 pt");
	text.setId("label");
	Rectangle l =  createLine(1, PEN.getColor());
	VBox res = new VBox(12, l, text);
	VBox.setMargin(l, new Insets(0, 40, 0, 40));
	return res;
    }
    
    private VBox xPosition() {
	Text text = new Text("X-Pos");
	text.setId("label");
	Text pos = new Text(Double.toString(TURTLE.getX()));
	pos.setId("position");
	return new VBox(12, pos, text);
    }
    
    private VBox yPosition() {
	Text text = new Text("Y-Pos");
	text.setId("label");
	Text pos = new Text(Double.toString(TURTLE.getY()));
	pos.setId("position");
	return new VBox(12, pos, text);
    }


    private String getColor(Color c) {
	String hex = String.format( "#%02X%02X%02X",
	            (int)( c.getRed() * 255 ),
	            (int)( c.getGreen() * 255 ),
	            (int)( c.getBlue() * 255 ) );
	for(String key : COLOR_RESOURCES.keySet()) {
	    if(COLOR_RESOURCES.getString(key).equals(hex)) {
		return key;
	    }
	}
	return null;
    }
    
    private Rectangle createLine(int thickness, Color c) {
	Rectangle pen = new Rectangle(thickness, 45);
	pen.setFill(c);
	return pen;
    }
    
    private Button buttonFactory(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
    
    private Button createHelpButton() {
	Button HelpButton = buttonFactory("/resources/images/help.png");
	HelpButton.setOnAction(click->{try {
	    java.awt.Desktop.getDesktop().browse(new URI(WEBSITE));
	} catch (IOException e) {
	//   viewController.sendError("IOException");
	    e.printStackTrace();
	} catch (URISyntaxException e) {
	 //   controller.sendError("URISyntaxException");
	    e.printStackTrace();
	}});
	return HelpButton;
    }
    

}
