package com.team2576.robot.subsystems;

import java.util.Vector;

import com.team2576.lib.util.ChiliFunctions;
import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.Timer;


/**
*
* @author Pato
*/

public class PatoDrive implements SubComponent {
	
    private static final double TIME_BETWEEN_TOGGLES = 0.1;
	private static final double DRIVE_TYPES = 6;
	private boolean use_gyro;
    private double mode_selector = 0;
    private double time_marker;
    private static PatoDrive instance;
    private Vector<Object> dataDrive;
    
    public static PatoDrive getInstance() {
    	if(instance == null) {
    		instance = new PatoDrive();
    	}
    	return instance;
    }
    
    public PatoDrive(){
    	dataDrive = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
        this.use_gyro = ChiliConstants.use_gyro;
        time_marker = Timer.getFPGATimestamp();
    }
    
    
	private double[] mecanumDrive(double hor, double ver, double rotate,double gyro){
        //Rotation deadband
        if(Math.abs(rotate) < 0.1){
            rotate = 0;
        }
        
        //Translation deadband
        if(Math.abs(hor*hor + ver*ver) < 0.1){
            hor = 0;
            ver = 0;
        }
        
        //Initialize gyro_deg variable
        double gyro_deg = 180;
        
        //If gyro is used, assign real value to gyro_deg
        if(use_gyro){
            gyro_deg = gyro % 360 + 180;
        }
        
        //Prevent flipflop of values
        if (gyro_deg > -2 && gyro_deg < 2) gyro_deg = 0;
        if (gyro_deg > 88 && gyro_deg < 92) gyro_deg = 90;
        if (gyro_deg > 178 && gyro_deg < 182) gyro_deg = 180;
        if (gyro_deg > 268 && gyro_deg < 272) gyro_deg = 270;
        
        if (gyro_deg > -92 && gyro_deg < -88) gyro_deg = -90;
        if (gyro_deg > -182 && gyro_deg < -178) gyro_deg = -180;
        if (gyro_deg > -272 && gyro_deg < -268) gyro_deg = -270;
        if (gyro_deg < -358) gyro_deg = 0;
        
        //Convert direction from degrees to radians
        double gyro_rad = Math.toRadians(gyro_deg);
        
        //Calculate components of vector with the horizontal magnitude
        double x_comp_hor = hor * Math.cos(gyro_rad);
        double y_comp_hor = hor * Math.sin(gyro_rad);
        
        //Rotate original vector degrees to calculate components with the vertical magnitude
        gyro_rad = Math.toRadians(gyro_deg + 90);
        
        //Calculate vector components with the vertical magnitude. The trig functions 
        double x_comp_ver = ver * Math.sin(gyro_rad);
        double y_comp_ver = ver * Math.cos(gyro_rad);
        
        //Sum both X components and update horizontal magnitude
        hor = x_comp_hor + x_comp_ver;
        //Sum both Y components and update vertical magnitude
        ver = y_comp_hor + y_comp_ver;
        
        //Calculate forces for all wheels
        double force_fl = ver + rotate + hor;
        double force_rl = ver - rotate - hor;
        double force_fr = ver + rotate - hor;
        double force_rr = ver - rotate + hor;
        
        double[] forces = {force_fl, force_rl, force_fr, force_rr};
        
        double[] resulting_forces = ChiliFunctions.normalize(forces);
        
        return resulting_forces;
    }
    
    public double[] tankDrive(double left, double right) {
    	double[] power_set = {left, left, right, right};    	
    	return power_set;
    }
    
    public double[] arcadeDrive(double forward, double steer){
    	double forceL = forward + steer;
    	double forceR = forward - steer;
    	
    	double[] forces = {forceL, forceL, forceR, forceR};
    	
    	double[] resulting_forces = ChiliFunctions.normalize(forces);
    	
    	return resulting_forces;
    }
    
    public double[] patoDrive(Vector<Object> dataDriver, Vector<Object> dataSensor, double selector){    	 	
    	if(selector == 1){ //Arcade
    		double forward = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double steer = (double) dataDriver.elementAt(ChiliConstants.kLeftXAxis);    		
    		return arcadeDrive(forward, steer);
    	}
    	
    	else if(selector == 2){ //FPS
    		double forward = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double steer = (double) dataDriver.elementAt(ChiliConstants.kRightXAxis);    		
    		return arcadeDrive(forward, steer);  		
    	}
    	
    	else if(selector == 3){ //Tank
    		double left = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double right = (double) dataDriver.elementAt(ChiliConstants.kRightYAxis);    		
    		return tankDrive(left, right);
    	}
    	
    	else if(selector == 4){ //Mecanum Arcade
    		double ver = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double rotate = (double) dataDriver.elementAt(ChiliConstants.kLeftXAxis);
    		double hor = (((double) dataDriver.elementAt(ChiliConstants.kRightTrigger)) - (((double) dataDriver.elementAt(ChiliConstants.kLeftTrigger))));
    		double gyro = (double) dataSensor.elementAt(ChiliConstants.kGyroAngle);    		
    		return mecanumDrive(hor, ver, rotate, gyro);
    	}
    	
    	else if(selector == 5){ //Mecanum FPS
    		double ver = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double rotate = (double) dataDriver.elementAt(ChiliConstants.kRightXAxis);
    		double hor = (((double) dataDriver.elementAt(ChiliConstants.kRightTrigger)) - (((double) dataDriver.elementAt(ChiliConstants.kLeftTrigger))));    		
    		double gyro = (double) dataSensor.elementAt(ChiliConstants.kGyroAngle);    		
    		return mecanumDrive(hor, ver, rotate, gyro);
    	}
    	
    	else  if(selector == 6){ //Mecanum Tank
    		double left = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double right = (double) dataDriver.elementAt(ChiliConstants.kRightYAxis);
    		double hor = (((double) dataDriver.elementAt(ChiliConstants.kRightTrigger)) - (((double) dataDriver.elementAt(ChiliConstants.kLeftTrigger))));
    		double ver = (left + right) / 2;
    		double rotate = (left - right) / 2;    		
    		double gyro = (double) dataSensor.elementAt(ChiliConstants.kGyroAngle);    		
    		return mecanumDrive(hor, ver, rotate, gyro);
    	}
    	double[] temp = {0, 0, 0, 0};
    	return temp;
    	
    }

	public Vector<Object> update(Vector<Object> dataDriver, Vector<Object> dataSensor) {
		boolean time_again = (Timer.getFPGATimestamp() - time_marker) > TIME_BETWEEN_TOGGLES;
		if(((boolean) dataDriver.elementAt(ChiliConstants.kXboxDriveTrigger)) && time_again) {
			mode_selector = ChiliFunctions.overFlowToZero(++mode_selector, DRIVE_TYPES);
		}
		patoDrive(dataDriver, dataSensor, mode_selector);
		return this.dataDrive;
	}

	public void disable() {
		
	}
}
