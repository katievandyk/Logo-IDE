package model.commands.math;

import model.commands.CommandException;

public class NotEqual extends Math {
	public static final double EQUAL_ERROR = .0000001;
	
	@Override
	public double getReturnValue() {
		return (java.lang.Math.abs(parameters.get(0) - parameters.get(1)) < EQUAL_ERROR) ? 0 : 1;
	}

	@Override
	protected void validate() throws CommandException {
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		
	}

}
