package model.commands.math;

import model.commands.CommandException;

public class Or extends Math {

	@Override
	public double getReturnValue() {
		boolean temp = false;
		for (double d : parameters) {
			temp |= (d != 0);
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
