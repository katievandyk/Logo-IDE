package model.commands.control;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class IfElse extends Conditional {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states = super.execute(states);
		if (runElse) {
			states = commands.get(2).execute(states);
			returnval = commands.get(2).getReturnValue();
		}
		return states;
	}

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of ifelse");
		}
		else if (!(commands.get(2) instanceof ListOpen)) {
			throw new CommandException("List input expected in third argument of ifelse");
		}
	}

}
