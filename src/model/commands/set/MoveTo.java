package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class MoveTo extends Set {
	
	@Override
	protected State setNextState(State nextState) {
		nextState.setXY(parameters.get(0), parameters.get(1));
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return Math.sqrt(Math.pow(parameters.get(0),2)+Math.pow(parameters.get(1),2));
	}

	@Override
	public void validate() throws CommandException {
		if (commands.size() != 2) {
			throw new CommandException("Invalid number of commands: " + commands.size());
		}
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}
}
