package model.commands.control;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class Ask extends TurtleManager{

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		initialize();
		states = super.execute(states);
		
		return addExecute(states);
	}

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(0) instanceof ListOpen)) {
			throw new CommandException("List input expected in first argument of ask");
		}
		else if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of ask");
		}
	}

}
