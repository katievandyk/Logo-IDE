package model.commands.set;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class Towards extends Set {
	
	double angleChange;
	State currentState;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		currentState = states.get(states.size()-1);
		return super.execute(states);
	}

	@Override
	protected State setNextState(State nextState) {
		angleChange = nextState.setAngle(Math.toDegrees(Math.atan((currentState.getY()-parameters.get(1))/(currentState.getX()-parameters.get(0)))));
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
