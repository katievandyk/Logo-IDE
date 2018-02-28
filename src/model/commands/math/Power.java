package model.commands.math;

import model.commands.CommandException;

public class Power extends Math{

	@Override
	public double getReturnValue() {
		return java.lang.Math.pow(parameters.get(0), parameters.get(1));
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}
}
