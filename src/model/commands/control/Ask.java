package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class Ask extends Command{

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getReturnValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void validate() throws CommandException {
		// TODO Auto-generated method stub
		
	}

}
