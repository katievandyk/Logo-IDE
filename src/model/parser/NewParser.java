package model.parser;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.commands.Command;
import model.commands.CommandException;
import model.dictionaries.CommandDictionary;
import model.dictionaries.TurtleList;
import model.dictionaries.VariableDictionary;

import java.util.InputMismatchException;


/**
 * Responsible for taking in string input and outputting the commands
 * that correspond to the different inputs.
 * 
 * @author Eric Fu
 */
public class NewParser {
    private List<Entry<String, Pattern>> mySymbols;
    private String myInput;  //what the user types
    private List<String> myInputSpliced; // what user types separated by spaces
    private List<String> myCommands; //changing the user input into the string name of the command object
    private NewCommandCreator myCreator;
    private Command myCommand;
    /**
     * Create an empty parser.
     */
    public NewParser () {
        mySymbols = new ArrayList<Entry<String, Pattern>>();
        myCommands = new ArrayList<String>();
        myInputSpliced = new ArrayList<String>();
        myCreator = new NewCommandCreator();
        myCommand = null;
    }
    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            mySymbols.add(new SimpleEntry<>(key,
                           Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }
    /**
     * A
     */
    public void parse() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CommandException {
    	myInput = removeComments(myInput);
    	myInputSpliced = splitInput(myInput);
    	myCommands = replaceWithSymbols(new ArrayList<String>(myInputSpliced));
    	myCreator.setLists(mySymbols, myCommands, myInputSpliced);
    	myCreator.newCommands();
    }
    
    public void createTopLevelCommand() throws CommandException {
    	myCommand =  myCreator.finalCommand();
    }
    
    /*
     * removes comments from a given input and returns a list of each line
     */
    private String removeComments(String input) {
    	ArrayList<String> separatedInput = new ArrayList<String>(Arrays.asList(input.split("[\\r\\n]+")));
    	for (int i = 0; i < separatedInput.size(); i+=1) {//get rid of comments
    		if (separatedInput.get(i).contains("#")) {
    			separatedInput.set(i, separatedInput.get(i).substring(0, separatedInput.get(i).indexOf("#")));
    		}
    	}
    	String inputWithoutComments = String.join(" ", separatedInput);
    	return inputWithoutComments;
    }
    
    private List<String> splitInput(String input) {
    	ArrayList<String> spacedInput = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
    	spacedInput.removeAll(Arrays.asList("", null));
    	return spacedInput;
    }
    
    private List<String> replaceWithSymbols(List<String> input){
    	for (String symbol: input) {
    		try {
    			input.set(input.indexOf(symbol), getSymbol(symbol));
    		}
    		catch(InputMismatchException ime) {
    			//change this later
    			symbol = "Custom"+symbol;
    		}
    	}
    	return input;
    }
    
    /**
     * Returns language's type associated with the given text if one exists 
     */
    //Should this be private? - probably
    private String getSymbol (String text) {
        for (Entry<String, Pattern> e : mySymbols) {//try once to get something other than stringcommand
            if (match(text, e.getValue()) && !e.getKey().equals("StringCommand")) {
                return e.getKey();
            }
        }
        for (Entry<String, Pattern> e : mySymbols) {//try again (this will be a stringcommand)
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        throw new InputMismatchException();
    }

    
    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
    
    public void setString(String input) {
    	myInput = input;
    }
    public Command getCommand() {
    	return myCommand;
    }
    
    public boolean hasNext() {
    	return !myCommands.isEmpty();
    }

    //workaround code below
    public CommandDictionary getCommandDictionary() {
    	return myCreator.getCommandDictionary();
    }

    public VariableDictionary getVariableDictionary() {
    	return myCreator.getVariableDictionary();
    }
    
    public TurtleList getTurtleList() {
    	return myCreator.getTurtleList();
    }
    
}