package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.instructions.Instruction;

public class Repeat extends Command {

	public double returnval;

	@Override
	public List<Instruction> execute() throws CommandException {
		List<Instruction> instructions = new LinkedList<Instruction>();
		
		instructions.addAll(commands.get(0).execute());
		parameters.add(commands.get(0).getReturnValue());
		
		returnval = 0;
		
		for (int i = 0; i < parameters.get(0); i++) {
			for (int j = 1; j < commands.size(); j++) {
				instructions.addAll(commands.get(j).execute());
				returnval = commands.get(j).getReturnValue();
			}
			
		}
		validate();
		return instructions;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
		else if (parameters.get(0) <= 0) {
			throw new CommandException("Negative argument given");
		}
	}

}
