package controller;

import view.panels.ControlPanel;

public class Controller{
    String currentInput;

    public Controller() {

    }
    
    public void update(ControlPanel cpanel) {
    	currentInput = cpanel.getInput();
    }

}
