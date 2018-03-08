package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class AskWith extends Command {
	double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();

		ArrayList<Integer> ids = new ArrayList<Integer>();
		returnval = 0;
		try {
			for (Command c : (ListOpen) commands.get(0)) {
				for (int id : turtles) {
					c.execute(turtles.getPreviousState(id));
					if ((int) c.getReturnValue() == 1) {
						ids.add(id);
					}
				}
			}
		}
		catch(Exception e) {
			throw new CommandException("List expected after Tell command!");
		}
		
		turtles.addTempTurtle(ids);
		
		states = commands.get(1).execute(states);
		returnval = commands.get(1).getReturnValue();
		
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	protected void validate() throws CommandException {
		// TODO Auto-generated method stub
		
	}

}