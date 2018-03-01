package model.commands.control;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class MakeUserInstruction extends Command {
	private String commandName;
	private int returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		validate();
		returnval = 0;
		
		commandName = ((StringCommand) commands.get(0)).getString();
		
		
		commandDictionary.addVariable(commandName, commands.get(1), commands.get(2));
		
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	protected void validate() throws CommandException {
	}
}
