package com.team2576.auto.commands.drive;

import com.team2576.auto.AutoCommands;

public class DriveFrontLeftArray extends AutoCommands {
	
	double[] data;
	int index;
	
	public DriveFrontLeftArray(double[] data) {
		this.data = data;
		this.index = 0;
		super.first_cycle = true;
	}
	
	public boolean update() {
		
		if(first_cycle) {
			super.start_time = super.getTime();
			super.elapsed_time = this.start_time;
			super.first_cycle = false;
		}
		if(index < data.length) {
			super.output.setFrontLeftDrive(data[index]);
			index++;
			return false;
		}
		
		return true;
	}

}
