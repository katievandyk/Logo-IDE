package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class AskWith extends TurtleManager {

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		initialize();
		validate();
		for (Command c : (ListOpen) commands.get(0)) {
			for (int id : turtles) {
				c.execute(turtles.getPreviousState(id));
				addWithCondition(id, c.getReturnValue());
			}
		}
		return addExecute(states);
	}

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(0) instanceof ListOpen)) {
			throw new CommandException("List input expected in first argument of askwith");
		}
		else if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of askwith");
		}
	}
	
	private void addWithCondition(int id, double condtition) {
		if ((int) condtition == 1) {
			ids.add(id);
		}
	}

}
