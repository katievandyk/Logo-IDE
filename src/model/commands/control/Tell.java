package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class Tell extends Command {
	double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();

		ArrayList<Integer> ids = new ArrayList<Integer>();
		returnval = 0;
		try {
			for (Command c : (ListOpen) commands.get(0)) {
				if (!(c instanceof ListClose)) {
					states = c.execute(states);
					int id = ((int) c.getReturnValue());
					ids.add(id);
					returnval = c.getReturnValue();
				}
			}
		}
		catch(Exception e) {
			throw new CommandException("List expected after Tell command!");
		}
		
		if (ids.size() == 1 && !turtles.contains(ids.get(0))) {
			states.addAll(turtles.addTurtles(ids.get(0)));
		}
		turtles.setActiveTurtles(ids);
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
