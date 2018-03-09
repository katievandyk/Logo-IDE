package view.save;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Map containing all current palettes and their attributes. Constructed initially from 
 * palettes resource file.
 * 
 * @author Katherine Van Dyk
 */
public class PaletteMap {

    private Map<String ,Map<String, String>> paletteMap;
    private final ResourceBundle PALETTES = ResourceBundle.getBundle("resources/settings/palettes");
    private final String[] labels = {"Background Color", "Pen Color", "Pen Size", "Turtle Image"};

    /**
     * Creates initial palettes map by parsing resources file
     */
    public PaletteMap(){
	paletteMap = new HashMap<String, Map<String, String>>();
	parsePalettes();
    }

    /**
     * Parses each index of palettes map and places into outer map
     */
    private void parsePalettes() {
	for(String index : PALETTES.keySet()) {
	    paletteMap.put(index, parseValues(PALETTES.getString(index)));
	}
    }

    /**
     * Maps attributes of each palette to their tags. Places in inner map.
     * 
     * @param line: Line of resource file (e.g. palette) to be parsed
     * @return Map mapping attribute of palette to value
     */
    private Map<String, String> parseValues(String line){
	HashMap<String, String> values = new HashMap<String, String>();
	String[] split = line.split(" ");
	for(int i = 0; i < 4; i++) {
	    values.put(labels[i], split[i]);
	}
	return values;
    }
    
    /**
     * @return Current palette map
     */
    public Map<String, Map<String, String>> getMap(){
	return paletteMap;
    }
    
    /**
     * @return Background color attribute of palette @param index
     */
    public Color getBackgroundColor(String index){
	return Color.web(paletteMap.get(index).get(labels[0]));
    }
    
    /**
     * @return Pen color attribute of palette @param index
     */
    public Color getPenColor(String index) {
	return Color.web(paletteMap.get(index).get(labels[1]));
    }
    
    /**
     * @return Pen thickness attribute of palette @param index
     */
    public int getPenThickness(String index) {
	return Integer.parseInt(paletteMap.get(index).get(labels[2]));
    }
    
    /**
     * @return Turtle shape attribute of palette @param index
     */
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
    
    /**
     * Changes background color to @param val in palette of @param index
     */
    public void editMap(String index, Color val) {
	paletteMap.get(index).put(labels[0], val.toString());
    }

}
