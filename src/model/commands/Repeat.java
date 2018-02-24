package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.instructions.Instruction;

public class Repeat implements Command {
	List<Command> commands;
	double parameter;
	double returnval;

	@Override
	public List<Instruction> execute() {
		returnval = 0;
		List<Instruction> instructions = new LinkedList<Instruction>();
		for (int i = 0; i < parameter; i++) {
			for (Command c : commands) {
				instructions.addAll(c.execute());
				returnval = c.getReturnValue();
			}
			
		}
		return instructions;
	}

	@Override
	public double getReturnValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

}
