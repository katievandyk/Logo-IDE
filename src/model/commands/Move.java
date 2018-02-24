package model.commands;

import java.util.List;

import model.instructions.Instruction;
import model.instructions.MoveInstruction;

import java.util.LinkedList;

public class Move extends Command{
	double dist;

	@Override
	public List<Instruction> execute() {
		List<Instruction> instructions = new LinkedList<Instruction>();
		instructions.addAll(commands.get(0).execute());
		dist = commands.get(0).getReturnValue();
		validate();
		Instruction move = new MoveInstruction();
		instructions.add(move);
		return instructions;
	}

	@Override
	public double getReturnValue() {
		return dist;
	}

	@Override
	public void validate() {
	}

}
