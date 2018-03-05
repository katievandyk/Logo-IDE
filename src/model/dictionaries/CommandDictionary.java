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
    
    public void addCommand(String commandName, ListOpen variableList, ListOpen commandList) throws CommandException {
    	ArrayList<Command> variables = new ArrayList<Command>();
    	ArrayList<Command> commands = new ArrayList<Command>();
		for (Command c : variableList) {
			variables.add(c);
    	}
    	for (Command c : commandList) {
    		commands.add(c);
    	}
    	List<Command>[] commandData = (List<Command>[]) new List[] {variables, commands};
    	commandDict.put(commandName, commandData);
    	
    }
    
    public void defineCommand(String commandName, ListOpen variableList) {
    	ArrayList<Command> v = new ArrayList<Command>();
    	for (Command c : variableList) {
    		v.add(c);
    	}
    	List<Command>[] commandData = (List<Command>[]) new List[] {v, null};
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
