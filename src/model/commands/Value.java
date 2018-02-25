package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.instructions.Instruction;

public class Value extends Command {

	@Override
	public List<Instruction> execute() throws CommandException {
		validate();
		return new LinkedList<Instruction>();
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments");
		}
	}

}
