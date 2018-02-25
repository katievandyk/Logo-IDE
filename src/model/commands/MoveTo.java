package model.commands;

import java.util.List;
import model.state.State;

public class MoveTo extends Command {
	List<Command> commands;
	List<Double> parameters;

	@Override
	public List<State> execute(List<State> states) throws CommandException{
		clearParameters();
		for (Command c : commands) {
			states = (c.execute(states));
			parameters.add(c.getReturnValue());
		}
		validate();
		State nextState = new State(states.get(states.size()-1));
		nextState.setXY(parameters.get(0), parameters.get(1));
		states.add(nextState);
		
		return states;
	}

	@Override
	public double getReturnValue() {
		return Math.sqrt(Math.pow(parameters.get(0),2)+Math.pow(parameters.get(1),2));
	}

	@Override
	public void validate() throws CommandException {
		if (commands.size() != 2) {
			throw new CommandException("Invalid number of commands: " + commands.size());
		}
		if (parameters.size() != 2) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
