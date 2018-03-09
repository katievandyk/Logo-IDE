package model.commands.set;

import model.state.State;

public class PenDown extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setPen(true);
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return 1;
	}
}
