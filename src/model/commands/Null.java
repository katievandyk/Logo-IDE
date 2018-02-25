package model.commands;

import java.util.List;

import model.instructions.Instruction;

public class Null extends Command {

	@Override
	public List<Instruction> execute() {
		return null;
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
