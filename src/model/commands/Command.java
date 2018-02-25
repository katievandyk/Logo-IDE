package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.state.State;


public abstract class Command {
	public List<Command> commands;
	public List<Double> parameters;
	//public VarDict vars;
	//public CommandDict custom;
	
	public Command() {
		commands = new ArrayList<Command>();
		parameters = new ArrayList<Double>();
	}
	
	public abstract List<State> execute(List<State> states) throws CommandException;
	
	public abstract double getReturnValue();
	
	public abstract void validate() throws CommandException;
	
	public void clearParameters() {
		parameters.clear();
	}
	
	/*public setDictionaries(Vardict, commanddirc) {
		
	}*/
	
}
