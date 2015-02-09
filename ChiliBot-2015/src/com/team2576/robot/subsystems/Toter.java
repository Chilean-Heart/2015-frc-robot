package com.team2576.robot.subsystems;

/**
*
* @author Bender
*/

import com.team2576.robot.io.*;
import com.team2576.lib.util.ChiliPID;

public class Toter implements SubComponent {
	
	private int totes;
	private double lifter_force;
	private boolean toter_error = false;
	
	private static Toter instance;
	private RobotOutput output;
	
	private double left_encoder_rate;
	private double right_encoder_rate;
	private double encoder_error;
	private double corrector;
	private ChiliPID lifter_control;
	
	public static Toter getInstance() {
		if(instance == null) {
			instance = new Toter();
		}
		return instance;
	}
	
	private Toter() {
		this.totes = 0;
		this.lifter_force = 0;
		this.left_encoder_rate = 0;
		this.right_encoder_rate = 0;
		this.encoder_error = 0;
		this.corrector = 0;
		this.lifter_control = new ChiliPID(1, 0, 0);
		
		
		output = RobotOutput.getInstance();
		
	}
	
	public int getTotes() {
		return this.totes;
	}

	public boolean update(DriverInput driver, SensorInput sensor) {
		
		this.lifter_force = driver.getJoystickThrottle();
		
		if(toter_error) {
			return false;
		}
		
		left_encoder_rate = sensor.getLeftEncoderRate();
		right_encoder_rate = sensor.getRightEncoderRate();
		encoder_error = left_encoder_rate - right_encoder_rate;
		corrector = lifter_control.calcPIDInc(encoder_error);

		this.output.setLeftLifter(lifter_force-corrector);
		this.output.setRightLifter(lifter_force+corrector);
		this.output.setLifterForce(lifter_force);
		
		return true;
	}
	
	public void disable() {
		this.toter_error = false;
		this.output.setLifters(0);
	}
}
