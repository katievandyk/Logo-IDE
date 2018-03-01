package model.commands.set;

import java.util.List;

import model.commands.CommandException;
import model.state.State;

public class ClearScreen extends Home{
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		states.get(states.size()-1).clearScreen();
		return super.execute(states);
	}
	
}
