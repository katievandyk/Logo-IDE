package model.commands.math;

import java.util.LinkedList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.instructions.Instruction;

public abstract class Math extends Command {
	
	@Override
	public List<State> execute(State s) throws CommandException {
		clearParameters();
		List<Instruction> instructions = new LinkedList<Instruction>();
		for (Command c : commands) {
			c.setDictionaries(uvkjv
			s.addAll(c.execute(s.get(s.size()-1)));
			parameters.add(c.getReturnValue());
		}
		validate();
		return instructions;
	}
}
