package com.team2576.auto;

import java.util.Vector;

public class AutoGenerator {
	
	private Vector<AutoCommands> commands;
	
	public AutoGenerator() {
		this.commands = new Vector<AutoCommands>();
	}
	
	public void addCommand(AutoCommands command){
		this.commands.add(command);
	}
	
	public AutoCommands[] generateRoutine() {
		//Add auto ender
		this.commands.add(null);
		AutoCommands[] routine = new AutoCommands[this.commands.size()];
		this.commands.copyInto(routine);
		return routine;
	}
}
