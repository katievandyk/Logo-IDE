package model.commands.set;

import model.state.State;

public class PenUp extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setPen(false);
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return 0;
	}
}
