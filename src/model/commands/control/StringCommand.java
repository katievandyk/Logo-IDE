package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class StringCommand extends Command{
	private String name;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		return states;
	}

	@Override
	public double getReturnValue() {
		return 0;
	}

	@Override
	protected void validate() throws CommandException {
		// TODO Auto-generated method stub

	}
	
	public String getString() {
		return name;
	}
	
	public void setString(String s) {
		name = s;
	}
}
