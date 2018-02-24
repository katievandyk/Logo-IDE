package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.instructions.Instruction;

public abstract class Command {
	public List<Command> commands;
	public Command() {
		commands = new ArrayList<Command>();
	}
	public abstract List<Instruction> execute();
	
	public abstract double getReturnValue();
	
	public abstract void validate();
}
