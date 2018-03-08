package model.commands.set;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class Forward extends Set {
	
	@Override
	protected State setNextState(State nextState) {
		nextState.move(parameters.get(0));
		return nextState;
	}
	
	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	protected void validate() throws CommandException {
		if (commands.size() != 1) {
			throw new CommandException("Invalid number of arguments in Forward: " + commands.size());
		}
	}
	
	@Override
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		double val = 0;
		states = groupCommands.get(0).execute(states);
		for (Command c : groupCommands) {
			states = c.execute(states);
			val += c.getReturnValue();
		}
		clearParameters();
		parameters.add(val);
		parameters.add(0.0);
		return states;
	}
}
