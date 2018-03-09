package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class For extends Command {

	public double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		validate();
		ListOpen list = (ListOpen) commands.get(0);
		String varName = ((StringVar) list.get(0)).getString();
		for (int i = 1; i <= 3; i++) {
			states = (list.get(i).execute(states));
			parameters.add(list.get(i).getReturnValue());
		}
		for (double i = parameters.get(0); i <= parameters.get(1); i += parameters.get(2)) {
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
		if (!(commands.get(0) instanceof ListOpen)) {
			throw new CommandException("List input expected in first argument of for");
		}
		else if (commands.get(0).size() != 4) {
			throw new CommandException("Expected four arguments in first argument of for");
		}
		else if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of for");
		}
	}
}
