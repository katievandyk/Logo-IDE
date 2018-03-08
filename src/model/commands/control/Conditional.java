package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class Conditional extends Command {
	protected double returnval;
	protected boolean runElse;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		validate();
		runElse = true;
		states = commands.get(0).execute(states);
		if (commands.get(0).getReturnValue() != 0) {
			runElse = false;
			states = commands.get(1).execute(states);
			returnval = commands.get(1).getReturnValue();
		}
		return states;
	}
	
	@Override
	public double getReturnValue() {
		return returnval;
	}
}
