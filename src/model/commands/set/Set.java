package model.commands.set;

import java.util.ArrayList;
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
		
		for (int id : turtles.getActiveTurtles()) {
			State nextState = new State(turtles.getPreviousState(id));
			nextState = setNextState(nextState);
			turtles.setCurrentState(id, nextState);
			states.add(nextState);
		}
		
		return states;
	}
	
	protected abstract State setNextState(State nextState);

}
