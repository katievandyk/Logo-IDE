package view.panels;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Tab panel that holds all tabs and handles switching between tabs. 
 * 
 * @author Katherine Van Dyk
 *
 */
public class TabPanel {

    private HBox tabs;
    private TabPane tabPane;
    private Tab addTab;
    private Controller CONTROLLER;
    private int counter;

    /**
     * Constructs tab pane and sets behavior for tabs in tab pane when they are selected.
     * Source: https://stackoverflow.com/questions/17018562/how-to-create-tabs-with-icons-in-javafx
     * 
     * @param c: Main controller that switches between different instances of modelController (e.g. 
     * the different instances of SLogo in each tab)
     */
    public TabPanel(Controller c) {
	counter = 0;
	tabPane = new TabPane();
	CONTROLLER = c;
	tabConstructor();
	makeAddTab();
	tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
	    @Override
	    public void changed(ObservableValue<? extends Tab> arg0, Tab oldTab, Tab newTab) {
		int index = tabPane.getTabs().indexOf(newTab);
		if(index == counter -1) return;
		CONTROLLER.switchContext(index);
	    }
	});
    }

    /**
     * @return TabPane containing all tabs within HBoxes
     */
    public TabPane construct() {
	tabs = new HBox(tabPane);
	tabs.setPadding(new Insets(0,0,0,0));
	return tabPane;
    }

    /**
     * @return Adds new tab object to tab pane
     */
    public Tab tabConstructor() {
	Tab tab = constructTab();
	counter++;
	tabPane.getTabs().add(tab);
	return tab;
    }

    /**
     * @return tab with appropriate graphic
     */
    private Tab constructTab() {
	HBox temp = new HBox();
	Tab tab = new Tab();
	Circle circle = new Circle(0, 0, 10);
	circle.setFill(Color.PALEGREEN);
	tab.setGraphic(circle);
	temp.setAlignment(Pos.CENTER);
	tab.setContent(temp);
	return tab;
    }

    /**
     * Makes and sets behavior of addTab, which creates new tabs to be displayed
     */
    private void makeAddTab() {
	addTab = tabConstructor();
	addTab.setText("+");
	addTab.setOnSelectionChanged(new EventHandler<Event>() {
	    @Override
	    public void handle(Event t) {
		if (addTab.isSelected()) {
		    tabConstructor();
		    Tab currTab = tabPane.getTabs().get(tabPane.getTabs().size() -1);
		    tabPane.getSelectionModel().select(currTab);
		    CONTROLLER.addContext();
		    tabPane.getTabs().remove(addTab);
		    tabPane.getTabs().add(addTab);
		}
	    }
	    });
    }
}