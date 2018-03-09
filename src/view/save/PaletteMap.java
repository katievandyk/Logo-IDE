package view.save;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class PaletteMap {

    private Map<String ,Map<String, String>> paletteMap;
    private final ResourceBundle PALETTES = ResourceBundle.getBundle("resources/settings/palettes");
    private final String[] labels = {"Background Color", "Pen Color", "Pen Size", "Turtle Image"};

    public PaletteMap(){
	paletteMap = new HashMap<String, Map<String, String>>();
	parsePalettes();
    }

    private void parsePalettes() {
	for(String index : PALETTES.keySet()) {
	    paletteMap.put(index, parseValues(PALETTES.getString(index)));
	}
    }

    private Map<String, String> parseValues(String line){
	HashMap<String, String> values = new HashMap<String, String>();
	String[] split = line.split(" ");
	for(int i = 0; i < 4; i++) {
	    values.put(labels[i], split[i]);
	}
	return values;
    }
    
    public Map<String, Map<String, String>> getMap(){
	return paletteMap;
    }
    
    public Color getBackgroundColor(String index){
	return Color.web(paletteMap.get(index).get(labels[0]));
    }
    
    public Color getPenColor(String index) {
	return Color.web(paletteMap.get(index).get(labels[1]));
    }
    
    public int getPenThickness(String index) {
	return Integer.parseInt(paletteMap.get(index).get(labels[2]));
    }
    
    public ImageView getShape(String index) {
	return makeImage(paletteMap.get(index).get(labels[3]));
    } 

    /**
     * Image creator
     */
    private ImageView makeImage(String img) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	return new ImageView(temp);
    }
    
    public void editMap(String index, Color val) {
	paletteMap.get(index).put(labels[0], val.toString());
    }

}
