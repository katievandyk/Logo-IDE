package model.commands.get;

public class XCor extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getX();
	}
}
