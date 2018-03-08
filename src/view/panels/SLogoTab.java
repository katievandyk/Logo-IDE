package view.panels;

import javafx.scene.control.Tab;
import model.ModelController;

public class SLogoTab extends Tab {

    private ModelController CONTROLLER;

    public SLogoTab(ModelController c) {
	CONTROLLER = c;
    }
    
    public ModelController getModel() {
	return CONTROLLER;
    }

}
