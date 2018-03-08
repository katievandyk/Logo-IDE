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
import java.util.Scanner;

import javafx.scene.Group;
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
public class ModelController{
    private Parser Parser;
    private State lastState; 
    private ViewController viewController;
    private String currentLanguage;
    CommandCreator myCreator;

    public ModelController() {
	Parser = new Parser();
	Parser.addPatterns("resources.languages.English");
	lastState = new State();
	viewController = new ViewController();
	myCreator = new CommandCreator(Parser.getCommands());

    }

    public Group getScreen(int width, int height) {
	return viewController.getPane(width, height);
    }

    public void initialize() {
	viewController.initialize(this, myCreator.getCommandDictionary(), myCreator.getVariableDictionary(), myCreator.getTurtleList());
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
		ArrayList<State> myDuplicateStates = new ArrayList<State>();
		for (int i = 0; i < states.size()-1; i += 1) {
		    if (states.get(i).equals(states.get(i+1))) {
			System.out.println("duplicate found");
			myDuplicateStates.add(states.get(i));
		    }
		}
		System.out.println("number of duplicated states: " + myDuplicateStates.size());
		for (State state : myDuplicateStates) {
		    states.remove(state);
		}
		for (State state : states) {
		    System.out.println("here 1 " + state.toString());
		}
		System.out.println("here 2 "+ lastState);
	    } 
	    viewController.updateTurtle(states); //this used to be inside for loop
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

    public void toggleTurtle(double x, double y) {
	viewController.toggleTurtle(x,y);
    }

}
