package model.commands.control;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class Tell extends TurtleManager {

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		initialize();
		validate();
		states = super.execute(states);
		turtles.setActiveTurtles(ids);
		if (ids.size() == 1 && !turtles.contains(ids.get(0))) {
			states.addAll(turtles.addTurtles(ids.get(0)));
		}
		return states;
	}

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(0) instanceof ListOpen)) {
			throw new CommandException("List input expected in first argument of tell");
		}
	}

}
