package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class Forward extends Set {
	
	@Override
	protected State setNextState(State nextState) {
		nextState.move(parameters.get(0));
		return nextState;
	}
	
	@Override
	public double getReturnValue() {
		System.out.println(parameters.toString());
		return parameters.get(0);
	}

	@Override
	protected void validate() throws CommandException {
		if (commands.size() != 1) {
			throw new CommandException("Invalid number of arguments in Forward: " + commands.size());
		}
	}
}
