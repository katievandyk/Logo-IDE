package model;

import view.ViewController;
import model.state.State;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.Group;
import model.commands.Command;
import model.commands.CommandException;
import model.parser.NewCommandCreator;
import model.parser.NewParser;

/**
 * Handles updating turtles state from user input
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @date 2/25/18
 *
 */
public class ModelController{
	private NewParser Parser;
	private State lastState; 
	private ViewController viewController;
	private String currentLanguage;
	NewCommandCreator myCreator;

	public ModelController() {
		Parser = new NewParser();
		Parser.addPatterns("resources.languages.English");
		lastState = new State();
		viewController = new ViewController();

	}

	public Group getScreen(int width, int height) {
		return viewController.getPane(width, height);
	}

	public void initialize() {
		viewController.initialize(this, Parser.getCommandDictionary(), Parser.getVariableDictionary(), Parser.getTurtleList());
	}

	public void update(String currentInput) {
		Parser.setString(currentInput);
		try {
			Parser.parse();
		} catch (CommandException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1 ) {
			viewController.sendError(e1.getMessage());
		}
		while(Parser.hasNext()){
			try {
				Parser.createTopLevelCommand();
			} catch (CommandException e1) {
				viewController.sendError(e1.getMessage());
			}
			Command command = Parser.getCommand();
			LinkedList<State> states = new LinkedList<>();
			try {
				states.addAll(command.execute(lastState));
			} catch (CommandException e) {
				String error = e.getMessage();
				viewController.sendError(error);
			}
			lastState = states.getLast();
			viewController.updateTurtle(states);
		}
	}

	/**
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

	public void updateLanguage(String current) {
		currentLanguage = current;
		Parser.addPatterns(currentLanguage);
	}

	public void toggleTurtle(double x, double y) {
		viewController.toggleTurtle(x,y);
	}

}
