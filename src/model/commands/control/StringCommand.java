package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class StringCommand extends Command{
	private String name;
	private double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		validate();
		
		returnval = 0;
		int index = 0;
		
		for (Command var : commandDictionary.getVariables(name)) {
			states = commands.get(index).execute(states);
			variableDictionary.addVariable(((StringVar) var).getString(), commands.get(index).getReturnValue());
		}
		for (Command c : commandDictionary.getCommands(name)) {
			states = c.execute(states);
			returnval = c.getReturnValue();
		}
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	protected void validate() throws CommandException {
		if (commands.size() != commandDictionary.getVariables(name).size()-1) {
			throw new CommandException("Mismatch between actual and expected number of arguments in " + name);
		}
	}
	
	public String getString() {
		return name;
	}
	
	public void setString(String s) {
		name = s;
	}
}
