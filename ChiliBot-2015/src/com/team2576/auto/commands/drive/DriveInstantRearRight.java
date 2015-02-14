package com.team2576.auto.commands.drive;

import com.team2576.auto.AutoCommands;

public class DriveInstantRearRight extends AutoCommands {

	double drive_force;
	
	public DriveInstantRearRight(double force) {
		this.drive_force = force;
	}

	public boolean update() {
		super.output.setRearRightDrive(drive_force);
		return true;
	}
}
