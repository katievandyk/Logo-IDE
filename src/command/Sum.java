package command;

import java.util.LinkedList;
import java.util.List;

import instruction.Instruction;

public class Sum implements Command {
	List<Command> commands;
	List<Double> parameters;

	@Override
	public List<Instruction> execute() {
		List<Instruction> instructions = new LinkedList<Instruction>();
		for (Command c : commands) {
			instructions.addAll(c.execute());
			parameters.add(c.getReturnValue());
		}
		validate();
		return null;
	}


	@Override
	public double getReturnValue() {
		return parameters.get(0)+parameters.get(1);
	}


	@Override
	public void validate() {
	}
	

}
