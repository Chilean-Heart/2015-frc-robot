package com.team2576.auto.commands.drive;

import com.team2576.auto.AutoCommands;

public class DriveInstantFrontLeft extends AutoCommands {
	
	double drive_force;
	
	public DriveInstantFrontLeft(double force) {
		this.drive_force = force;
	}

	public boolean update() {
		super.output.setFrontLeftDrive(drive_force);
		return true;
	}

}
