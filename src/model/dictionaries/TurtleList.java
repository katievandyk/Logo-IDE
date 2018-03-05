package model.dictionaries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.state.State;

public class TurtleList {
	private HashMap<Integer, State> allTurtles;
	private HashSet<Integer> activeTurtles;
	
	public TurtleList() {
		allTurtles = new HashMap<Integer, State>();
		activeTurtles = new HashSet<Integer>();
	}
	
	public void setActiveTurtles(List<Integer> id) {
		activeTurtles.clear();
		activeTurtles.addAll(id);
	}
	
	public List<Integer> getActiveTurtles() {
		return (List<Integer>) activeTurtles;
	}
	
	public State getPreviousState(int id) {
		return allTurtles.get(id);
	}
	
	public void setCurrentState(int id, State newState) {
		allTurtles.put(id, newState);
	}
	
	public void addTurtles(int highID) {
		if (!allTurtles.keySet().contains(highID)) {
			for (int id = 1; id <= highID; id++) {
				if (!allTurtles.keySet().contains(id)) {
					allTurtles.put(id, new State(id));
					activeTurtles.add(id);
				}
			}
		}
	}
	
	
}
