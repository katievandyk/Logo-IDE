package controller;

import view.panels.ControlPanel;
import model.state.State;
import java.util.LinkedList;

import model.commands.Command;
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
    private String currentInput;
    private Parser Parser;
    private State lastState;
    

    public Controller() {
	Parser = new Parser();
	lastState = new State();
    }
    
    public void update(String current) {
    	currentInput = current;
    	LinkedList<Command> commands = Parser.getCommands(currentInput);
    	LinkedList<State> states = new LinkedList<>();
    	for(Command c : commands) {
    	    states.addAll(c.execute(lastState));
    	    lastState = states.getLast();
    	}
    }
    
    public void connectPanel(ControlPanel cpanel) {
    	cpanel.connectController(this);
    }
   
}
