package command;

import java.util.List;

import instruction.Instruction;

public interface Command {
	public List<Instruction> execute();
}
