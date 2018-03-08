package model.dictionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import model.state.State;

public class TurtleList implements Iterable<Integer>{
	private HashMap<Integer, State> allTurtles;
	private HashSet<Integer> activeTurtles;
	private Stack<HashSet<Integer>> tempTurtles;
	
	/**
	 * Object used to keep track of all turtles created and currently active turtles.
	 */
	public TurtleList() {
		allTurtles = new HashMap<Integer, State>();
		activeTurtles = new HashSet<Integer>();
		tempTurtles = new Stack<HashSet<Integer>>();
		State defaultState = new State();
		allTurtles.put(1, defaultState);
		activeTurtles.add(1);
		tempTurtles.add(activeTurtles);
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
		while (tempTurtles.size() > 1) {
			tempTurtles.pop();
		}
		
		System.out.println("active turtles " + tempTurtles.peek().toString());
	}
	
	public void addTempTurtle(List<Integer> id) {
		HashSet<Integer> temp = new HashSet<Integer>(id);
		tempTurtles.add(temp);
		System.out.println("active turtles " + tempTurtles.peek().toString());
	}
	
	public List<Integer> popTempTurtle() {
		HashSet<Integer> temp;
		if (tempTurtles.size() > 1 ) {
			temp = tempTurtles.pop();
		}
		else {
			temp = tempTurtles.peek();
		}
		return new ArrayList<Integer>(temp);
	}
	
	public boolean contains(int id) {
		return allTurtles.containsKey(id);
	}
	
	/**
	 * @return A list of active turtle IDs
	 */
	public ArrayList<Integer> getActiveTurtles() {
		return new ArrayList<Integer>(tempTurtles.peek());
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
		ArrayList<State> newTurtleStates = new ArrayList<State>();
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
		System.out.println("active turtles " + tempTurtles.peek().toString());
		return newTurtleStates;
	}

	@Override
	public Iterator<Integer> iterator() {
		return allTurtles.keySet().iterator();
	}
	
	
}
