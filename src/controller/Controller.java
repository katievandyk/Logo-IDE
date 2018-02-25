package controller;

import java.util.PriorityQueue;
import java.util.Queue;

import model.instructions.Instruction;
import view.panels.ControlPanel;
import view.panels.TurtlePanel;
import model.instructions.InstructionGenerator;

public class Controller{
    Queue<Instruction> instructionQueue;
    String currentInput;
    InstructionGenerator INSTRUCTION_GENERATOR;

    public Controller() {
	INSTRUCTION_GENERATOR = new InstructionGenerator();
    }
    
    public executeInstruction() {
	while()
    }
    
    public void update(ControlPanel cpanel) {
    	currentInput = cpanel.getInput();
    }

}
