package model.commands;

import java.util.List;

import model.state.State;

public class Repeat extends Command {

	public double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states = (commands.get(0).execute(states));
		parameters.add(commands.get(0).getReturnValue());
		
		returnval = 0;
		for (int i = 0; i < parameters.get(0); i++) {
			for (int j = 1; j < commands.size(); j++) {
				states = (commands.get(j).execute(states));
				returnval = commands.get(j).getReturnValue();
			}
			
		}
		validate();
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		else if (parameters.get(0) <= 0) {
			throw new CommandException("Negative argument given");
		}
	}

}
