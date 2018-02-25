package model.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class VariableDictionary {
    
    private Map<String, Integer> COMMAND_MAP;
    
    public VariableDictionary() {
	this.COMMAND_MAP = new HashMap<>();
    }
    
    public int getVariable(String var) {
	return COMMAND_MAP.get(var);
    }
    
    public void addVariable(String var, int value) {
	COMMAND_MAP.put(var, value);
    }

}
