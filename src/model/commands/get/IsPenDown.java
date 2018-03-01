package model.commands.get;

public class IsPenDown extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getPen() ? 1 : 0;
	}
}
