package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

/**
 * @author Martin
 * 
 * TurtleManager commands are used to create new turtles and to control which turtles are active.
 *
 */
public abstract class TurtleManager extends Command {
	
	protected double returnval;
	protected ArrayList<Integer> ids;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		validate();
		for (Command c : (ListOpen) commands.get(0)) {
			states = c.execute(states);
			int id = ((int) c.getReturnValue());
			ids.add(id);
			returnval = c.getReturnValue();
		}
		return states;
	}
	
	protected List<State> addExecute(List<State> states) throws CommandException {
		turtles.addTempTurtle(ids);
		states = commands.get(1).execute(states);
		returnval = commands.get(1).getReturnValue();
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
