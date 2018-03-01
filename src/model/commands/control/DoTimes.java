package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class DoTimes extends Command {

	public double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		String varName = null;
		try {
			varName = ((StringVar) commands.get(0).get(0)).getString();
		}
		catch(Exception e) {
			throw new CommandException("Syntax error: variable expected");
		}
		
		states = (commands.get(0).get(1).execute(states));
		parameters.add(commands.get(0).get(1).getReturnValue());
		
		returnval = 0;
		for (int i = 1; i <= parameters.get(0); i++) {
			variableDictionary.addVariable(varName, (double) i);
			for (Command c :commands.get(1)) {
				states = c.execute(states);
				returnval = c.getReturnValue();
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
