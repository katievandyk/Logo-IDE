package model.dictionaries;

import java.util.HashSet;
import java.util.List;

public class TurtleList {
	private HashSet<Integer> allTurtles;
	private HashSet<Integer> activeTurtles;
	
	public TurtleList() {
		allTurtles = new HashSet<Integer>();
		activeTurtles = new HashSet<Integer>();
	}
	
	public void setActiveTurtles(List<Integer> id) {
		activeTurtles.clear();
		activeTurtles.addAll(id);
	}
	
	public void addTurtles(int highID) {
		if (!allTurtles.contains(highID)) {
			for (int id = 1; id <= highID; id++) {
				if (!allTurtles.contains(id)) {
					allTurtles.add(id);
					activeTurtles.add(id);
				}
			}
		}
	}
	
	
}
