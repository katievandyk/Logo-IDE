package model.commands.get;

public class IsShowing extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getShowing() ? 1 : 0;
	}
}
