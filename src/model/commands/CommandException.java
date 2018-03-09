package model.commands;

/**
 * @author Martin Muenster
 * 
 * The CommandException is thrown when command objects contain the wrong types of commands as children,
 * when illegal values are given, or when the number of children is incorrect.
 *
 */
public class CommandException extends Exception{
	/**
     * 
     */
    private static final long serialVersionUID = 8094616888920605623L;

	public CommandException(String message) {
        super(message);
    }

}
