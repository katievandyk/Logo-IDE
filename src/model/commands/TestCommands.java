package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.commands.control.Repeat;
import model.commands.math.*;
import model.commands.set.*;
import model.state.State;

public class TestCommands {
	public static void main(String args[]) {
		List<Command> queue = new LinkedList<Command>();
		Backward h = new Backward();
		
		
		Repeat a = new Repeat();
		Value k = new Value();
		k.parameters.add(3.0);
		a.commands.add(k);
		Forward b = new Forward();
		Quotient c = new Quotient();
		Value d = new Value();
		d.parameters.add(10.0);
		Right e = new Right();
		Value f = new Value();
		f.parameters.add(5.0);
		Minus l = new Minus();
		l.commands.add(f);
		e.commands.add(l);
		c.commands.add(d);
		c.commands.add(e);
		b.commands.add(c);
		a.commands.add(b);
		h.commands.add(a);
		queue.add(h);
		
		List<State> g = new LinkedList<State>();
		State z = new State(4,9,0,true, true);
		
		for (Command j : queue) {
			try {
				g = (j.execute(z));
			} catch (CommandException e1) {
				e1.printStackTrace();
			}
		}

		for (State m : g) {
			System.out.println(m.toString());
		}
	}
}
