package view.turtle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle implements ActionListener {
    
    private boolean penUp;
    private ImageView image;
    private double x; 
    private double y;
    
    public Turtle(String img, int screenHeight, int screenWidth) {
	this.image = makeImage(img, screenHeight, screenWidth);
	this.x = image.getX();
	this.y = image.getY();
	this.penUp = false;
    }
    
    private double xLocation() {
	return this.x;
	
    }
    
    private double yLocation() {
	return this.y;
    }
    
    private boolean penUp() {
	return this.penUp;
    }
    
    
    public ImageView display() {
	return this.image;
    }
    
   
    private ImageView makeImage(String img, int height, int width) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	ImageView image = new ImageView(temp);
	image.setX(width / 2);
	image.setY(height / 2);
	return image;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
    } 
}
