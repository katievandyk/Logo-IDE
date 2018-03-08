package model.commands.get;

public class GetPenColor extends Get {

	@Override
	public double getReturnValue() {
		return currentState.getPencolor();
	}

}
