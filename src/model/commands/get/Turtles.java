package model.commands.get;

public class Turtles extends Get {

	@Override
	public double getReturnValue() {
		return turtles.numTurtles();
	}

}
