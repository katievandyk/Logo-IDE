package command.turtleCommands;

import java.util.LinkedList;
import java.util.List;

import command.Command;
import model.instructions.Instruction;

public class MoveTo implements Command {
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
		Instruction i = new Instruction();
		instructions.add(i);
		return instructions;
	}

	@Override
	public double getReturnValue() {
		return Math.sqrt(Math.pow(parameters.get(0),2)+Math.pow(parameters.get(1),2));
	}

	@Override
	public void validate() {
	}

}
