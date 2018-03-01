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
import model.commands.control.ListClose;
import model.commands.control.StringVar;
import model.dictionaries.CommandDictionary;
import model.dictionaries.VariableDictionary;

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
    private CommandDictionary myDict = new CommandDictionary();
    private VariableDictionary myVarDict = new VariableDictionary();

    public CommandCreator(List<String> commands) {
	myStringCommands = (ArrayList<String>) commands;
    }

    public void newCommands() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	initializeList("resources.languages.CommandTypes", myTypes);
	initializeList("resources.languages.CommandChildrenNumbers", myChildrenNumbers);
	for (String stringCommand: myStringCommands) {
	    myCommands.add(createCommand(stringCommand));
	}
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
		command.addtoCommands(myCommands.get(currIndex));
		//if listclose
		if (myCommands.get(currIndex) instanceof ListClose) {
		    return;
		}
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


    private Command createCommand(String newCommand) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
	Class<?> myInstance;
	Constructor<?> constructor;
	Command command;
	// refactor this later
	if (isCommand(newCommand)) {
	    myInstance = Class.forName(getPackageName(newCommand) + newCommand);
	    constructor = myInstance.getConstructor();
	    command = (Command) constructor.newInstance();
	    command.setDictionaries(myVarDict, myDict);
	    if (command instanceof StringVar) ((StringVar) command).setString(newCommand.substring(1, newCommand.length()));
	}
	else {
	    myInstance = Class.forName("model.commands.Value");
	    constructor = myInstance.getConstructor();
	    command = (Command) constructor.newInstance();
	    //set value's variable equal to the number here, special case because different values have same class
	    ((Value) command).setValue(Double.parseDouble(newCommand));
	    command.setDictionaries(myVarDict, myDict);
	}
	return command;
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
	if (packName.equals("Control")) return "model.commands.control.";
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

    public CommandDictionary getCommandDictionary() {
	return myDict;
    }

    public VariableDictionary getVariableDictionary() {
	return myVarDict;
    }

    public List<Command> getCommands(){
	return topLevelCommands;
    }
}
