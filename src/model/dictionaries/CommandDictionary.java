package model.dictionaries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.commands.Command;
import model.commands.CommandException;

public class CommandDictionary {
    
    private Map<String, List<Command>[]> commandDict;
    
    public CommandDictionary() {
	 	commandDict = new HashMap<>();
    }
    
    public List<Command> getVariables(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandDict.get(commandName)[0];
    }
    
    public int getNumParameters(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandDict.get(commandName)[0].size();
    }
    
    public List<Command> getCommands(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandDict.get(commandName)[1];
    }
    
    public void addCommand(String commandName, List<Command> vars, List<Command> commands) {
    	List<Command>[] commandData = (List<Command>[]) new List[] {vars, commands};
    	commandDict.put(commandName, commandData);
    }

    public void checkCommand(String commandName) throws CommandException {
    	if (!commandDict.containsKey(commandName)) {
    		throw new CommandException("Command " + commandName + " not found!");
    	}
    }
}
