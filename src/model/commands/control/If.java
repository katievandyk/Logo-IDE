package model.commands.control;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class If extends Conditional {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		return super.execute(states);
	}

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of ask");
		}
	}

}
