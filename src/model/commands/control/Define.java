package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class Define extends Command {
	private String commandName;
	private double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		returnval = 0;
		clearParameters();
		validate();
		
		commandName = ((StringCommand) commands.get(0)).getString();
		commandDictionary.defineCommand(commandName, (ListOpen) commands.get(1));
		returnval = 1;
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(0) instanceof StringCommand)) {
			throw new CommandException("Custom command name expected in first argument of repeat");
		}
		else if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of repeat");
		}
	}

}
