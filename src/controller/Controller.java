package controller;

import java.util.PriorityQueue;
import java.util.Queue;

import model.instructions.Instruction;
import view.panels.ControlPanel;
import view.panels.TurtlePanel;

public class Controller{
    Queue<Instruction> instructionQueue;
    String currentInput;
    
    public Controller() {
	instructionQueue = new PriorityQueue<>();
    }
    
    public Instruction getInstruction() {
	Instruction i = instructionQueue.peek();
	instructionQueue.remove();
	return i;
    }
    
    public void update(ControlPanel cpanel) {
    	currentInput = cpanel.getInput();
    }

}
