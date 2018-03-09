package view.save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import view.turtle.Turtle;

public class Writer {

    private final ResourceBundle WORKSPACE_RESOURCES = ResourceBundle.getBundle("resources/settings/workspace");
    private final String PATH = "./src/resources/savedWorkspaces/";
    private Turtle TURTLE;
    private Color BACKGROUND;

    public Writer(Turtle t, Color backgroundColor) {
	TURTLE = t;
	BACKGROUND = backgroundColor;
    }

    public void write(String filename) {
	String content = "";
	String[] properties = WORKSPACE_RESOURCES.keySet().toArray(new String[WORKSPACE_RESOURCES.keySet().size()]);
	content +=  properties[0] + " = " + BACKGROUND + "\n";
	content +=  properties[1] + " = " + TURTLE.getPen().getColor() + "\n";
	content +=  properties[2] + " = " + TURTLE.getPen().getThickness() + "\n";
	content +=  properties[3] + " = " + TURTLE.penUp() + "\n";
	content +=  properties[4] + " = " + TURTLE.image() + "\n";
	SaveFile(content, new File(PATH + filename + ".txt"));
    }


    /**
     * http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html
     * 
     * @param content
     * @param file
     */
    private void SaveFile(String content, File filename){
	    System.out.println(filename);
	    System.out.println(content);
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
