package view.save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import view.turtle.Turtle;

/**
 * Saves the current workspace to a text file so that it can be later read and used to 
 * set workspace preferences, such as background color, pen color/thickness, turtle image,
 * etc.
 * 
 * @author Katherine Van Dyk
 */
public class Writer {

    private final ResourceBundle WORKSPACE_RESOURCES = ResourceBundle.getBundle("resources/settings/workspace");
    private final String PATH = "./src/resources/savedWorkspaces/";
    private Turtle TURTLE;
    private Color BACKGROUND;

    /**
     * Constructor for writer that takes in turtle object @param t and 
     * @param backgroundColor to be saved
     */
    public Writer(Turtle t, Color backgroundColor) {
	TURTLE = t;
	BACKGROUND = backgroundColor;
    }

    /**
     * Writes current parameters to file with name @param filename
     */
    public void write(String filename) {
	String content = "";
	content +=  WORKSPACE_RESOURCES.getString("background") + " = " + BACKGROUND + "\n";
	content +=  WORKSPACE_RESOURCES.getString("pencolor")  + " = " + TURTLE.getPen().getColor() + "\n";
	content +=  WORKSPACE_RESOURCES.getString("penthickness")  + " = " + TURTLE.getPen().getThickness() + "\n";
	content +=  WORKSPACE_RESOURCES.getString("penup")  + " = " + TURTLE.penUp() + "\n";
	content +=  WORKSPACE_RESOURCES.getString("turtleimage")  + " = " + TURTLE.image() + "\n";
	SaveFile(content, new File(PATH + filename + ".txt"));
    }


    /**
     * Saves @param file with file content @param content  to specified directory
     * Source: http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html
     */
    private void SaveFile(String content, File filename){
	try {
	    FileWriter fileWriter = null;
	    fileWriter = new FileWriter(filename);
	    fileWriter.write(content);
	    fileWriter.close();
	} catch (IOException ex) {
	    System.out.println("Can't write file.");
	}
    }
}
