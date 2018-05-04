package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class ClearStamps extends Command {

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		return states;
	}

	@Override
	public double getReturnValue() {
		return 0;
	}

}
