package view.factory;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonFactory {
    
    
    public Button styledButton(String stringImage, String ID) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	button.setId(ID);
	return button;
    }
    
    public Button textButton(String text, String ID) {
	Button button = new Button();
	button.setText(text);
	button.setId(ID);
	return button;
    }
    
   public Button imageButton(String stringImage) {
	Button button = new Button();
	Image img = new Image(getClass().getResourceAsStream(stringImage));
	button.setGraphic(new ImageView(img));
	return button;
    }
}