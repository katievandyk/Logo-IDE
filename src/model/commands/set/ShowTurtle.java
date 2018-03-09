package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class ShowTurtle extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setShowing(true);
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return 1;
	}
}
