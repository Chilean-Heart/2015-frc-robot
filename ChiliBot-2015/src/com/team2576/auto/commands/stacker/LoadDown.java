package com.team2576.auto.commands.stacker;

/**
*
* @author Pato
*/

import com.team2576.auto.AutoCommands;

public class LoadDown extends AutoCommands{
	
	public LoadDown() {
		super.first_cycle = true;
	}

	public boolean update() {
		if(first_cycle){
			super.start_time = super.getTime();
			super.elapsed_time = super.start_time;
			super.first_cycle = false;
		}
		if((super.elapsed_time - super.start_time) < 3){
			super.output.setLifters(-1);
			return false;
		}
		super.elapsed_time = super.getTime();
		
		return true;
	}

}