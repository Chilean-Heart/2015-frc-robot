package com.team2576.auto.commands.drive;

import com.team2576.auto.AutoCommands;

public class DriveInstantFrontRight extends AutoCommands {

	double drive_force;
	
	public DriveInstantFrontRight(double force) {
		this.drive_force = force;
	}

	public boolean update() {
		super.output.setFrontRightDrive(drive_force);
		return true;
	}
}
