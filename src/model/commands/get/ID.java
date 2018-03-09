package model.commands.get;

public class ID extends Get {

	@Override
	public double getReturnValue() {
		return turtles.activeTurtle;
	}
}
