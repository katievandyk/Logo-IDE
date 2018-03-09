package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class SetBackground extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setBackground(parameters.get(0).intValue());
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}
}
