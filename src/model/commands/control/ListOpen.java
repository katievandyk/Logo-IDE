package model.commands.control;

import java.util.Iterator;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class ListOpen extends Command {
	private double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		returnval = 0;
		for (Command c : commands) {
			states = c.execute(states);
			returnval = c.getReturnValue();
		}
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	protected void validate() throws CommandException {
	}
	
	public Command get(int index) {
		return commands.get(index);
	}
}
