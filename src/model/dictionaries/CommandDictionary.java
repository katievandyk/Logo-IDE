package model.dictionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.commands.Command;
import model.commands.CommandException;
import model.commands.control.ListOpen;
import model.commands.control.StringCommand;
import model.commands.control.StringVar;

public class CommandDictionary {
    
    private Map<String, List<Command>[]> commandDict;
    
    /**
     * Object used to hold custom created commands
     */
    public CommandDictionary() {
	 	commandDict = new HashMap<>();
    }
    
    /**
     * @param commandName 	Name of the custom command
     * @return				List of variables required by this command
     * @throws CommandException
     */
    public List<Command> getVariables(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandDict.get(commandName)[0];
    }
    
    
    /**
     * @param commandName		Name of the custom command
     * @return					List of commands contained by the given command name	
     * @throws CommandException
     */
    public List<Command> getCommands(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandDict.get(commandName)[1];
    }
    
    /**
     * Adds a custom command to the command dictionary
     * 
     * @param commandName		Name of the command to be created
     * @param variableList		List of variables required by the command
     * @param commandList		List of commands to be run when this command is called
     * @throws CommandException
     */
    public void addCommand(String commandName, ListOpen variableList, ListOpen commandList) throws CommandException {
    	ArrayList<Command> variables = new ArrayList<Command>();
    	ArrayList<Command> commands = new ArrayList<Command>();
		for (Command c : variableList) {
			if (!(c instanceof StringVar)) {
				throw new CommandException("Expected variable in variable list");
			}
			variables.add(c);
    	}
    	for (Command c : commandList) {
    		commands.add(c);
    	}
    	List<Command>[] commandData = (List<Command>[]) new List[] {variables, commands};
    	commandDict.put(commandName, commandData);
    	
    }
    
    /**
     * Defines a command without giving the command an implementation
     * 
     * @param commandName	Name of the command to be defined
     * @param variableList	List of variables required by the command
     */
    public void defineCommand(String commandName, ListOpen variableList) {
    	ArrayList<Command> v = new ArrayList<Command>();
    	for (Command c : variableList) {
    		v.add(c);
    	}
    	List<Command>[] commandData = (List<Command>[]) new List[] {v, null};
    	commandDict.put(commandName, commandData);
    }

    private void checkCommand(String commandName) throws CommandException {
    	if (!commandDict.containsKey(commandName)) {
    		throw new CommandException("Command " + commandName + " not found!");
    	}
    }

    /**
     * @param commandName 	Name of the custom command
     * @return				Number of arguments that this command takes
     * @throws CommandException 
     */
    public int getNumArgs(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandDict.get(commandName)[0].size();
    }
    //TODO make immutable copy
    public Map<String, List<Command>[]> getMap(){
	return commandDict;
    }
}
