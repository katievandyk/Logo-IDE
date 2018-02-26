package view.turtle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle {
    
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
    
    public ImageView changeImage(double x, double y) {
	image.setX(x);
	image.setY(y);
	return image;
    }
   
    private ImageView makeImage(String img, double height, double width) {
	Image temp = new Image(getClass().getClassLoader().getResourceAsStream(img));
	image = new ImageView(temp);
	image.setX(width / 2);
	image.setY(height / 2);
	return image;
    }

}
