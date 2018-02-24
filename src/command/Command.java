package command;

import java.util.List;

import model.instructions.Instruction;

public interface Command {
	public List<Instruction> execute();
	
	public double getReturnValue();
	
	public void validate();
}
