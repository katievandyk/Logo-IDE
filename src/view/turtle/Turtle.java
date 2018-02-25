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
    private double headAngle;
    
    public Turtle(String img, double screenHeight, double screenWidth) {
	this.image = makeImage(img, screenHeight, screenWidth);
	this.x = image.getX();
	this.y = image.getY();
	this.headAngle = 90;
	this.penUp = false;
    }
    
    public double xLocation() {
	return this.x;
	
    }
    
    public double yLocation() {
	return this.y;
    }
    
    public void setAngle(double angle) {
	this.headAngle = angle;
    }
    
    public boolean penUp() {
	return this.penUp;
    }
    
    public double getAngle() {
	return this.headAngle;
    }
    
    public ImageView display() {
	return this.image;
    }
    
   
    private ImageView makeImage(String img, double height, double width) {
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
