package model.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Holds saved variables
 * 
 * @author Martin Muenster
 *
 */
public class VariableDictionary implements Iterable<String> {
    
    private Map<String, Double> variableDict;
    
    /**
     * Object used to hold user defined variables
     */
    public VariableDictionary() {
    	variableDict = new HashMap<>();
    }
    
    /**
     * Get the value of the variable specified by key
     * 
     * @param key 	The string name of the variable
     * @return		The value associated with this variable
     */
    public double get(String key) {
    	if (variableDict.containsKey(key)) {
    		return variableDict.get(key);
    	}
    	else {
    		return 0;
    	}
    }
    
    /**
     * Puts a variable/double pair into the dictionary
     * 
     * @param var 		The string representation of the variable
     * @param double1	The value to insert with the given string
     */
    public void addVariable(String var, Double double1) {
    	variableDict.put(var, double1);
    }
    
	@Override
	public Iterator<String> iterator() {
		return variableDict.keySet().iterator();
	}
}
