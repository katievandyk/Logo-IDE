package model;

import view.ViewController;
import model.state.State;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import javafx.scene.Group;
import model.commands.Command;
import model.commands.CommandException;
import model.parser.Parser;

/**
 * Handles updating turtles state from user input
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @author Eric Fu
 * @date 2/25/18
 *
 */
public class ModelController{
	private Parser myParser;
	private State lastState; 
	private ViewController viewController;
	private String currentLanguage;
	/**
	 * creates new model controller
	 */
	public ModelController() {
		myParser = new Parser();
		myParser.addPatterns("resources.languages.English");
		lastState = new State();
		viewController = new ViewController();

	}
	
	/**
	 * returns the screen of certain size
	 * @param width
	 * @param height
	 * @return
	 */
	public Group getScreen(int width, int height) {
		return viewController.getPane(width, height);
	}

	/**
	 * initializes the view controller
	 */
	public void initialize() {
		viewController.initialize(this, myParser.getCommandDictionary(), myParser.getVariableDictionary(), myParser.getTurtleList());
	}
	/**
	 * Updates the turtle based on the user commands
	 * @param currentInput this is the input string that the parser will interpret
	 */
	public void update(String currentInput) {
		myParser.setString(currentInput);
		try {
			myParser.parse();
			while(myParser.hasNext()){
				myParser.createTopLevelCommand();
				Command command = myParser.getCommand();
				LinkedList<State> states = new LinkedList<>();
				states.addAll(command.execute(lastState));
				lastState = states.getLast();
				viewController.updateTurtle(states);
			}
		} 
		catch (IndexOutOfBoundsException | CommandException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1 ) {
			viewController.sendError(myParser.getErrorMessage(e1));
			System.out.println(e1.getClass().getSimpleName());
		}
	}

	/**
	 * opens a file and iether gives it to the parser or reads in some configurations
	 * Source: https://stackoverflow.com/questions/20637865/javafx-2-2-get-selected-file-extension
	 * @param file
	 * @throws IOException 
	 */
	public void openFile(File file) throws IOException {
		String fileName = file.getName();          
		String fileExtension = fileName.substring(fileName.indexOf(".") + 1, file.getName().length());
		if(fileExtension.equals("txt")) {
			viewController.readFile(file);
		}
		else {
			String text = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
			update(text);
		}
	}
	/**
	 * updates the language for the parser
	 * @param current represents the language that is now being interpreted
	 */
	public void updateLanguage(String current) {
		currentLanguage = current;
		myParser.addPatterns(currentLanguage);
	}
	/**
	 * toggles the turtle by mouseclick
	 * @param x
	 * @param y
	 */
	public void toggleTurtle(double x, double y) {
		viewController.toggleTurtle(x,y);
	}

}
