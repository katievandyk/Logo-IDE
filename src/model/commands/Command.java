package model.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.state.State;


public abstract class Command {
	protected List<Command> commands;
	protected List<Double> parameters;
	//public VarDict vars;
	//public CommandDict custom;
	
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
	
	protected void clearParameters() {
		parameters.clear();
	}
	
	/*public setDictionaries(Vardict, commanddirc) {
		
	}*/
	
}
