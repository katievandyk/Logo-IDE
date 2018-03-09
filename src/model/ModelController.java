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
import model.parser.NewCommandCreator;
import model.parser.NewParser;
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
		if(command != null) {
		    LinkedList<State> states = new LinkedList<>();
			try {
			    states.addAll(command.execute(lastState));
			} catch (CommandException e) {
			    String error = e.getMessage();
			    viewController.sendError(error);
			}
			lastState = states.getLast();
			ArrayList<State> myDuplicateStates = new ArrayList<State>();
			for (int i = 0; i < states.size()-1; i += 1) {
			    if (states.get(i).equals(states.get(i+1))) {
				myDuplicateStates.add(states.get(i));
			    }
			}
			for (State state : myDuplicateStates) {
			    states.remove(state);
			} 
		    viewController.updateTurtle(states);
		}
		else {
		    viewController.sendError("Invalid command");
		}
	}
    }

    /**
     * @param file
     */
    public void openFile(File file) {
	try (Scanner scanner = new Scanner(file)) {
	    String text = new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
	    update(text);
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
