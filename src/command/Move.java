package command;

import java.util.List;
import java.util.LinkedList;

import instruction.Instruction;

public class Move implements Command{
	Command command;

	@Override
	public List<Instruction> execute() {
		List<Instruction> instructions = new LinkedList<Instruction>();
		instructions.addAll(command.execute());
		return instructions;
	}

}
