package model.commands.math;

import model.commands.CommandException;

public class Remainder extends Math {

	@Override
	public double getReturnValue() {
		return parameters.get(0) % parameters.get(1);
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		if (parameters.get(1) == 0) {
			throw new CommandException("Divide by zero");
		}
	}

}
