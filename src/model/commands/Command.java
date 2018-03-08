package model.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.dictionaries.CommandDictionary;
import model.dictionaries.TurtleList;
import model.dictionaries.VariableDictionary;
import model.state.State;


public abstract class Command implements Iterable<Command> {
	protected ArrayList<Command> commands;
	protected List<Double> parameters;
	protected VariableDictionary variableDictionary;
	protected CommandDictionary commandDictionary;
	protected TurtleList turtles;

	
	public Command() {
		commands = new ArrayList<Command>();
		parameters = new ArrayList<Double>();
	}
	
	public abstract List<State> execute(List<State> states) throws CommandException;
	
	public List<State> execute(State initialState) throws CommandException {
		List<State> states = new LinkedList<State>();
		states.add(initialState);
		return execute(states);
	}
	
	public abstract double getReturnValue();
	
	protected abstract void validate() throws CommandException;
	
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		states = groupCommands.get(0).execute(states);
		return states;
	}
	
	protected void clearParameters() {
		parameters.clear();
	}
	
	protected void clearCommands() {
		commands.clear();
	}
	
	public void setDictionaries(VariableDictionary v, CommandDictionary c, TurtleList t) {
	    variableDictionary = v;
	    commandDictionary = c;	
	    turtles = t;
	}
	
	public void addtoCommands(Command commandList) {
		commands.add(commandList);
	}
	
	@Override
	public Iterator iterator() {
		return commands.iterator();
	}
	
	public int size() {
		return commands.size();
	}
	
}
