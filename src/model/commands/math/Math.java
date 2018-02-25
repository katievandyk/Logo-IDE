package model.commands.math;

import java.util.LinkedList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.instructions.Instruction;

public abstract class Math extends Command {
	
	@Override
	public List<Instruction> execute() throws CommandException {
		clearParameters();
		List<Instruction> instructions = new LinkedList<Instruction>();
		for (Command c : commands) {
			instructions.addAll(c.execute());
			parameters.add(c.getReturnValue());
		}
		validate();
		return instructions;
	}
}
