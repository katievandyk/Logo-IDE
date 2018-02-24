package model.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.instructions.Instruction;

public class Sum extends Command {
	public List<Double> parameters;

	@Override
	public List<Instruction> execute() {
		parameters = new ArrayList<Double>();
		List<Instruction> instructions = new LinkedList<Instruction>();
		for (Command c : commands) {
			instructions.addAll(c.execute());
			parameters.add(c.getReturnValue());
		}
		validate();
		return instructions;
	}


	@Override
	public double getReturnValue() {
		return parameters.get(0)+parameters.get(1);
	}


	@Override
	public void validate() {
	}
	

}
