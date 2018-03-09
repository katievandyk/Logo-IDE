package model.commands.set;

import model.state.State;

public class SetShape extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setShape(parameters.get(0).intValue());
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}
}
