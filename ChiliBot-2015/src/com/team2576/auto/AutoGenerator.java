package com.team2576.auto;

/**
*
* @author Lucas
*/

import java.util.Vector;

import com.team2576.auto.commands.AutoStop;
import com.team2576.lib.util.ChiliConstants;

public class AutoGenerator {
	
	private Vector<AutoCommands> commands;
	
	public AutoGenerator() {
		this.commands = new Vector<AutoCommands>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
	}
	
	public void addCommand(AutoCommands command){
		this.commands.add(command);
	}
	
	public AutoCommands[] generateRoutine() {
		//Add auto ender
		this.commands.add(new AutoStop());
		AutoCommands[] routine = new AutoCommands[this.commands.size()];
		this.commands.copyInto(routine);
		return routine;
	}
}
