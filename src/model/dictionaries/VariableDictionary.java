package model.dictionaries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Holds saved variables
 * 
 * @author katherinevandyk
 *
 */
public class VariableDictionary implements Iterable<String> {
    
    private Map<String, Double> variableDict;
    
    public VariableDictionary() {
    	variableDict = new HashMap<>();
    }
    
    public double get(String key) {
    	if (variableDict.containsKey(key)) {
    		return variableDict.get(key);
    	}
    	else {
    		return 0;
    	}
    }
    
    public void addVariable(String var, Double double1) {
    	variableDict.put(var, double1);
    }

    public String toString() {
    	return variableDict.toString();
    }
    
	@Override
	public Iterator<String> iterator() {
		return variableDict.keySet().iterator();
	}
}
