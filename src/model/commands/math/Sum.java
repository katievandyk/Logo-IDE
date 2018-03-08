package model.commands.math;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.commands.set.Forward;
import model.state.State;

public class Sum extends Math {

	@Override
	public double getReturnValue() {
		return parameters.get(0)+parameters.get(1);
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}
	
	@Override
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		double val = 0;
		for (Command c : groupCommands) {
			states = c.execute(states);
			val += c.getReturnValue();
		}
		clearParameters();
		parameters.add(val);
		parameters.add(0.0);
		return states;
	}
	
	

}
