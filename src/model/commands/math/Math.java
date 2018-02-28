package model.commands.math;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class Math extends Command {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		for (ArrayList<Command> commandList : commands) {
			states = (commandList.get(0).execute(states));
			parameters.add(commandList.get(0).getReturnValue());
		}
		validate();
		return states;
	}
}
