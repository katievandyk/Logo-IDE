package model.commands.set;

import model.state.State;

public class SetPenColor extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setPencolor(parameters.get(0).intValue());
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}
}
