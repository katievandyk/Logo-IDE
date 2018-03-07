package view.factory;

import javafx.stage.FileChooser;

public class ChooserFactory {
    
    public FileChooser fileChooser(String prompt) {
	FileChooser fc = new FileChooser();
	fc.setTitle(prompt);
	return fc;
    }

}
