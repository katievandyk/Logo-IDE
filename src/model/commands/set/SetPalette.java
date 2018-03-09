package model.commands.set;

import model.commands.CommandException;
import model.state.State;

public class SetPalette extends Set {

	@Override
	protected State setNextState(State nextState) {
		nextState.setPalette(parameters.get(0).intValue());
		int[] rgb = {parameters.get(1).intValue(), parameters.get(2).intValue(), parameters.get(3).intValue()};
		nextState.setPaletteRGB(rgb);
		return nextState;
	}

	@Override
	public double getReturnValue() {
		return parameters.get(0);
	}
}
