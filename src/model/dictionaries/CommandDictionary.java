package model.dictionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.commands.Command;
import model.commands.CommandException;
import model.commands.control.ListOpen;

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
    
    public void addCommand(String commandName, Command vars, Command commands) {
    	ArrayList<Command> v = new ArrayList<Command>();
    	ArrayList<Command> cmd = new ArrayList<Command>();
    	for (Command c : ((ListOpen) vars).getCommands()) {
    		v.add(c);
    	}
    	for (Command c : ((ListOpen) commands).getCommands()) {
    		cmd.add(c);
    	}
    	List<Command>[] commandData = (List<Command>[]) new List[] {v, cmd};
    	commandDict.put(commandName, commandData);
    }

    public void checkCommand(String commandName) throws CommandException {
    	if (!commandDict.containsKey(commandName)) {
    		throw new CommandException("Command " + commandName + " not found!");
    	}
    }
    
    //TODO make immutable copy
    public Map<String, List<Command>[]> getMap(){
	return commandDict;
    }
}
