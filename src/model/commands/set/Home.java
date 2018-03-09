package model.commands.set;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class Home extends Set {
	State currentState;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		currentState = states.get(states.size()-1);
		return super.execute(states);
	}
	
	@Override
	protected State setNextState(State nextState) {
		nextState.setXY(0, 0);
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return Math.sqrt(Math.pow(currentState.getX(),2)+Math.pow(currentState.getY(),2));
	}
}
