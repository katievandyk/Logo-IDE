package model.commands.math;

import model.commands.CommandException;

public class NaturalLog extends Math{

	@Override
	public double getReturnValue() {
		return java.lang.Math.log(parameters.get(0));
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		if (parameters.get(0) <= 0) {
			throw new CommandException("Illegal negative parameter in Log: " + parameters.get(0));
		}
	}
}
