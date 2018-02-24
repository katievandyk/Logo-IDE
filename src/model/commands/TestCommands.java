package model.commands;

import java.util.LinkedList;
import java.util.List;

import model.instructions.*;
public class TestCommands {
	public static void main(String args[]) {
		List<Command> queue = new LinkedList<Command>();
		Move h = new Move();
		
		
		Repeat a = new Repeat();
		a.parameters.add(3.0);
		Move b = new Move();
		Sum c = new Sum();
		Value d = new Value();
		d.parameters.add(5.0);
		Move e = new Move();
		Value f = new Value();
		f.parameters.add(6.0);
		e.commands.add(f);
		c.commands.add(d);
		c.commands.add(e);
		b.commands.add(c);
		a.commands.add(b);
		h.commands.add(a);
		queue.add(h);
		
		List<Instruction> g = new LinkedList<Instruction>();
		for (Command j : queue) {
			try {
				g.addAll(j.execute());
			} catch (CommandException e1) {
				e1.printStackTrace();
			}
		}

		for (Instruction m : g) {
			System.out.println(m.toString());
		}
	}
}
