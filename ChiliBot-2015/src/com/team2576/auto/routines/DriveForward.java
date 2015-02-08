package com.team2576.auto.routines;

/**
*
* @author Lucas
*/

import com.team2576.auto.AutoCommands;
import com.team2576.auto.AutoGenerator;
import com.team2576.auto.commands.drive.DriveStraightTime;

public class DriveForward implements AutoRoutines{

	private final double time_to_drive = 2;
	private final double force_to_drive = 0.6;
	
	public AutoCommands[] generateSequence() {
		AutoGenerator commmands = new AutoGenerator();
		commmands.addCommand(new DriveStraightTime(this.time_to_drive, this.force_to_drive));
		return commmands.generateRoutine();
	}

}
