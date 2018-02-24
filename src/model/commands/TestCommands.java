package model.commands;

import java.util.List;

import model.instructions.*;
public class TestCommands {
	public static void main(String args[]) {
		Repeat a = new Repeat();
		a.parameter = 3;
		Move b = new Move();
		Sum c = new Sum();
		Value d = new Value();
		d.val = 5;
		Move e = new Move();
		Value f = new Value();
		f.val = 5;
		e.commands.add(f);
		c.commands.add(d);
		c.commands.add(e);
		b.commands.add(c);
		a.commands.add(b);
		List<Instruction> g = a.execute();
		System.out.println(g.toString());
	}
}
