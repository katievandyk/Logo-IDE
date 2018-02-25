package model.commands;

import java.util.List;

import model.state.State;


public class Move extends Command{

	@Override
	public List<State> execute(List<State> states) throws CommandException{
		clearParameters();
		states = (commands.get(0).execute(states));
		parameters.add(commands.get(0).getReturnValue());
		validate();
		/*Instruction move = new MoveInstruction();
		move.param = parameters.get(0);
		instructions.add(move);
		*/
		//ActionEvent move = new ActionEvent(this, move.ACTION_PERFORMED, "move 4");
		//move.
		State nextState = new State(states.get(states.size()-1));
		nextState.move(parameters.get(0));
		states.add(nextState);
		
		return states;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}

	@Override
	public void validate() throws CommandException {
		if (commands.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
