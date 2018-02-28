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
import model.commands.Value;

/**
 * 
 * @author Eric Fu
 *
 */
public class CommandCreator {

	private ArrayList<Command> myCommands = new ArrayList<Command>();
	private ArrayList<String> myStringCommands;
    private List<Entry<String, Pattern>> mySymbols;
    private List<Entry<String, String>> myTypes= new ArrayList<Entry<String, String>>();
	private List<Entry<String, String>> myChildrenNumbers = new ArrayList<Entry<String, String>>();
	private ArrayList<Command> topLevelCommands = new ArrayList<Command>();
	private Command root;
	private int currIndex = 0; // for createHierarchy
    
	public CommandCreator(List<String> commands) {
		myStringCommands = (ArrayList<String>) commands;
	}
	
	public void newCommands() {
		initializeList("resources.languages.CommandTypes", myTypes);
		initializeList("resources.languages.CommandChildrenNumbers", myChildrenNumbers);
		for (String stringCommand: myStringCommands) {
			myCommands.add(createCommand(stringCommand));
		}
		System.out.println(myCommands.toString());
		while(myCommands.size() != 0) {
			root = myCommands.get(0);
			createHierarchy(root);
			topLevelCommands.add(root);
			for (int i = 0; i <= currIndex; i += 1) {
				myCommands.remove(0);
			}
			currIndex = 0;
		}
	}
	//will return root command
	private void createHierarchy(Command command) {
		for(int i = 0; i < findNumberChildren(command); i+=1) {
			if(findNumberChildren(command)>0) {
				currIndex += 1;
				command.addtoCommands(new ArrayList<Command>(myCommands.subList(currIndex, currIndex + 1)));
			}
			if (findNumberChildren(myCommands.get(currIndex))>0) {
				createHierarchy(myCommands.get(currIndex));
			}
		}
	}
	
	private int findNumberChildren(Command command) {
		String name = command.getClass().getSimpleName();
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
			System.out.println("Command is not in the properties file.");
		}
		try {
			Class<?> myInstance = Class.forName("model.commands.Value");
			Constructor<?> constructor = myInstance.getConstructor();
			Value command = (Value) constructor.newInstance();
			//set value's variable equal to the number here, special case because different values have same class
			command.setValue(Double.parseDouble(newCommand));
			return command;		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Command is not a value either. Your command was not understood.");
		}
		return null;
	}
	
	
    public void initializeList (String propertyFile, List myList) {
        ResourceBundle resources = ResourceBundle.getBundle(propertyFile);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String value = resources.getString(key);
            myList.add(new SimpleEntry<>(key,value));
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
		for(Entry<String, String> entry : myTypes) {
			if(entry.getKey().equals(command)) {
				packName = entry.getValue();
				break;
			}
		}
		if (packName == null) return null;
		if (packName.equals("Set")) return "model.commands.set.";
		if (packName.equals("Get")) return "model.commands.get.";
		if (packName.equals("Math")) return "model.commands.math.";
		return null;	
	}
	
	private void reset() {
		myCommands = new ArrayList<Command>();
	    myTypes= new ArrayList<Entry<String, String>>();
	    myChildrenNumbers = new ArrayList<Entry<String, String>>();
		topLevelCommands = new ArrayList<Command>();
		currIndex = 0; // for createHierarchy
	}
	
	public void setSymbols(List<Entry<String, Pattern>> symbols) {
		mySymbols = symbols;
	}
	public void setStringCommands(List<String> stringCommands) {
		reset();
		myStringCommands = (ArrayList<String>) stringCommands;
	}
	
	public List<Command> getCommands(){
		return topLevelCommands;
	}
}
