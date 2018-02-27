package model.commands.set;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class Set extends Command {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		for (Command c : commands) {
			states = (c.execute(states));
			parameters.add(c.getReturnValue());
		}
		validate();
		State nextState = new State(states.get(states.size()-1));
		states.add(setNextState(nextState));
		return states;
	}
	
	protected abstract State setNextState(State nextState);

}
