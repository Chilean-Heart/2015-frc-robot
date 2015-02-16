package com.team2576.auto.commands.stacker;

/**
*
* @author Lucas
*/

import com.team2576.auto.AutoCommands;

public class LoadUp extends AutoCommands{
	
	public LoadUp() {
		super.first_cycle = true;
	}

	public boolean update() {
		if(first_cycle){
			super.start_time = super.getTime();
			super.elapsed_time = super.start_time;
			super.first_cycle = false;
		}
		if((super.elapsed_time - super.start_time) < 3 && !super.sensorData.getTopLeftLimit() && !super.sensorData.getTopRightLimit()){
			super.output.setLifters(1);
			return false;
		}
		return true;
	}

}
