package model.dictionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import model.state.State;

public class TurtleList implements Iterable<Integer>{
	private HashMap<Integer, State> allTurtles;
	private HashSet<Integer> activeTurtles;
	private Stack<HashSet<Integer>> tempTurtles;
	private int activeTurtle;
	/**
	 * Object used to keep track of all turtles created and currently active turtles.
	 */
	public TurtleList() {
		allTurtles = new HashMap<>();
		activeTurtles = new HashSet<>();
		tempTurtles = new Stack<>();
		State defaultState = new State();
		allTurtles.put(1, defaultState);
		activeTurtles.add(1);
		tempTurtles.add(activeTurtles);
	}
	
	/**
	 * Removes all active turtles from the list, and then adds the turtles with given IDs 
	 * to the list.
	 * 
	 * @param id	List of turtle IDs to be added 
	 */
	public void setActiveTurtles(List<Integer> id) {
		activeTurtles.clear();
		activeTurtles.addAll(id);
		while (tempTurtles.size() > 1) {
			tempTurtles.pop();
		}
	}
	
	/**
	 * Adds a list of temporarily active turtle IDs to the TurtleList. Once this temporary list 
	 * has expired, calling popTempTurtle will remove this list from TurtleList.
	 * 
	 * @param id List of temporary turtle IDs to make active.
	 */
	public void addTempTurtle(List<Integer> id) {
		HashSet<Integer> temp = new HashSet<>(id);
		tempTurtles.add(temp);
	}
	
	/**
	 * Retrieves a list of the most recently active turtle IDs and removes this list
	 * from the TurtleList if there are more than one lists of active turtles.
	 * 
	 * @return A list of most recently active turtles
	 */
	public List<Integer> popTempTurtle() {
		HashSet<Integer> temp;
		if (tempTurtles.size() > 1 ) {
			temp = tempTurtles.pop();
		}
		else {
			temp = tempTurtles.peek();
		}
		return new ArrayList<>(temp);
	}
	
	/**
	 * @return A list of most recently active turtle IDs
	 */
	public ArrayList<Integer> getActiveTurtles() {
		return new ArrayList<>(tempTurtles.peek());
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
	public List<State> addTurtles(int highID) {
		ArrayList<State> newTurtleStates = new ArrayList<>();
		if (!allTurtles.containsKey(highID)) {
			for (int id = 1; id <= highID; id++) {
				if (!allTurtles.containsKey(id)) {
					State newState = new State(id);
					allTurtles.put(id, newState);
					newTurtleStates.add(newState);
					activeTurtles.add(id);
				}
			}
		}
		return newTurtleStates;
	}

	/**
	 * An undefined turtle with the given ID is defined and added to the active turtles list.
	 * 
	 * @param myID 	ID of turtle to define.
	 */
	public List<State> addTurtle(int myID) {
		ArrayList<State> newTurtleStates = new ArrayList<>();
		if (!allTurtles.containsKey(myID)) {
			State newState = new State(myID);
			allTurtles.put(myID, newState);
			activeTurtles.add(myID);
			newTurtleStates.add(newState);
			setActiveTurtles(new ArrayList<>(activeTurtles));
		}
		return newTurtleStates;
	}
	
	/** Set the current turtle that is accepting actions
	 * 
	 * @param id
	 */
	public void setActiveTurtle(int id) {
		activeTurtle = id;
	}
	
	public int getActiveTurtle() {
		return activeTurtle;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return allTurtles.keySet().iterator();
	}
	
	
}
