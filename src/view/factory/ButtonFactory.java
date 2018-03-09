package view.factory;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Factory class that handles the creation of different types of buttons,
 * styled and not styled, and with images/text
 * 
 * @author Katherine Van Dyk
 */
public class ButtonFactory {

    /**
     * Creates styled button with CSS tag @param ID and 
     * image @param stringImage
     * 
     * @return Button object
     */
    public Button styledButton(String stringImage, String ID) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	button.setId(ID);
	return button;
    }

    /**
     * Creates styled button with CSS tag @param ID and 
     * text @param text
     * 
     * @return Button object
     */
    public Button textButton(String text, String ID) {
	Button button = new Button();
	button.setText(text);
	button.setId(ID);
	return button;
    }

    /**
     * Creates unstyled button with image @param stringImage
     * 
     * @return Button object
     */
    public Button imageButton(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
}