package com.team2576.auto.commands.drive;

/**
*
* @author Lucas
*/

import com.team2576.auto.AutoCommands;
import com.team2576.lib.util.ChiliConstants;

public class DriveStraightTime extends AutoCommands{
	
	double drive_time, drive_force;
	
	public DriveStraightTime(double time, double force) {
		this.drive_time = time;
		this.drive_force = force;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
