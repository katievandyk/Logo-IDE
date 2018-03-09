package model.commands.set;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public abstract class Set extends Command {

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		for (int id : turtles.getActiveTurtles()) {
			clearParameters();
			for (Command c : commands) {
				states = c.execute(states);
				parameters.add(c.getReturnValue());
			}
			turtles.setActiveTurtle(id);
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
		if (groupCommands.get(0).size() == 0) {
			return states;
		}
		clearParameters();
		for (int i = 1; i < groupCommands.size(); i+=groupCommands.get(0).size()) {
			Set s = createNewSet(groupCommands, i);
			states = s.execute(states);
			val += s.getReturnValue();
		} 
		parameters.set(0, val);
		return states;
	}
	
	private Set addCommands(Set s, List<Command> groupCommands, int i) {
		for (int j = 0; j < groupCommands.get(0).size(); j++) {
			s.addtoCommands(groupCommands.get(i+j));
			parameters.add(0.0);
		}
		return s;
	}
	
	private Set createNewSet(List<Command> groupCommands, int i) throws CommandException {
		Set s = null;
		try {
			s = this.getClass().newInstance();
			s.setDictionaries(variableDictionary, commandDictionary, turtles);
			s = addCommands(s, groupCommands, i);
		}
		catch (InstantiationException|IllegalAccessException e) {
			throw new CommandException("Grouping: class " + this.getClass().toString() + " does not exist");
		}
		return s;
	}
	
	protected abstract State setNextState(State nextState);

}
