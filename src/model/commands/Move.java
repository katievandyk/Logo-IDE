package model.commands;

import java.util.List;

import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager.State;

import model.instructions.Instruction;
import model.instructions.MoveInstruction;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class Move extends Command{

	@Override
	public List<State> execute(State s) throws CommandException{
		clearParameters();
		List<Instruction> instructions = new LinkedList<Instruction>();
		instructions.addAll(commands.get(0).execute());
		parameters.add(commands.get(0).getReturnValue());
		validate();
		/*Instruction move = new MoveInstruction();
		move.param = parameters.get(0);
		instructions.add(move);
		*/
		//ActionEvent move = new ActionEvent(this, move.ACTION_PERFORMED, "move 4");
		//move.
		
		return instructions;
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
