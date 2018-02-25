package model.commands.math;

import model.commands.CommandException;

public class Pi extends Math{
	@Override
	public double getReturnValue() {
		return java.lang.Math.PI;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 0) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}
}
