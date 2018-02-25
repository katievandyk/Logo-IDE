package controller;

import java.util.Queue;

import model.instructions.Instruction;
import view.panels.ControlPanel;

public class Controller{
    Queue<Instruction> instructionQueue;
    String currentInput;

    public Controller() {

    }
    
    public void update(ControlPanel cpanel) {
    	currentInput = cpanel.getInput();
    }

}
