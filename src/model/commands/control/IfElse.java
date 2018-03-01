package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class IfElse extends Command {

	private double returnval;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states = commands.get(0).get(0).execute(states);
		returnval = 0;
		if (commands.get(0).get(0).getReturnValue() != 0) {
			for (Command c : commands.get(1)) {
				states = c.execute(states);
				returnval = c.getReturnValue();
			}
		}
		else {
			for (Command c : commands.get(2)) {
				states = c.execute(states);
				returnval = c.getReturnValue();
			}
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
