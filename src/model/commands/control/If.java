package model.commands.control;


import model.commands.CommandException;

public class If extends Conditional {

	@Override
	protected void validate() throws CommandException {
		if (!(commands.get(1) instanceof ListOpen)) {
			throw new CommandException("List input expected in second argument of ask");
		}
	}

}
