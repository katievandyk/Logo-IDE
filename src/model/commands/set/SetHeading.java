package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class SetHeading extends Set {
	private double angleChange;

	@Override
	protected State setNextState(State nextState) {
		angleChange = nextState.setAngle(parameters.get(0));
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return angleChange;
	}

	@Override
	protected void validate() throws CommandException {
		if (commands.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + commands.size());
		}
	}

}
