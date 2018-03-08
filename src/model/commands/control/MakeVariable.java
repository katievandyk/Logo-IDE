package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.commands.set.Set;
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
			System.out.println(variableDictionary.toString());
			
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
	
	@Override
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		states = groupCommands.get(0).execute(states);
		double val = groupCommands.get(0).getReturnValue();
		clearParameters();
		
		for (int i = 1; i < groupCommands.size(); i+=2) {
			MakeVariable s = new MakeVariable();
			s.setDictionaries(variableDictionary, commandDictionary, turtles);
			s.addtoCommands(groupCommands.get(i));
			s.addtoCommands(groupCommands.get(i+1));
			states = s.execute(states);
			val = s.getReturnValue();
		}
		parameters.add(val);
		return states;
	}
	

}
