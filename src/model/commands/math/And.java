package model.commands.math;

import model.commands.CommandException;

public class And extends Math {

	@Override
	public double getReturnValue() {
		boolean temp = true;
		for (double d : parameters) {
			temp &= (java.lang.Math.abs(d) > EQUAL_ERROR);
		}
		return temp  ? 1 : 0;
	}

	@Override
	protected void validate() throws CommandException {
		if (parameters.size() < 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		
	}

}
