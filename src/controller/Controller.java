package controller;

import view.ViewController;
import model.state.State;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.stage.Stage;
import model.commands.Command;
import model.commands.CommandException;
import model.parser.CommandCreator;
import model.parser.Parser;

/**
 * Handles updating turtles state from user input
 * 
 * @author Katherine Van Dyk
 * @author Brandon Dalla Rosa
 * @date 2/25/18
 *
 */
public class Controller{
    private Parser Parser;
    private State lastState; 
    private ViewController viewController;
    private String currentLanguage;
    CommandCreator myCreator;

    public Controller() {
	Parser = new Parser();
	Parser.addPatterns("resources.languages.English");
	lastState = new State();
	viewController = new ViewController();
	myCreator = new CommandCreator(Parser.getCommands());

    }

    public void initialize(Stage primaryStage) {
	viewController.initialize(primaryStage, this, myCreator.getCommandDictionary(), myCreator.getVariableDictionary());
    }

    public void update(String currentInput) {
	Parser.setString(currentInput);
	Parser.splitInput();
	myCreator.setStringCommands(Parser.getCommands());
	myCreator.setSymbols(Parser.getSymbols());
	myCreator.setStringInput(Parser.getInput());
	try {
	    myCreator.newCommands();
	} catch (CommandException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1 ) {
	    viewController.sendError(e1.getMessage());
	}
	ArrayList<Command> commands = (ArrayList<Command>) myCreator.getCommands();
	if(commands != null) {
	    LinkedList<State> states = new LinkedList<>();
	    for(Command c : commands) {
		try {
		    states.addAll(c.execute(lastState));
		} catch (CommandException e) {
		    String error = e.getMessage();
		    viewController.sendError(error);
		}
		lastState = states.getLast();
		viewController.updateTurtle(states); 
	    } 
	}
	else {
	    viewController.sendError("Invalid command");
	}
    }

	/**
	 * @param file
	 */
	public void openFile(File file) {
	    try (Scanner scanner = new Scanner(file)) {
	    String text = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
	    update(text);
		//while (scanner.hasNextLine())
		//    update(scanner.nextLine());
	    } catch (IOException e) {
		//TODO
		e.printStackTrace();
	    }
	}

    public void updateLanguage(String current) {
	currentLanguage = current;
	Parser.addPatterns(currentLanguage);
    }

}
