package model.commands.get;

public class Heading extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getAngle();
	}
}
