package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class Ask extends TurtleManager{

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		initialize();
		states = super.execute(states);
		turtles.addTempTurtle(ids);
		
		states = commands.get(1).execute(states);
		returnval = commands.get(1).getReturnValue();
		
		return states;
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
