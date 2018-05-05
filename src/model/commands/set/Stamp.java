package model.commands.set;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class Stamp extends Set {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states.get(states.size()-1).stamp();
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
