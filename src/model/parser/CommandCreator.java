package model.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import model.commands.Command;

/**
 * 
 * @author Eric Fu
 *
 */
public class CommandCreator {

	private ArrayList<Command> myCommands;
	private ArrayList<String> myStringCommands;
	
	public CommandCreator(List<String> commands) {
		myStringCommands = (ArrayList<String>) commands;
	}
	
	
	private void newCommands() {
		
	}
	
	private Command createCommand() {
		return null;
	}
	
	
	
	public List<Command> getCommands(){
		return Collections.unmodifiableList(myCommands);
	}
}
