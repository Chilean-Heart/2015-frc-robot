package com.team2576.robot.io;

import java.util.Vector;

import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;

/**
*
* @author Lucas
*/

public class RobotOutput implements IOComponent {
	
	private static RobotOutput instance;
	
	private final Talon front_left, rear_left, front_right, rear_right;
	private final Jaguar winch;
	
	public RobotOutput() {			
		
		front_left = new Talon(ChiliConstants.front_left_motor);
		rear_left = new Talon(ChiliConstants.rear_left_motor);
		front_right = new Talon(ChiliConstants.front_right_motor);
		rear_right = new Talon(ChiliConstants.rear_right_motor);
		winch = new Jaguar(ChiliConstants.winch_motor);
		
		front_left.setSafetyEnabled(true);
		rear_left.setSafetyEnabled(true);
		front_right.setSafetyEnabled(true);
		rear_right.setSafetyEnabled(true);
		//winch.setSafetyEnabled(true);		
	}
	
	void setFrontLeftDrive(double x) {
		this.front_left.set(x);
	}
	
	void setRearLeftDrive(double x) {
		this.rear_left.set(x);
	}
	
	void setFrontRightDrive(double x) {
		this.front_right.set(x);
	}
	
	void setRearRightDrive(double x) {
		this.rear_right.set(x);
	}
	
	void setLeftDrive(double x) {
		this.front_left.set(x);
		this.rear_left.set(x);
	}
	
	void setRightDrive(double x) {
		this.front_right.set(x);
		this.rear_right.set(x);
	}
	
	void setDrive(double fl, double rl, double fr, double rr) {
		this.front_left.set(fl);
		this.rear_left.set(rl);
		this.front_right.set(fr);
		this.rear_right.set(rr);
	}
	
	void setWinch(double x) {
		this.winch.set(x);
	}
	
	public static RobotOutput getInstance() {
		if(instance == null) {
			instance = new RobotOutput();
		}
		return instance;
	}

	public Vector<Object> shareOut() {
		return null;
	}

	public void shareIn(Vector<Object> dataOut) {
		double[] powers = (double[]) ChiliFunctions.doubleDimensionVectorValue(ChiliConstants.iDriveTrain, ChiliConstants.iPatoDriveForces, dataOut);
		setDrive(powers[ChiliConstants.iFrontLeftForce], powers[ChiliConstants.iRearLeftForce],
				 powers[ChiliConstants.iFrontRightForce], powers[ChiliConstants.iRearRightForce]);
	}
}