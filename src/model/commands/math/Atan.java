package model.commands.math;

import model.commands.CommandException;

public class Atan extends Math {

	@Override
	public double getReturnValue() {
		return java.lang.Math.toDegrees(java.lang.Math.atan(parameters.get(0)));
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
