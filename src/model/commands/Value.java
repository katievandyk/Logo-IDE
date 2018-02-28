package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.state.State;

public class Value extends Command {

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		validate();
		return states;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments");
		}
	}
	
	public void setValue(double val) {
		parameters.add(val);
	}

}
