package controller;

import view.panels.ControlPanel;
import view.ViewController;
import model.state.State;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.stage.Stage;
import model.commands.Command;
import model.commands.CommandException;
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


    public Controller() {
	Parser = new Parser();
	lastState = new State();
	ViewController = new ViewController();

    }

    public void initialize(Stage primaryStage) {
	ViewController.initialize(primaryStage,this);
	update("");
    }

    public void update(String currentInput) {
	LinkedList<Command> commands = (LinkedList<Command>) Parser.getCommands(currentInput);
	LinkedList<State> states = new LinkedList<>();
	for(Command c : commands) {
	    try {
		states.addAll(c.execute(lastState));
	    } catch (CommandException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    lastState = states.getLast();
	}
	ViewController.updateTurtle(states);

    }

}
