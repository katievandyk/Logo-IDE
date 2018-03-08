package model.commands.math;


import model.commands.CommandException;

public class Difference extends Math {

	@Override
	public double getReturnValue() {
		return parameters.get(0)-parameters.stream().skip(1).mapToDouble(Double::doubleValue).sum();
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() < 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
