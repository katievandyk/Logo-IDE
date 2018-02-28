package model.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.dictionaries.CommandDictionary;
import model.dictionaries.VariableDictionary;
import model.state.State;


public abstract class Command {
	public ArrayList<ArrayList<Command>> commands;
	public List<Double> parameters;
	public VariableDictionary variableDictionary;
	public CommandDictionary commandDictionary;

	
	public Command() {
		commands = new ArrayList<ArrayList<Command>>();
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
	
	protected void clearParameters() {
		parameters.clear();
	}
	
	public void setDictionaries(VariableDictionary v, CommandDictionary c) {
	    variableDictionary = v;
	    commandDictionary = c;	
	}
	
	public void addtoCommands(List<Command> commandList) {
		commands.add((ArrayList<Command>) commandList);
	}
	
}
