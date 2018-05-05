package model.commands.set;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class ClearStamps extends Set{
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states.get(states.size()-1).clearStamp();
		return super.execute(states);
	}
	
	@Override
	protected State setNextState(State nextState) {
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return 0;
	}

}
