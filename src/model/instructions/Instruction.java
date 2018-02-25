package model.instructions;

public abstract class Instruction {
	public double param;
	public abstract void run();
	
	public String toString() {
		return (this.getClass().toString() + " " + param);
	}
}
