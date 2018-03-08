package model.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import model.commands.Command;
import model.commands.CommandException;
import model.commands.Value;
import model.commands.control.Define;
import model.commands.control.ListClose;
import model.commands.control.MakeUserInstruction;
import model.commands.control.ParenClose;
import model.commands.control.StringCommand;
import model.commands.control.StringVar;
import model.dictionaries.CommandDictionary;
import model.dictionaries.TurtleList;
import model.dictionaries.VariableDictionary;

/**
 * 
 * @author Eric Fu
 *
 */
public class CommandCreator {

    private ArrayList<Command> myCommands = new ArrayList<Command>(); //command objects actually created from strings
    private ArrayList<String> myInput = new ArrayList<String>();// spliced string of actual input
    private ArrayList<String> myStringCommands; //each spliced string matched to command object name
    private List<Entry<String, Pattern>> mySymbols;
    private List<Entry<String, String>> myTypes= new ArrayList<Entry<String, String>>();
    private List<Entry<String, String>> myChildrenNumbers = new ArrayList<Entry<String, String>>();
    private ArrayList<Command> topLevelCommands = new ArrayList<Command>();
    private Command root;
    private int currIndex = 0; // for createHierarchy
    private CommandDictionary myDict;
    private VariableDictionary myVarDict;
    private TurtleList myTurtleList;

    public CommandCreator(List<String> commands) {
		myStringCommands = (ArrayList<String>) commands;
		myDict = new CommandDictionary();
		myVarDict = new VariableDictionary();
		myTurtleList = new TurtleList();
    }

    public void newCommands() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CommandException {
		initializeList("resources.parsersettings.CommandTypes", myTypes);
		initializeList("resources.parsersettings.CommandChildrenNumbers", myChildrenNumbers);
		for (int i = 0 ; i < myStringCommands.size(); i += 1) {
			myCommands.add(createCommand(myStringCommands.get(i), i));
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
    private void createHierarchy(Command command) throws CommandException{
    	int numChildren = findNumberChildren(command);
    	if (command instanceof StringCommand && ((myCommands.indexOf(command)== 0) || (!((myCommands.get(myCommands.indexOf(command)-1)) instanceof MakeUserInstruction) && !((myCommands.get(myCommands.indexOf(command)-1)) instanceof Define)))) {
    		numChildren = myDict.getNumArgs(((StringCommand) command).getString());
		}
		for(int i = 0; i < numChildren; i+=1) {
		    if(numChildren>0) {
			currIndex += 1;
			if ((myCommands.get(currIndex) instanceof ListClose) || (myCommands.get(currIndex) instanceof ParenClose)) return;
			command.addtoCommands(myCommands.get(currIndex));
		    }
		    if ((myCommands.get(currIndex) instanceof StringCommand) || (findNumberChildren(myCommands.get(currIndex))>0)) {
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


    private Command createCommand(String newCommand, int i) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
	Class<?> myInstance = null;
	Constructor<?> constructor = null;
	Command command = null;
	// refactor this later
	if (isCommand(newCommand)) {
	    myInstance = Class.forName(getPackageName(newCommand) + newCommand);
	    constructor = myInstance.getConstructor();
	    command = (Command) constructor.newInstance();
	    command.setDictionaries(myVarDict, myDict, myTurtleList);
	    if (command instanceof StringVar) ((StringVar) command).setString(myInput.get(i));
	    else if (command instanceof StringCommand) ((StringCommand) command).setString(myInput.get(i));
	}
	else {
	    myInstance = Class.forName("model.commands.Value");
	    constructor = myInstance.getConstructor();
	    command = (Command) constructor.newInstance();
	    //set value's variable equal to the number here, special case because different values have same class
	    ((Value) command).setValue(Double.parseDouble(newCommand));
	    command.setDictionaries(myVarDict, myDict, myTurtleList);
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
	    if (myEntry.getKey().equals(command)) return true;
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
    public void setStringInput(List<String> stringInput) {
	myInput = (ArrayList<String>) stringInput;
    }

    public CommandDictionary getCommandDictionary() {
	return myDict;
    }

    public VariableDictionary getVariableDictionary() {
	return myVarDict;
    }
    
    public TurtleList getTurtleList() {
    	return myTurtleList;
    }

    public List<Command> getCommands(){
	return topLevelCommands;
    }
}
