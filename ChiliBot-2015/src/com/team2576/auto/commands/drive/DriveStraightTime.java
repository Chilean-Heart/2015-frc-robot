package com.team2576.auto.commands.drive;

/**
*
* @author Lucas
*/

import com.team2576.auto.AutoCommands;

public class DriveStraightTime extends AutoCommands{
	
	double drive_time, drive_force;
	
	public DriveStraightTime(double time, double force) {
		this.drive_time = time;
		this.drive_force = force;
		super.first_cycle = true;
	}

	public boolean update() {
		if(first_cycle) {
			super.start_time = super.getTime();
			super.elapsed_time = this.start_time;
			super.first_cycle = false;
		}
		if((super.elapsed_time - super.start_time) < this.drive_time) {
			super.output.setAllDrives(this.drive_force);
			super.elapsed_time = super.getTime();
			return false;
		}
		return true;
	}
}
