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
import model.commands.control.Define;
import model.commands.control.ListClose;
import model.commands.control.MakeUserInstruction;
import model.commands.control.ParenClose;
import model.commands.control.StringCommand;
import model.commands.control.StringVar;
import model.commands.math.Value;
import model.dictionaries.CommandDictionary;
import model.dictionaries.TurtleList;
import model.dictionaries.VariableDictionary;

/**
 * 
 * @author Eric Fu
 *
 */
public class NewCommandCreator {

    private List<Entry<String, Pattern>> mySymbols;
    private ArrayList<String> myInput;// spliced string of actual input
    private ArrayList<String> myStringCommands; //each spliced string matched to command object name
    private ArrayList<Command> myCommands; //command objects actually created from strings
    private List<Entry<String, String>> myTypes;
    private List<Entry<String, String>> myChildrenNumbers;
    private CommandDictionary myDict;
    private VariableDictionary myVarDict;
    private TurtleList myTurtleList;
    private Command root;
    private int currIndex; //for create heirarchy
    
    public NewCommandCreator() {
    	mySymbols = new ArrayList<Entry<String, Pattern>>();
    	myInput = new ArrayList<String>();
		myStringCommands = new ArrayList<String>();
		myCommands = new ArrayList<Command>();
		myTypes = new ArrayList<Entry<String, String>>();
		myChildrenNumbers = new ArrayList<Entry<String, String>>();
		myDict = new CommandDictionary();
		myVarDict = new VariableDictionary();
		myTurtleList = new TurtleList();
		root = null;
		currIndex = 0;
    }

    public void newCommands() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CommandException {
		initializeList("resources.parsersettings.CommandTypes", myTypes);
		initializeList("resources.parsersettings.CommandChildrenNumbers", myChildrenNumbers);
		for (int i = 0 ; i < myStringCommands.size(); i += 1) {
			myCommands.add(createCommand(myStringCommands.get(i), i));
		}
    }
    
    public Command finalCommand() throws CommandException {
	    root = myCommands.get(0);
	    createHierarchy(root);
	    for (int i = 0; i <= currIndex; i += 1) {
	    	myCommands.remove(0);
	    	myStringCommands.remove(0);
	    	myInput.remove(0);
	    }
	    System.out.println(myStringCommands.size());
	    currIndex = 0;
	    return root;
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
    	Class <?> myInstance = Class.forName(getPackageName(newCommand) + newCommand);
		Constructor <?> constructor = myInstance.getConstructor();
		Command command = (Command) constructor.newInstance();
		command.setDictionaries(myVarDict, myDict, myTurtleList);
		if (command instanceof StringVar) ((StringVar) command).setString(myInput.get(i));
		else if (command instanceof StringCommand) ((StringCommand) command).setString(myInput.get(i));
		else if (command instanceof Value) ((Value) command).setValue(Double.parseDouble(myInput.get(i)));
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

    private String getPackageName(String command) {
		String packName = null;
		for(Entry<String, String> entry : myTypes) {
		    if(entry.getKey().equals(command)) {
			packName = entry.getValue();
			break;
		    }
		}
		if (packName == null) return null;
		else return ("model.commands." + packName.toLowerCase()+".");
    }

    public void setLists(List<Entry<String, Pattern>> symbols, List<String> stringCommands, List<String> stringInput) {
    	mySymbols = symbols;
    	myStringCommands = (ArrayList<String>) stringCommands;
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
    public Command getCommand() {
    	return root;
    }
}