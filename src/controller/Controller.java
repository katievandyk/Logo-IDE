package controller;

import view.panels.ControlPanel;
import view.ViewController;
import model.state.State;
import java.util.LinkedList;

import model.commands.Command;
import model.parser.Parser;

/**
 * Handles updating turtles state from user input
 * 
 * @author Katherine Van Dyk
 * @date 2/25/18
 *
 */
public class Controller{
    private String currentInput;
    private Parser Parser;
    private State lastState;
    private ViewController ViewController;
    

    public Controller() {
	Parser = new Parser();
	lastState = new State();
	ViewController = new ViewController();
	
    }
    
    public void update(ControlPanel cpanel) {
    	currentInput = cpanel.getInput();
    	/*LinkedList<Command> commands = Parser.getCommands(currentInput);
    	LinkedList<State> states = new LinkedList<>();
    	for(Command c : commands) {
    	    states.addAll(c.execute(lastState));
    	    lastState = states.getLast();
    	}*/
    	LinkedList<State> states = new LinkedList<>();
    	for(int i = 0; i < 10; i++) {
    	    states.add(new State(200 + 20*i, 200 + 40*i, 10*i, true, true));
    	}
    	
    }
   
}
