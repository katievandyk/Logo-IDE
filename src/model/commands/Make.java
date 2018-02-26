package model.commands;

import java.util.List;

import model.state.State;

public class Make extends Command {
	private String variableName;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		validate();
		try {
			String var = getString((StringVar) commands.get(0));
			states = commands.get(1).execute(states);
			parameters.add(commands.get(1).getReturnValue());
			variableDictionary.addVariable(var, parameters.get(0));
		}
		catch(Exception e) {
			throw new CommandException("Declaration error: given variable is not a valid variable:");
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
	
	private String getString(StringVar v) {
		return v.getString();
	}
	

}
