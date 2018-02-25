package model.commands.math;

import java.util.Random;

import model.commands.CommandException;

public class Rand extends Math {

	@Override
	public double getReturnValue() {
		Random r = new Random();
		return r.nextDouble()*parameters.get(0);
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		if (parameters.get(0) < 0) {
			throw new CommandException("Negative value not allowed in Random: " + parameters.get(0));
		}
	}

}
