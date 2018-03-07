package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class ParenOpen extends Command {
	private double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		returnval = 0;
		
		Command mainCommand = commands.get(0);
		states = mainCommand.groupExecute(states, commands);
		returnval = mainCommand.getReturnValue();
		
		int numInputs = mainCommand.size();
		
		for (Command c : commands.subList( 1, commands.size())) {
			states = c.execute(states);
			returnval = c.getReturnValue();
		}
		mainCommand.groupExecute(states, commands.subList( 1, commands.size()));
		
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
