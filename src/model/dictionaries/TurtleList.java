package model.dictionaries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.state.State;

public class TurtleList {
	private HashMap<Integer, State> allTurtles;
	private HashSet<Integer> activeTurtles;
	
	/**
	 * Object used to keep track of all turtles created and currently active turtles.
	 */
	public TurtleList() {
		allTurtles = new HashMap<Integer, State>();
		activeTurtles = new HashSet<Integer>();
		State defaultState = new State();
		allTurtles.put(1, defaultState);
		activeTurtles.add(1);
	}
	
	/**
	 * Removes all active turtles from the list, and then adds the turtles with given ID 
	 * to the list.
	 * 
	 * @param id	List of turtle IDs to be added 
	 */
	public void setActiveTurtles(List<Integer> id) {
		activeTurtles.clear();
		activeTurtles.addAll(id);
	}
	
	/**
	 * @return A list of active turtle IDs
	 */
	public HashSet<Integer> getActiveTurtles() {
		return activeTurtles;
	}
	
	/**
	 * @param id 	Turtle ID
	 * @return		The previous state of the turtle with given ID
	 */
	public State getPreviousState(int id) {
		return allTurtles.get(id);
	}
	
	/**
	 * @param id 	Turtle ID
	 * @param newState 	Current state of the turtle with given ID
	 */
	public void setCurrentState(int id, State newState) {
		allTurtles.put(id, newState);
	}
	
	/**
	 * @return number of turtles defined
	 */
	public int numTurtles() {
		return allTurtles.size();
	}
	
	/**
	 * Any undefined turtles with ID less than or equal to highID are defined and added to 
	 * the active turtles list.
	 * 
	 * @param highID 	Highest ID of turtle to define.
	 */
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
