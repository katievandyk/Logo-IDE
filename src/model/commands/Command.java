package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.dictionaries.CommandDictionary;
import model.dictionaries.VariableDictionary;
import model.state.State;


public abstract class Command {
	public List<Command> commands;
	public List<Double> parameters;
	public VariableDictionary variableDictionary;
	public CommandDictionary commandDictionary;

	
	public Command() {
		commandDictionary = new CommandDictionary();
		variableDictionary = new VariableDictionary();
	}
	
	public abstract List<State> execute(List<State> states) throws CommandException;
	
	public List<State> execute(State initialState) throws CommandException {
		List<State> states = new LinkedList<State>();
		states.add(initialState);
		return execute(states);
	}
	
	public abstract double getReturnValue();
	
	protected abstract void validate() throws CommandException;
	
	protected void clearParameters() {
		parameters.clear();
	}
	
	public void setDictionaries(VariableDictionary v, CommandDictionary c) {
	    
		
	}
	
}
