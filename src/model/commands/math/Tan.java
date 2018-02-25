package model.commands.math;

import model.commands.CommandException;

public class Tan extends Math {

	@Override
	public double getReturnValue() {
		return java.lang.Math.tan(java.lang.Math.toRadians(parameters.get(0)));
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		if (parameters.get(0) >= 90 || parameters.get(0) <= -90) {
			throw new CommandException("Parameter out of bounds: " + parameters.get(0));
		}
	}

}
