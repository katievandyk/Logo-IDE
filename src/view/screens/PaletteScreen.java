package view.screens;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.factory.TextFactory;
import view.save.PaletteMap;

public class PaletteScreen {

    private TextFactory TEXT; 
    private PaletteMap paletteMap;

    public PaletteScreen(PaletteMap map) {
	TEXT = new TextFactory();
	paletteMap = map;
	Stage palletteStage = new Stage();
	Group root = new Group();
	root.getChildren().add(parseMap());
	palletteStage.setScene(new Scene(root, 300, 100));
	palletteStage.show();
	palletteStage.centerOnScreen();
    }

    private VBox parseMap() {
	VBox ret = new VBox(12);
	for(String key : paletteMap.getMap().keySet()) {
	    Text index = new Text("Index " + key + ": ");
	    Rectangle backgroundColor = makeRectangle(paletteMap.getBackgroundColor(key));
	    Rectangle penColor = makeRectangle(paletteMap.getPenColor(key));
	    Text penSize = makeLabel(Integer.toString(paletteMap.getPenThickness(key)));
	    ImageView image = paletteMap.getShape(key);
	    ret.getChildren().add(new HBox(12, index, backgroundColor, penColor, penSize, image));
	}
	return ret;
    }

    private Rectangle makeRectangle(Color c) {
	Rectangle rect = new Rectangle(20, 20);
	rect.setFill(c);
	return rect;
    }

    private Text makeLabel(String text) {
	return TEXT.styledText(text, "label");
    }
}