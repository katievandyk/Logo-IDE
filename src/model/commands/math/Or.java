package model.commands.math;

import model.commands.CommandException;

public class Or extends Math {

	@Override
	public double getReturnValue() {
		return ((parameters.get(0) != 0) || (parameters.get(1) != 0)) ? 1 : 0;
	}

	@Override
	protected void validate() throws CommandException {
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		
	}

}
