package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.commands.math.*;
import model.instructions.*;
import model.state.State;
public class TestCommands {
	public static void main(String args[]) {
		List<Command> queue = new LinkedList<Command>();
		Move h = new Move();
		
		
		Repeat a = new Repeat();
		Value k = new Value();
		k.parameters.add(3.0);
		a.commands.add(k);
		Move b = new Move();
		Quotient c = new Quotient();
		Value d = new Value();
		d.parameters.add(5.0);
		Move e = new Move();
		Value f = new Value();
		f.parameters.add(6.0);
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
		State z = new State(4,9,0,true);
		g.add(z);
		
		for (Command j : queue) {
			try {
				g = (j.execute(g));
			} catch (CommandException e1) {
				e1.printStackTrace();
			}
		}

		for (State m : g) {
			System.out.println(m.toString());
		}
	}
}
