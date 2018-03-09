package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class HideTurtle extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setShowing(false);
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return 0;
	}
}
