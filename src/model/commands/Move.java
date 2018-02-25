package model.commands;

import java.util.List;

import model.instructions.Instruction;
import model.instructions.MoveInstruction;

import java.util.LinkedList;

public class Move extends Command{

	@Override
	public List<Instruction> execute() throws CommandException{
		clearParameters();
		List<Instruction> instructions = new LinkedList<Instruction>();
		instructions.addAll(commands.get(0).execute());
		parameters.add(commands.get(0).getReturnValue());
		validate();
		Instruction move = new MoveInstruction();
		move.param = parameters.get(0);
		instructions.add(move);
		return instructions;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	public void validate() throws CommandException {
		if (commands.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
