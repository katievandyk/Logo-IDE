package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class TurtleManager extends Command {
	
	protected double returnval;
	protected ArrayList<Integer> ids;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		
		try {
			for (Command c : (ListOpen) commands.get(0)) {
				states = c.execute(states);
				int id = ((int) c.getReturnValue());
				ids.add(id);
				returnval = c.getReturnValue();
			}
		}
		catch(Exception e) {
			throw new CommandException("List expected after Tell command!");
		}
		
		return states;
	}
	
	protected void initialize() {
		clearParameters();
		returnval = 0;
		ids = new ArrayList<>();
	}
	
	@Override
	public double getReturnValue() {
		return returnval;
	}

}
