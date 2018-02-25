package model.instructions;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import model.commands.Command;

/**
 * Generates a list of executable actions for turtle object
 * 
 * @author Katherine Van Dyk
 *
 */
public class InstructionGenerator {
    
    private Queue<Instruction> INSTRUCTIONS;
    
    /**
     * Constructor
     */
    public InstructionGenerator() {
	INSTRUCTIONS = new PriorityQueue<>();
    }
    
    /**
     * Compiles a list of instructions from a list of commands
     * 
     * @param commands
     */
    public void CompileCommands(Queue<Command> commands) {
	while(commands.size() > 0) {
	    Command c = commands.peek();
	    commands.remove();
	    addToQueue(c.execute());	
	}
    }
    
    /**
     * Used by controller to access instructions
     * 
     * @return Instruction: executable action for turtle
     */
    public Instruction getInstruction() {
	Instruction ret = INSTRUCTIONS.peek();
	INSTRUCTIONS.remove();
	return ret;
    }
    
    /**
     * Helper method to add instructions to queue
     * 
     * @param instructions: List of instructions generated from one command
     */
    private void addToQueue(List<Instruction> instructions) {
	for(Instruction instruction : instructions) {
	    INSTRUCTIONS.add(instruction);
	}
    }

    
    

}
