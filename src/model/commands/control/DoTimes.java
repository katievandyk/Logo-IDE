package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class DoTimes extends Command {

	public double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		
		ListOpen list = (ListOpen) commands.get(0);
		String varName = ((StringVar) list.get(0)).getString();
		
		states = list.get(1).execute(states);
		parameters.add(list.get(1).getReturnValue());
		
		validate();
		
		returnval = 0;
		for (int i = 1; i <= parameters.get(0); i++) {
			variableDictionary.addVariable(varName, (double) i);
			states = commands.get(1).execute(states);
			returnval = commands.get(1).getReturnValue();
		}
		
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.get(0) <= 0) {
			throw new CommandException("Negative argument given");
		}
		else if (!(commands.get(0) instanceof ListOpen)) {
			throw new CommandException("List input expected in first argument of dotimes");
		}
		else if (commands.get(0).size() != 2) {
			throw new CommandException("Expected two arguments in first argument of dotimes");
		}
		else if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of dotimes");
		}
	}
}
