package model.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

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
 * Responsible for creating command objects and returning one top level command (root command)
 */
public class NewCommandCreator {

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
    /**
     * initializes an empty command creator
     */
    public NewCommandCreator() {
    	myInput = new ArrayList<>();
		myStringCommands = new ArrayList<>();
		myCommands = new ArrayList<>();
		myTypes = new ArrayList<>();
		myChildrenNumbers = new ArrayList<>();
		root = null;
		currIndex = 0;
    }
    
    /**
     * responsible for creating an array of command objects from the array of command "symbols"
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws CommandException
     */
    public void newCommands() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CommandException {
		initializeList("resources.parsersettings.CommandTypes", myTypes);
		initializeList("resources.parsersettings.CommandChildrenNumbers", myChildrenNumbers);
		for (int i = 0 ; i < myStringCommands.size(); i += 1) {
			myCommands.add(createCommand(myStringCommands.get(i), i));
		}
    }
    
    /**
     * creates and returns the root command of a command tree, created from the arraylist of command objects
     * @return this value is the root command of the command tree
     * @throws CommandException
     */
    public Command finalCommand() throws CommandException {
	    root = myCommands.get(0);
	    createHierarchy(root);
	    removeUsedInputs();
	    return root;
    }
    
    private void removeUsedInputs() {
	    for (int i = 0; i <= currIndex; i += 1) {
	    	myCommands.remove(0);
	    	myStringCommands.remove(0);
	    	myInput.remove(0);
	    }
	    currIndex = 0;
    }
    
    /**
     * creates a single command tree using as many of the command objects from the arraylist of 
     * command objects as necessary.
     * @param command
     * @throws CommandException
     */
    private void createHierarchy(Command command) throws CommandException{
    	int numChildren = findNumberChildren(command);
    	if (command instanceof StringCommand && ((myCommands.indexOf(command)== 0) || (!((myCommands.get(myCommands.indexOf(command)-1)) instanceof MakeUserInstruction) && !((myCommands.get(myCommands.indexOf(command)-1)) instanceof Define)))) {
    		numChildren = myDict.getNumArgs(((StringCommand) command).getString());
		}
		for(int i = 0; i < numChildren; i+=1) {
			currIndex += 1;
			if ((myCommands.get(currIndex) instanceof ListClose) || (myCommands.get(currIndex) instanceof ParenClose)) {
				return;
			}
			command.addtoCommands(myCommands.get(currIndex));
		    if ((myCommands.get(currIndex) instanceof StringCommand) || (findNumberChildren(myCommands.get(currIndex))>0)) {
		    	createHierarchy(myCommands.get(currIndex));
		    }
		}
    }

    /**
     * gets the number of children(child commands) that each command should have
     * @param command
     * @return
     */
    private int findNumberChildren(Command command) {
		String name = command.getClass().getSimpleName();
		for (Entry<String, String> entry : myChildrenNumbers) {
		    if (entry.getKey().equals(name)) {
			return Integer.parseInt(entry.getValue());
		    }
		}
		return 0;
    }
	
	/**
	 * Uses reflection to create single instances of the command objects using a single command "symbol"
	 * @param newCommand
	 * @param i
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
    private Command createCommand(String newCommand, int i) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	Class <?> myInstance = Class.forName(getPackageName(newCommand) + newCommand);
		Constructor <?> constructor = myInstance.getConstructor();
		Command command = (Command) constructor.newInstance();
		command.setDictionaries(myVarDict, myDict, myTurtleList);
		if (command instanceof StringVar) {
			((StringVar) command).setString(myInput.get(i));
		}
		else if (command instanceof StringCommand) {
			((StringCommand) command).setString(myInput.get(i));
		}
		else if (command instanceof Value) {
			((Value) command).setValue(Double.parseDouble(myInput.get(i)));
		}
		return command;
    }

    /** initializes the information from a resource bundle into a list of entries
     * 
     * @param propertyFile
     * @param myList
     */
    @SuppressWarnings("unchecked")
    public void initializeList (String propertyFile, @SuppressWarnings("rawtypes") List myList) {
		ResourceBundle resources = ResourceBundle.getBundle(propertyFile);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
		    String key = iter.nextElement();
		    String value = resources.getString(key);
		    myList.add(new SimpleEntry<>(key,value));
		}
    }
    /**
     * Gets the package name of a specific command class
     * @param command
     * @return
     */
    private String getPackageName(String command) {
		String packName = null;
		for(Entry<String, String> entry : myTypes) {
		    if(entry.getKey().equals(command)) {
			packName = entry.getValue();
			break;
		    }
		}
		if (packName == null){
			return null;
		}
		else {
			return ("model.commands." + packName.toLowerCase()+".");
		}
    }

    /**
     * Sets the lists from the parser that are needed to create commands with.
     * @param stringCommands
     * @param stringInput
     */
    public void setLists(List<String> stringCommands, List<String> stringInput) {
    	myStringCommands = (ArrayList<String>) stringCommands;
    	myInput = (ArrayList<String>) stringInput;
    }
    /**
     * Sets the dictionaries that are stored in the parser, because each command needs to know what is in the dictionary
     * so the dictionaries are assigned to the commands upon creation.
     * @param cd
     * @param vd
     * @param tl
     */
    public void setDictionaries(CommandDictionary cd, VariableDictionary vd, TurtleList tl) {
    	myDict = cd;
    	myVarDict = vd;
    	myTurtleList = tl;
    }
    /**
     * 
     * @return provides the root command of the command tree
     */
    public Command getCommand() {
    	return root;
    }
}