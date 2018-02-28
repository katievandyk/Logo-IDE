package model.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import model.commands.Command;

/**
 * 
 * @author Eric Fu
 *
 */
public class CommandCreator {

	private ArrayList<Command> myCommands = new ArrayList<Command>();
	private ArrayList<String> myStringCommands;
    private List<Entry<String, Pattern>> mySymbols;
    private List<Entry<String, String>> myTypes;
	private List<Entry<String, String>> myChildrenNumbers;
	private Command root;
	private int currIndex = 0; // for createHierarchy
    
	public CommandCreator(List<String> commands) {
		myStringCommands = (ArrayList<String>) commands;
	}
	
	public void newCommands() {
		initializeList("CommandTypes.properties", myTypes);
		initializeList("CommandChildrenNumbers.properties", myChildrenNumbers);
		for (String stringCommand: myStringCommands) {
			myCommands.add(createCommand(stringCommand));
		}
		root = myCommands.get(0);
		createHierarchy(root);
	}
	//will return root command
	private void createHierarchy(Command command) {
		for(int i = 0; i < findNumberChildren(command); i+=1) {
			if(findNumberChildren(command)>0) {
				currIndex += 1;
				command.addtoCommands(myCommands.get(currIndex));
			}
			if (findNumberChildren(myCommands.get(myCommands.indexOf(currIndex)))>0) {
				createHierarchy(myCommands.get(myCommands.indexOf(command)+1));
			}
		}
	}
	
	private int findNumberChildren(Command command) {
		String name = command.getClass().getName();
		for (Entry<String, String> entry : myChildrenNumbers) {
			if (entry.getKey().equals(name)) {
				return Integer.parseInt(entry.getValue());
			}
		}
		return 0;
	}
	
	
	private Command createCommand(String newCommand){
		try {
			Class<?> myInstance = Class.forName(getPackageName(newCommand) + newCommand);
			Constructor<?> constructor = myInstance.getConstructor();
			Command command = (Command) constructor.newInstance();
			if(isCommand(newCommand))
				return command;
		}
		catch(ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Class not found");
		}
		try {
			Class<?> myInstance = Class.forName("model.commands.Value");
			Constructor<?> constructor = myInstance.getConstructor();
			Command command = (Command) constructor.newInstance();
			//set value's variable equal to the number here, special case because different values have same class
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("command is not a value either");
		}
		return null;
	}
	
	
    public void initializeList (String propertyFile, List myList) {
        ResourceBundle resources = ResourceBundle.getBundle(propertyFile);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String value = resources.getString(key);
            myList.add(value);
        }
    }

	private boolean isCommand(String command) {
		for (Entry<String, Pattern> myEntry: mySymbols) {
			if (myEntry.getKey().equals(command));
			return true;
		}

		return false;	
	}
	
	private boolean isValue(String command){
		return command.matches("-?\\d+");
	}
	
	private String getPackageName(String command) {
		String packName = null;
		for(Entry<String, String> entry: myTypes) {
			if(entry.getKey() == command) {
				packName = entry.getKey();
				break;
			}
		}
		if (packName == "Set") return "model.commands.set";
		if (packName == "Get") return "model.commands.get";
		if (packName == "Math") return "model.commands.math";
		return null;	
	}
	
	public List<Command> getCommands(){
		return Collections.unmodifiableList(myCommands);
	}
}
