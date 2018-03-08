package model.parser;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import java.util.InputMismatchException;


/**
 * Simple parser based on regular expressions that matches program strings to 
 * kinds of language features.
 * 
 * @author Eric Fu
 */
public class Parser {
    private List<Entry<String, Pattern>> mySymbols;
    private String input;
    private List<String> myCommands; //initial splitting based on spaces and regex
    private List<String> myInputSpliced;
    /**
     * Create an empty parser.
     */
    public Parser () {
        mySymbols = new ArrayList<>();
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

    
    public void splitInput() {
    	myCommands = new ArrayList<String>(Arrays.asList(input.split("[\\r\\n]+")));
    	for (int i = 0; i < myCommands.size(); i+=1) {//get rid of comments
    		if (myCommands.get(i).contains("#")) {
    			myCommands.set(i, myCommands.get(i).substring(0, myCommands.get(i).indexOf("#")));
    		}
    	}
    	myCommands.removeAll(Arrays.asList("", null));
    	String inputWithoutComments = String.join(" ", myCommands);
    	myCommands = new ArrayList<String>(Arrays.asList(inputWithoutComments.split("\\s+")));
    	myCommands.add(0, "[");
    	myCommands.add("]");
    	myInputSpliced = new ArrayList<String>(myCommands);
    	//change this to iterate through myCommandsFinal
    	for (String symbol: myCommands) {
    		if (symbol.matches("-?\\d+")) {
    			; // do nothing as it is a value command
    		}
    		else {
	    		try {
	    			myCommands.set(myCommands.indexOf(symbol), getSymbol(symbol));
	    		}
	    		catch(InputMismatchException ime) {
	    			//change this later
	    			symbol = "Custom"+symbol;
	    		}
    		}
    	}
    }
    
    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE KEY LINE
        return regex.matcher(text).matches();
    }
    
    public void setString(String input) {
    	this.input = input;
    }
    
    public List<String> getCommands(){
    	return myCommands;
    }
    public List<String> getInput(){
    	return myInputSpliced;
    }
    
    public List<Entry<String, Pattern>> getSymbols(){
    	return mySymbols;
    }

}
