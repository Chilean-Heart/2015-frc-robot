package com.team2576.robot.subsystems;

/**
*
* @author Lucas
*/

import com.team2576.robot.io.*;

public class Toter implements SubComponent {
	
	private int totes;
	private double winch_force;
	private boolean toter_error = false;
	
	private static Toter instance;
	private RobotOutput output;
	
	public static Toter getInstance() {
		if(instance == null) {
			instance = new Toter();
		}
		return instance;
	}
	
	private Toter() {
		this.totes = 0;
		this.winch_force = 0;
		
		output = RobotOutput.getInstance();
	}
	
	public int getTotes() {
		return this.totes;
	}

	public boolean update(DriverInput driver, SensorInput sensor) {
		
		this.winch_force = driver.getJoystickThrottle();
		
		if(toter_error) {
			return false;
		}
		
		this.output.setWinch(this.winch_force);
		return true;
	}
	
	public void disable() {
		this.toter_error = false;
		this.output.setWinch(0);
	}
}
