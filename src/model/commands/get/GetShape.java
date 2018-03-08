package model.commands.get;

public class GetShape extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getShape();
	}

}
