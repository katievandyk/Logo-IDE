package view.turtle;

/**
 * Used for ADDITION.md
 * Turtle interface that encapsulates unnecessary information from the Frontend.
 * 
 * @author katherinevandyk
 *
 */
public interface FrontendTurtle {

    /**
     * @return String image of the Turtle
     */
    String image();
    
    /**
     * @return int ID of the turtle
     */
    public int getID();

    /**
     * Changes the image of the Turtle to @param string
     */
    public void changeImage(String string);

}
