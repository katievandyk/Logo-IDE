package model.commands.math;

import model.commands.CommandException;

public class Equal extends Math {
	
	@Override
	public double getReturnValue() {
		boolean temp = true;
		double first = parameters.get(0);
		for (double d : parameters) {
			temp &= java.lang.Math.abs(first - d) < EQUAL_ERROR;
		}
		return temp  ? 1 : 0;
		//return (java.lang.Math.abs(parameters.get(0) - parameters.get(1)) < EQUAL_ERROR) ? 1 : 0;
	}

	@Override
	protected void validate() throws CommandException {
		if (parameters.size() < 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		
	}

}
