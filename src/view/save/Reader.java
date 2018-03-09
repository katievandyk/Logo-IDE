package view.save;

import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import java.util.Scanner;

import view.panels.TurtlePanel;
import view.turtle.Turtle;

public class Reader {
    private Turtle TURTLE;
    private TurtlePanel TURTLE_PANEL;
    private final ResourceBundle WORKSPACE_RESOURCES = ResourceBundle.getBundle("resources/settings/workspace");

    public Reader(Turtle t, TurtlePanel tp) {
	TURTLE = t;
	TURTLE_PANEL = tp;
    }

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

    private void processLine(String line) {
	if(line.contains(WORKSPACE_RESOURCES.getString("background"))) TURTLE_PANEL.changeBack(getColor(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("pencolor"))) TURTLE.getPen().setColor(getColor(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("penthickness"))) TURTLE.getPen().setThickness(getValue(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("turtleimage"))) TURTLE.changeImage(getValue(line));
	else if(line.contains(WORKSPACE_RESOURCES.getString("penup"))) TURTLE.penUp(getBoolean(line));
    }

    private String getValue(String l) {
	String[] split = l.split("= ");
	return split[1];
    }

    private boolean getBoolean(String l) {
	if(getValue(l).equals("true")) return true;
	return false;
    }

    private Color getColor(String l) {
	String[] color = l.split("= ");
	return Color.web(color[1]);
    }
}
