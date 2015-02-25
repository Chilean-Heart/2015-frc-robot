package com.team2576.auto.commands.stacker;

/**
*
* @author Pato
*/

import com.team2576.auto.AutoCommands;
import com.team2576.lib.util.ChiliPID;

public class LoadUp extends AutoCommands{
	
	double left_count, right_count, prev_left, prev_right;
	ChiliPID toter_pid;
	
	public LoadUp() {
		super.first_cycle = true;
		toter_pid = new ChiliPID();
	}

	public boolean update() {
		if(first_cycle){
			super.start_time = super.getTime();
			super.elapsed_time = super.start_time;
			super.first_cycle = false;
			super.sensorData.resetEncoders();
		}
		if((super.elapsed_time - super.start_time) < 3){
			left_count = super.sensorData.getLeftEncodeCount();
			right_count = super.sensorData.getRightEncoderCount();
			super.output.setLifters(1, 1);
			return false;
		}
		super.elapsed_time = super.getTime();
		
		return true;
	}

}