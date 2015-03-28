package com.team2576.auto;

import com.team2576.robot.io.DriverInput;
import com.team2576.robot.io.RobotOutput;
import com.team2576.robot.io.SensorInput;

import edu.wpi.first.wpilibj.Timer;

/**
*
* @author Lucas
*/


public abstract class AutoCommands {
	public abstract boolean update();
	
	protected RobotOutput output;
	protected DriverInput driverData;
	protected SensorInput sensorData;
	
	protected double elapsed_time, start_time;
	protected boolean first_cycle;
	
	protected AutoCommands() {
		output = RobotOutput.getInstance();
		driverData = DriverInput.getInstance();
		sensorData = SensorInput.getInstance();
	}
	
	protected double getTime() {
		return Timer.getFPGATimestamp();
	}
}
 