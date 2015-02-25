package com.team2576.robot.subsystems;

/**
*
* @author Bender
*/

import com.team2576.robot.io.*;
import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
public class Toter implements SubComponent {
	
	private int totes;
	private double lifter_force;
	private boolean toter_error = false;
	private boolean correct = false;
	private boolean first_cycle = true;
	private double time_flag;
	
	private static Toter instance;
	private RobotOutput output;
	
	private double left_encoder_raw, right_encoder_raw;
	//private double encoder_error;
	private double corrector;
	//private ChiliPID lifter_control;
	
	public static Toter getInstance() {
		if(instance == null) {
			instance = new Toter();
		}
		return instance;
	}
	
	private Toter() {
		
		this.totes = 0;
		this.lifter_force = 0;
		this.left_encoder_raw = 0;
		this.right_encoder_raw = 0;
		//this.encoder_error = 0;
		this.corrector = 0;
		//this.lifter_control = new ChiliPID(0.05, 0, 0.005);		
		time_flag = Timer.getFPGATimestamp();
		output = RobotOutput.getInstance();		
	}
	
	public int getTotes() {
		return this.totes;
	}

	public boolean update(DriverInput driver, SensorInput sensor) {
		
		//Asumir que 0 es abajo
		
		if(first_cycle){
			time_flag = Timer.getFPGATimestamp();
			first_cycle = false;
		}
		
		this.lifter_force = driver.getLogitechY();
		this.left_encoder_raw = sensor.getLeftEncoderRaw();
    	this.right_encoder_raw = sensor.getRightEncoderRaw();
		
		if(toter_error) {
			return false;
		}
		
		double dif = Math.abs(left_encoder_raw - right_encoder_raw);
		
		double regulate = 1 - (Math.min(dif, ChiliConstants.kMaxPulse) / ChiliConstants.kMaxPulse);
		regulate *= lifter_force;
		
		double time_dif = Timer.getFPGATimestamp() - time_flag;
		
		/*SmartDashboard.putNumber("Left Raw", left_encoder_raw);
    	SmartDashboard.putNumber("Right Raw", right_encoder_raw);
    	SmartDashboard.putNumber("Move", lifter_force);
    	SmartDashboard.putNumber("Regulate", regulate);
    	SmartDashboard.putNumber("difference", dif);
    	SmartDashboard.putBoolean("Correct", correct);
    	SmartDashboard.putBoolean("Left limit", sensor.getLeftLimit());
    	SmartDashboard.putBoolean("Right limit", sensor.getRightLimit());
    	SmartDashboard.putNumber("Time flag", time_flag);*/
    	
    	if(sensor.getLeftLimit() && !sensor.getRightLimit() && (time_dif > ChiliConstants.kToterTimeThreshold)) {
    		this.output.setRightLifter(-0.4);
    		this.output.setLeftLifter(0);    		
    		correct = true;
    		//System.out.println("a, " + time_dif);
    	} 
    	if(sensor.getRightLimit() && !sensor.getLeftLimit() && (time_dif > ChiliConstants.kToterTimeThreshold)) {    		
    		this.output.setRightLifter(0);
    		this.output.setLeftLifter(-0.4);
    		correct = true;
    		//System.out.println("b, " + time_dif);
    	}
    	if(sensor.getRightLimit() && sensor.getLeftLimit()) {
    		time_flag = Timer.getFPGATimestamp();
    		sensor.resetRightEncoder();
    		sensor.resetLeftEncoder();
    		correct = false;
    		//System.out.println("c");
    	} else if (!sensor.getRightLimit() && !sensor.getLeftLimit()) {
    		correct = false;
    		//System.out.println("d");
    	}
		
    	if(!correct){    		
			if(this.lifter_force > 0){
	        	if(this.left_encoder_raw > this.right_encoder_raw) {
	        		//this.output.setLifters(regulate, this.lifter_force);
	        		this.output.setLifters(regulate, this.lifter_force);
	        		//System.out.println(String.format("%.2f , %.2f - 1", output.getLeftLifterForce(), output.getRightLifterForce()));

	        	}
	        	else if(this.left_encoder_raw < this.right_encoder_raw) {
	        		this.output.setLifters(this.lifter_force, regulate);
	        		
	        		//System.out.println(this.lifter_force + ", " + (-1 * regulate) + " - 2");
	        		//System.out.println(String.format("%.2f , %.2f - 2", (this.lifter_force), (-1 * regulate)));
	        	}
	        	else if(this.left_encoder_raw == this.right_encoder_raw) {
	        		this.output.setLifters(this.lifter_force, this.lifter_force);
	        		
	        		//System.out.println(this.lifter_force + ", " + (-1 * this.lifter_force) + " - 3");
 	        		//System.out.println(String.format("%.2f , %.2f - 3", (this.lifter_force), (-1 * this.lifter_force)));
	        	}
	    	}
	    	else if(this.lifter_force < 0) {
	    		if(this.left_encoder_raw > this.right_encoder_raw) {
	    			this.output.setLifters(this.lifter_force, regulate);
	    			
	    			//System.out.println(this.lifter_force + ", " + (-1 * regulate) + " - 4");
	    			//System.out.println(String.format("%.2f , %.2f - 4", (this.lifter_force), (-1 * regulate)));
	        	}
	        	else if(this.left_encoder_raw < this.right_encoder_raw) {
	        		this.output.setLifters(regulate, this.lifter_force);
	        		
	        		//System.out.println(regulate + ", " + (-1 * this.lifter_force) + " - 5");
	        		//System.out.println(String.format("%.2f , %.2f - 5", regulate, (-1 * this.lifter_force)));
	        	}
	        	else if(this.left_encoder_raw == this.right_encoder_raw) {
	        		this.output.setLifters(this.lifter_force, this.lifter_force);
	        		
	        		//System.out.println(this.lifter_force + ", " + (-1 * this.lifter_force) + " - 6");
	        		//System.out.println(String.format("%.2f , %.2f - 6", (this.lifter_force), (-1 * this.lifter_force)));
	        	}
	    	} else {
	    		this.output.setLifters(0, 0);
	    	}
			/*System.out.println(String.format("LEnc: %f (%b); REnc: %f (%b)", sensor.getLeftEncoderRaw(), sensor.getLeftLimit(), 
					sensor.getRightEncoderRaw(), sensor.getRightLimit()));*/
    	}
    	
		//SmartDashboard.putNumber("Left power", RobotOutput.left_lifter_force);
		//SmartDashboard.putNumber("Right power", RobotOutput.right_lifter_force);
		
		
		/*
		left_encoder_rate = sensor.getLeftEncoderRate();
		right_encoder_rate = sensor.getRightEncoderRate();
		encoder_error = left_encoder_rate - right_encoder_rate;
		corrector = lifter_control.calcPID(encoder_error);
		
		this.output.setLeftLifter(lifter_force);
		this.output.setRightLifter(lifter_force);
		
		
		this.output.setLifterForce(lifter_force);*/
		
		return true;
	}
	
	public void disable() {
		this.toter_error = false;
		this.output.setLifters(0, 0);
	}
}
