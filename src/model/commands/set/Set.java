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
		for (ArrayList<Command> commandList : commands) {
			states = (commandList.get(0).execute(states));
			parameters.add(commandList.get(0).getReturnValue());
		}
		validate();
		State nextState = new State(states.get(states.size()-1));
		states.add(setNextState(nextState));
		return states;
	}
	
	protected abstract State setNextState(State nextState);

}
