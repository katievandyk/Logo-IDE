package controller;

import view.ViewController;
import model.state.State;

import java.util.ArrayList;
import java.util.LinkedList;
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
	private ViewController ViewController;
	private String currentLanguage;


	public Controller() {
		Parser = new Parser();
		Parser.addPatterns("resources.languages.English");
		lastState = new State();
		ViewController = new ViewController();

	}

	public void initialize(Stage primaryStage) {
		ViewController.initialize(primaryStage, this);
	}

	public void sendError(String message) {
		ViewController.sendError(message);
	}

	public void update(String currentInput) {
		Parser.setString(currentInput);
		Parser.splitInput();
		CommandCreator myCreator = new CommandCreator(Parser.getCommands());
		myCreator.setSymbols(Parser.getSymbols());
		myCreator.newCommands();
		ArrayList<Command> commands = (ArrayList<Command>) myCreator.getCommands();
		if(commands != null) {
			LinkedList<State> states = new LinkedList<>();
			for(Command c : commands) {
				try {
				states.addAll(c.execute(lastState));
				} catch (CommandException e) {
					String error = "Wrong input";
					sendError(error);
				}
				lastState = states.getLast();
				ViewController.updateTurtle(states); 
			} 
		}
		else {
			sendError("Invalid command");
		}

	}
	
	public void updateLanguage(String current) {
		currentLanguage = current;
		//ADD FUNCTION TO UPDATE LANGUAGE IN PARSER
	}

}
