package view.panels;

import javafx.scene.control.Tab;

public class SLogoTab extends Tab {

    private int index;
    private boolean adder;

    public SLogoTab(int i) {
	index = i;
	adder = false;
    }
    
    public int getIndex() {
	return index;
    }
    
    public void setAdder() {
	adder = true;
    }
    
    public boolean getAdder() {
	return adder;
    }


}

