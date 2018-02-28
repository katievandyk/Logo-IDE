package model.parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.commands.Command;

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
    private List<String> myCommands;
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
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        // FIXME: perhaps throw an exception instead
        throw new InputMismatchException();
    }

    
    public void splitInput() {
    	myCommands = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
    	for (String symbol: myCommands) {
    		if (symbol.matches("-?\\d+")) {
    			; // do nothing
    		}
    		else {
	    		try {
	    			symbol = getSymbol(symbol);
	    		}
	    		catch(InputMismatchException ime) {
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
    public List<Command> getCommands(String input){
    	return null;
    }
}
