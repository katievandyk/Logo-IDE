package controller;

import java.util.PriorityQueue;
import java.util.Queue;

import model.instructions.Instruction;

public class Controller{
    Queue<Instruction> instructionQueue;
    
    public Controller() {
	instructionQueue = new PriorityQueue<>();
    }
    
    public Instruction getInstruction() {
	Instruction i = instructionQueue.peek();
	instructionQueue.remove();
	return i;
    }

}
