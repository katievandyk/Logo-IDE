package model.commands.set;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class MoveTo extends Set {
	
	State currentState;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		currentState = states.get(states.size()-1);
		return super.execute(states);
	}
	
	@Override
	protected State setNextState(State nextState) {
		nextState.setXY(parameters.get(0), parameters.get(1));
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return Math.sqrt(Math.pow(currentState.getX()-parameters.get(0),2)+Math.pow(currentState.getY()-parameters.get(1),2));
	}

	@Override
	public void validate() throws CommandException {
		if (commands.size() != 2) {
			throw new CommandException("Invalid number of commands: " + commands.size());
		}
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}
}
