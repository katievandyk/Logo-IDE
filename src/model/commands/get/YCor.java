package model.commands.get;

public class YCor extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getY();
	}
}
