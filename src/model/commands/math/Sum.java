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
		states = groupCommands.get(0).execute(states);
		for (int i = 1; i < groupCommands.size()-1; i++) {
			Sum f = new Sum();
			f.addtoCommands(groupCommands.get(i));
			f.addtoCommands(groupCommands.get(i+1));
			states = f.execute(states);
		}
		return states;
	}
	
	

}
