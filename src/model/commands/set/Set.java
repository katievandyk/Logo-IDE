package model.commands.set;

import java.util.ArrayList;
import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class Set extends Command {
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		for (Command c : commands) {
			states = (c.execute(states));
			parameters.add(c.getReturnValue());
		}
		validate();
		
		for (int id : turtles.getActiveTurtles()) {
			State nextState = new State(turtles.getPreviousState(id));
			nextState = setNextState(nextState);
			turtles.setCurrentState(id, nextState);
			states.add(nextState);
		}
		
		return states;
	}
	
	@Override
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		double val = 0;
		
		states = groupCommands.get(0).execute(states);
		val += groupCommands.get(0).getReturnValue();
		int numChildren = groupCommands.get(0).size();
		if (numChildren == 0) {
			return states;
		}
		clearParameters();
		
		for (int i = 1; i < groupCommands.size(); i+=numChildren) {
			try {
				Set s = this.getClass().newInstance();
				s.setDictionaries(variableDictionary, commandDictionary, turtles);
				for (int j = 0; j < numChildren; j++) {
					s.addtoCommands(groupCommands.get(i+j));
					parameters.add(0.0);
				}
				states = s.execute(states);
				val += s.getReturnValue();
			} catch (InstantiationException e) {
				throw new CommandException("Grouping: class " + this.getClass().toString() + " does not exist");
			} catch (IllegalAccessException e) {
				throw new CommandException("Grouping: class " + this.getClass().toString() + " does not exist");
			}
		}
		parameters.set(0, val);
		return states;
	}
	
	protected abstract State setNextState(State nextState);

}
