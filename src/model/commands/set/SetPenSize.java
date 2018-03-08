package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class SetPenSize extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setPensize(parameters.get(0).intValue());
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	protected void validate() throws CommandException {
	}

}
