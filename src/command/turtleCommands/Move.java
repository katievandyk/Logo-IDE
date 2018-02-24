package command.turtleCommands;

import java.util.List;

import command.Command;
import model.instructions.Instruction;

import java.util.LinkedList;

public class Move implements Command{
	Command command;
	double dist;

	@Override
	public List<Instruction> execute() {
		List<Instruction> instructions = new LinkedList<Instruction>();
		instructions.addAll(command.execute());
		dist = command.getReturnValue();
		validate();
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
