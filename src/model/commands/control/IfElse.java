package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class IfElse extends Command {

	private double returnval;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states = commands.get(0).execute(states);
		if (commands.get(0).getReturnValue() != 0) {
			states = commands.get(1).execute(states);
			returnval = commands.get(1).getReturnValue();
		}
		else {
			states = commands.get(2).execute(states);
			returnval = commands.get(2).getReturnValue();
		}
		validate();
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	protected void validate() throws CommandException {
	}

}
