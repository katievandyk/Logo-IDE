package model.dictionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.commands.Command;
import model.commands.CommandException;
import model.commands.control.ListOpen;
import model.commands.control.StringVar;

public class CommandDictionary implements Iterable<String>{
    
    private Map<String, List<Command>> commandMap;
    private Map<String, List<StringVar>> variableMap;
    
    /**
     * Object used to hold custom created commands
     */
    public CommandDictionary() {
	 	variableMap = new HashMap<>();
	 	commandMap = new HashMap<>();
    }
    
    /**
     * @param commandName 	Name of the custom command
     * @return				List of variables required by this command
     * @throws CommandException
     */
    public List<StringVar> getVariables(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return variableMap.get(commandName);
    }
    
    public List<String> getVariableNames(String commandName) throws CommandException {
    	checkCommand(commandName);
    	ArrayList<String> s = new ArrayList<>();
    	for (StringVar c : variableMap.get(commandName)) {
    		s.add(c.getString());
    	}
    	return s;
    }
    
    
    /**
     * @param commandName		Name of the custom command
     * @return					List of commands contained by the given command name	
     * @throws CommandException
     */
    public List<Command> getCommands(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return commandMap.get(commandName);
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
    	ArrayList<StringVar> variables = new ArrayList<>();
		for (Command c : variableList) {
			if (!(c instanceof StringVar)) {
				throw new CommandException("Expected variable in variable list");
			}
			variables.add((StringVar) c);
    	}
		
		ArrayList<Command> commands = new ArrayList<>();
    	for (Command c : commandList) {
    		commands.add(c);
    	}
    	variableMap.put(commandName, variables);
    	commandMap.put(commandName, commands);
    }
    
    /**
     * Defines a command without giving the command an implementation
     * 
     * @param commandName	Name of the command to be defined
     * @param variableList	List of variables required by the command
     */
    public void defineCommand(String commandName, ListOpen variableList) {
    	ArrayList<StringVar> variables = new ArrayList<>();
    	for (Command c : variableList) {
    		variables.add((StringVar) c);
    	}
    	variableMap.put(commandName, variables);
    }

    private void checkCommand(String commandName) throws CommandException {
    	if (!variableMap.containsKey(commandName)) {
    		throw new CommandException("Command " + commandName + " not found!");
    	}
    	/*else if (!commandMap.containsKey(commandName)) {
    		throw new CommandException("The implementation of Command " + commandName + " has not been defined!");
    	}*/
    }

    /**
     * @param commandName 	Name of the custom command
     * @return				Number of arguments that this command takes
     * @throws CommandException 
     */
    public int getNumArgs(String commandName) throws CommandException {
    	checkCommand(commandName);
    	return variableMap.get(commandName).size();
    }
    //TODO make immutable copy
    public Map<String, List<Command>> getMap(){
    	return commandMap;
    }

	@Override
	public Iterator<String> iterator() {
		return commandMap.keySet().iterator();
	}
}
