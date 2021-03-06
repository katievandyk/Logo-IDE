package model.commands.get;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

/**
 * @author Martin
 * 
 * Get commands retrieve a parameter from the most recent state object and return
 * this parameter (except for the ID command, which returns the ID of the most 
 * recently active turtle)
 *
 */
public abstract class Get extends Command {
	protected State currentState;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		currentState = states.get(states.size()-1);
		validate();
		return states;
	}
	
	@Override
	protected void validate() throws CommandException {
		if (commands.size() != 0) {
			throw new CommandException("Invalid number of arguments: " + commands.size());
		}
		if (currentState == null) {
			throw new CommandException("Null state!");
		}
	}
}
