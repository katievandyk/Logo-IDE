package model.commands;

import java.util.List;

import model.state.State;

public class For extends Command {

	public double returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		String varName = null;
		try {
			varName = ((StringVar) commands.get(0).get(0)).getString();
		}
		catch(Exception e) {
			throw new CommandException("Syntax error in For");
		}
		
		for (int i = 1; i <= 3; i++) {
			states = (commands.get(0).get(i).execute(states));
			parameters.add(commands.get(0).get(i).getReturnValue());
		}
		
		
		returnval = 0;
		for (double i = parameters.get(1); i <= parameters.get(2); i += parameters.get(3)) {
			variableDictionary.addVariable(varName, (double) i);
			for (Command c :commands.get(1)) {
				states = c.execute(states);
				returnval = c.getReturnValue();
			}	
		}
		validate();
		return states;
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	public void validate() throws CommandException {
		else if (parameters.get(0) <= 0) {
			throw new CommandException("Negative argument given");
		}
	}
}
