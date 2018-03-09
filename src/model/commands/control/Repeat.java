package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class Repeat extends Command {

	private double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		states = commands.get(0).execute(states);
		parameters.add(commands.get(0).getReturnValue());
		validate();
		returnval = 0;
		for (int i = 1; i <= parameters.get(0); i++) {
			variableDictionary.addVariable(":repcount", (double) i);
			states = commands.get(1).execute(states);
			returnval = commands.get(1).getReturnValue();	
		}
		
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments in Repeat: " + parameters.size());
		}
		else if (parameters.get(0) <= 0) {
			throw new CommandException("Negative argument given");
		}
		else if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of repeat");
		}
	}
}
