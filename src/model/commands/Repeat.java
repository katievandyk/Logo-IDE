package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.instructions.Instruction;

public class Repeat extends Command {

	public double parameter;
	public double returnval;

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
		return returnval;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

}
