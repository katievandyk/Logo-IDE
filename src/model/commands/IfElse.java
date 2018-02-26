package model.commands;

import java.util.List;

import model.state.State;

public class IfElse extends Command {

	private double returnval;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states = commands.get(0).execute(states);
		returnval = 0;
		if (commands.get(0).getReturnValue() != 0) {
			for (int j = 1; j < commands.size(); j++) {
				states = (commands.get(j).execute(states));
				returnval = commands.get(j).getReturnValue();
			}
		}
		else {
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
	protected void validate() throws CommandException {
	}

}
