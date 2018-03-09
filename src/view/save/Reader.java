package view.save;

import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;

import view.panels.TurtlePanel;
import view.turtle.Turtle;

/**
 * Reads in workspace file and sets attributes such as background color, 
 * pen color/thickness, and turtle image to user-specified values
 * 
 * @author Katherine Van Dyk
 */
public class Reader {
    private Turtle TURTLE;
    private TurtlePanel TURTLE_PANEL;
    private final ResourceBundle WORKSPACE_RESOURCES = ResourceBundle.getBundle("resources/settings/workspace");

    /**
     * Takes in turtle @param t and turtle panel @param tp objects that will be modified
     */
    public Reader(Turtle t, TurtlePanel tp) {
	TURTLE = t;
	TURTLE_PANEL = tp;
    }

    /**
     * Parses file @param f and assigns attributes if file is appropriate format
     */
    public void read(File f) {
	try {
	    Scanner scanner = new Scanner(f);
	    while(scanner.hasNextLine()) {
		processLine(scanner.nextLine());
	    }
	    scanner.close();
	} catch (FileNotFoundException e) {
	    System.out.println("Inappropriate file format");
	}
    }

    /**
     * Processes string line of file (@param line) and changes specific attribute
     */
    private void processLine(String line) {
	if(line.contains(WORKSPACE_RESOURCES.getString("background"))) TURTLE_PANEL.changeBack(getColor(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("pencolor"))) TURTLE.getPen().setColor(getColor(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("penthickness"))) TURTLE.getPen().setThickness(getValue(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("turtleimage"))) TURTLE.changeImage(getValue(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("penup"))) TURTLE.penUp(getBoolean(line));
    }

    /**
     * @return value of specific attribute from line @param l of file
     */
    private String getValue(String l) {
	String[] split = l.split("= ");
	return split[1];
    }

    /**
     * Converts string boolean to actual boolean in line @param l of file
     */
    private boolean getBoolean(String l) {
	if(getValue(l).equals("true")) return true;
	return false;
    }

    /**
     * Converts string color to color object in line @param l of file
     */
    private Color getColor(String l) {
	String[] color = l.split("= ");
	return Color.web(color[1]);
    }
}
