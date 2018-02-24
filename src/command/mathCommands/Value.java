package command.mathCommands;

import java.util.List;

import command.Command;
import model.instructions.Instruction;

public class Value implements Command {
	double val;

	@Override
	public List<Instruction> execute() {
		validate();
		return null;
	}

	@Override
	public double getReturnValue() {
		return val;
	}

	@Override
	public void validate() {
	}

}
