package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;

/**
*
* @author Lucas
*/

public class RobotOutput {
	
	private static RobotOutput instance;
	
	private final Talon front_left, rear_left, front_right, rear_right;
	private final Jaguar winch;
	
	public static RobotOutput getInstance() {
		if(instance == null) {
			instance = new RobotOutput();
		}
		return instance;
	}
	
	private RobotOutput() {			
		
		front_left = new Talon(ChiliConstants.front_left_motor);
		rear_left = new Talon(ChiliConstants.rear_left_motor);
		front_right = new Talon(ChiliConstants.front_right_motor);
		rear_right = new Talon(ChiliConstants.rear_right_motor);
		winch = new Jaguar(ChiliConstants.winch_motor);
		
		front_left.setSafetyEnabled(true);
		rear_left.setSafetyEnabled(true);
		front_right.setSafetyEnabled(true);
		rear_right.setSafetyEnabled(true);
		winch.setSafetyEnabled(true);		
	}
	
	private void setFrontLeftDrive(double x) {
		this.front_left.set(x);
	}
	
	private void setRearLeftDrive(double x) {
		this.rear_left.set(x);
	}
	
	private void setFrontRightDrive(double x) {
		this.front_right.set(x);
	}
	
	private void setRearRightDrive(double x) {
		this.rear_right.set(x);
	}
	
	private void setLeftDrive(double x) {
		this.front_left.set(x);
		this.rear_left.set(x);
	}
	
	private void setRightDrive(double x) {
		this.front_right.set(x);
		this.rear_right.set(x);
	}
	
	private void setDrive(double fl, double rl, double fr, double rr) {
		this.front_left.set(fl);
		this.rear_left.set(rl);
		this.front_right.set(fr);
		this.rear_right.set(rr);
	}
	
	private void setWinch(double x) {
		this.winch.set(x);
	}
}