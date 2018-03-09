package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class StringVar extends Command {
	private String name;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		return states;
	}

	@Override
	public double getReturnValue() {
		System.out.println(name);
		return variableDictionary.get(name);
		
	}
	
	public String getString() {
		return name;
	}
	
	public void setString(String s) {
		name = s;
	}

}
