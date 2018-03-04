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
		ArrayList<Command> list = null;
		
		try {
			list = (ArrayList<Command>) ((ListOpen) commands.get(0)).getCommands();
		}
		catch(Exception e) {
			throw new CommandException("List expected after Tell command!");
		}
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		returnval = 0;
		for (Command c : list) {
			states = c.execute(states);
			int id = ((int) c.getReturnValue());
			ids.add(id);
			returnval = c.getReturnValue();
		}
		if (ids.size() == 1) {
			turtles.addTurtles(ids.get(0));
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
