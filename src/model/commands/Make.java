package model.commands;

import java.util.List;

import model.state.State;

public class Make extends Command {
	private String variableName;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		validate();
		String var = null;
		try {
			var = getString((StringVar) commands.get(0).get(0));
			for (Command c : commands.get(1)) {
				states = c.execute(states);
				parameters.add(c.getReturnValue());
			}
			
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
	
	private String getString(StringVar v) {
		return v.getString();
	}
	

}
