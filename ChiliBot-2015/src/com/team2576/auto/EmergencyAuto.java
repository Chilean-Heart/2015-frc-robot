package com.team2576.auto;

import com.team2576.robot.io.DriverInput;
import com.team2576.robot.io.RobotOutput;
import com.team2576.robot.io.SensorInput;

import edu.wpi.first.wpilibj.Timer;

public class EmergencyAuto {
	
	RobotOutput output;
	SensorInput input;
	DriverInput driver;
	
	private double auto_timer = 0;
	
	private static EmergencyAuto instance;
	
	public static EmergencyAuto getInstance() {
		if(instance == null) {
			instance = new EmergencyAuto();
		}
		return instance;
	}
	
	public boolean startAuto() {
		auto_timer = Timer.getFPGATimestamp();
		return true;
	}
	
	public boolean runAuto() {
		while(Timer.getFPGATimestamp() - auto_timer < 2)
		
		return true;
		return false;
	}
}
