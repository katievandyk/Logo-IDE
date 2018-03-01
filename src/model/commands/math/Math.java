package model.commands.math;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class Math extends Command {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		for (Command c : commands) {
			states = (c.execute(states));
			parameters.add(c.getReturnValue());
		}
		validate();
		return states;
	}
}
