package view.panels;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import model.ModelController;

public class TabPanel extends Panel {

    private HBox tabs;
    private TabPane tabPane;
    private Button addButton;
    private Controller CONTROLLER;

    public TabPanel(Controller c, ModelController main) {
	tabPane = new TabPane();
	tabConstructor(main);
	
	// Source: https://stackoverflow.com/questions/14691138/how-can-i-do-some-action-when-one-specific-tab-is-selected-using-javafx
	tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
	    @Override
	    public void changed(ObservableValue<? extends Tab> arg0, Tab oldTab, Tab newTab) {
		ModelController updatedModel = ((SLogoTab) newTab).getModel();
		updatedModel.initialize();
		CONTROLLER.switchContext(updatedModel);
	    }
	});

	CONTROLLER = c;
	addButton = BUTTON.textButton("add", "label");
	addButton.setOnAction(click->{ 
	    ModelController mc = new ModelController();
	    tabConstructor(mc);
	    SLogoTab currTab = (SLogoTab) tabPane.getTabs().get(tabPane.getTabs().size() -1);
	    tabPane.getSelectionModel().select(currTab);
	    CONTROLLER.addContext(mc);});

    }

    public HBox construct() {
	tabs = new HBox(tabPane, addButton);
	return tabs;
    }

    public void tabConstructor(ModelController c) {
	SLogoTab sLogoTab = constructTab(c);
	tabPane.getTabs().add(sLogoTab);
    }

    private SLogoTab constructTab(ModelController c) {
	HBox temp = new HBox();
	SLogoTab sLogoTab = new SLogoTab(c);
	sLogoTab.setGraphic(new Circle(0, 0, 10));
	sLogoTab.setContent(temp);
	return sLogoTab;
    }

}
