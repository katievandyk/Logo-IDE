package model.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.dictionaries.CommandDictionary;
import model.dictionaries.TurtleList;
import model.dictionaries.VariableDictionary;
import model.state.State;


public abstract class Command implements Iterable<Command> {
	protected ArrayList<Command> commands;
	protected List<Double> parameters;
	protected VariableDictionary variableDictionary;
	protected CommandDictionary commandDictionary;
	protected TurtleList turtles;

	
	/**
	 * Construct a new command object of this type, used to compile a user input
	 * to states readable by turtle objects.
	 */
	public Command() {
		commands = new ArrayList<>();
		parameters = new ArrayList<>();
	}
	
	/**
	 * Executes an instruction given a list of states of commands that have already been executed.
	 * Each command calls execute on its child/children in order to generate new turtle states. Execute
	 * methods may also put data into the parameters list, where they can be used in other parts of the
	 * command.
	 * 
	 * @param states 	List of previously generated state objects
	 * @return			List of state objects containing information readable by the turtle
	 * @throws CommandException
	 */
	public abstract List<State> execute(List<State> states) throws CommandException;
	
	/**
	 * Executes an instruction given an initial state.
	 * 
	 * @param initialState	initial state of the turtle
	 * @return				List of state objects containing information readable by the turtle
	 * @throws CommandException
	 */
	public List<State> execute(State initialState) throws CommandException {
		List<State> states = new LinkedList<>();
		states.add(initialState);
		return execute(states);
	}
	
	/**
	 * Calculates or retrieves the return value of a command after it has been executed. Most
	 * commands pull their return value from their parameters list.
	 * 
	 * @return 	The return value of this command
	 */
	public abstract double getReturnValue();
	
	/**
	 * Confirms that necessary data is present, no illegal children commands exist, or that parameters
	 * are valid for the given command, dependent on the type of command.
	 * 
	 * @throws CommandException
	 */
	protected void validate() throws CommandException {
		// if validate is required by a command, it will override this method
		return;
	}
	
	/**
	 * Performs a group operation with the given command, where multiple arguments can be evaluated by the
	 * command. While groupExecute only makes sense for some commands, it is valid syntax for all commands.
	 * 
	 * @param states 		List of previously generated state objects
	 * @param groupCommands	List of commands represented by the command grouping data structure
	 * @return				List of state objects containing information readable by the turtle
	 * @throws CommandException
	 */
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		states = groupCommands.get(0).execute(states);
		return states;
	}
	
	protected void clearParameters() {
		parameters.clear();
	}
	
	/**
	 * Sets the dictionaries required by this command.
	 * 
	 * @param v		VariableDictionary object, used for saving and retrieving variables
	 * @param c		CommandDictionary object, used for saving and retrieving custom commands
	 * @param t		TurtleList object, used for tracking all created and currently active turtles
	 */
	public void setDictionaries(VariableDictionary v, CommandDictionary c, TurtleList t) {
	    variableDictionary = v;
	    commandDictionary = c;	
	    turtles = t;
	}
	
	/**
	 *  Add child command to this command. When this command is executed, it will call this child
	 *  command to execute itself.
	 *  
	 * @param command 	Command to be placed into the list of child commands
	 */
	public void addtoCommands(Command command) {
		commands.add(command);
	}
	
	@Override
	public Iterator<Command> iterator() {
		return commands.iterator();
	}
	
	/**
	 * @return size of the command children for this command
	 */
	public int size() {
		return commands.size();
	}
	
}
