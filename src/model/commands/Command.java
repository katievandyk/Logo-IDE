package model.commands;

import java.util.ArrayList;
import java.util.List;


public abstract class Command {
	public List<Command> commands;
	public List<Double> parameters;
	
	public Command() {
		commands = new ArrayList<Command>();
		parameters = new ArrayList<Double>();
	}
	
	public abstract List<Instruction> execute() throws CommandException;
	
	public abstract double getReturnValue();
	
	public abstract void validate() throws CommandException;
	
	public void clearParameters() {
		parameters.clear();
	}
}
