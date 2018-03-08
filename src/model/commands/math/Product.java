package model.commands.math;

import model.commands.CommandException;

public class Product extends Math {

	@Override
	public double getReturnValue() {
		double product = 1;
		for (double d : parameters) {
			product *= d;
		}
		return product;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
