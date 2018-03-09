package model.commands;

/**
 * @author Martin Muenster
 * 
 * The CommandException is thrown when command objects contain the wrong types of commands as children,
 * when illegal values are given, or when the number of children is incorrect.
 *
 */
public class CommandException extends Exception{
	public CommandException(String message) {
        super(message);
    }

}
