package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.instructions.Instruction;

public class Repeat extends Command {

	public double returnval;

	@Override
	public List<Instruction> execute() throws CommandException {
		returnval = 0;
		List<Instruction> instructions = new LinkedList<Instruction>();
		for (int i = 0; i < parameters.get(0); i++) {
			for (Command c : commands) {
				instructions.addAll(c.execute());
				returnval = c.getReturnValue();
			}
			
		}
		validate();
		return instructions;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments");
		}
		else if (parameters.get(0) <= 0) {
			throw new CommandException("Negative argument given");
		}
	}

}
