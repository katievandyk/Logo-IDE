package model.commands.get;

public class ID extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getID();
	}
}
