package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class MakeVariable extends Command {
	private String variableName;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		validate();
		String var = null;
		try {
			var = ((StringVar) commands.get(0)).getString();
			
			states = commands.get(1).execute(states);
			parameters.add(commands.get(1).getReturnValue());
			
			variableDictionary.addVariable(var, parameters.get(0));
		}
		catch(Exception e) {
			throw new CommandException("Declaration error: given variable is not a valid variable: " + var);
		}
		
		return states;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	protected void validate() throws CommandException {
	}
	

}
