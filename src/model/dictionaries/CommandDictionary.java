package model.dictionaries;

import java.util.HashMap;
import java.util.Map;

public class CommandDictionary {
    
    private Map<String, String> VARIABLE_MAP;
    
    public CommandDictionary() {
	this.VARIABLE_MAP = new HashMap<>();
    }
    
    public String getVariable(String var) {
	return VARIABLE_MAP.get(var);
    }
    
    public void addVariable(String var, String value) {
	VARIABLE_MAP.put(var, value);
    }

}
