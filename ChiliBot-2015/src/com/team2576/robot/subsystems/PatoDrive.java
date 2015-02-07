package com.team2576.robot.subsystems;

/**
*
* @author PatoLucash
*/

import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;
import com.team2576.robot.io.*;

import edu.wpi.first.wpilibj.Timer;



public class PatoDrive implements SubComponent {
	
	private int selector;
	private boolean selector_error;
    private double drive_toggle_marker;
    private double[] forces, disable_forces;
    
    private static PatoDrive instance;
    private RobotOutput output;
    
    public static PatoDrive getInstance() {
    	if(instance == null) {
    		instance = new PatoDrive();
    	}
    	return instance;
    }
    
    private PatoDrive() {
    	
        this.drive_toggle_marker = Timer.getFPGATimestamp();
        this.selector_error = false;
        
        this.forces = new double[ChiliConstants.kMotorCount];
        this.disable_forces = new double[ChiliConstants.kMotorCount];
        this.forces = ChiliFunctions.fillArrayWithZeros(this.forces);
        this.disable_forces = ChiliFunctions.fillArrayWithZeros(this.disable_forces);
        
        output = RobotOutput.getInstance();
    }
    
	private double[] mecanumDrive(double hor, double ver, double rotate, double gyro) {
        //Rotation deadband
        if(Math.abs(rotate) < 0.1) {
            rotate = 0;
        }
        
        //Translation deadband
        if(Math.abs(hor*hor + ver*ver) < 0.1) {
            hor = 0;
            ver = 0;
        }
        
        //Initialize gyro_deg variable
        double gyro_deg = gyro % 360 + 180;
        
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
    	return new double[]{left, left, right, right};  
    }
    
    public double[] arcadeDrive(double forward, double steer) {
    	double forceL = forward + steer;
    	double forceR = forward - steer;
    	
    	double[] forces = {forceL, forceL, forceR, forceR};
    	
    	double[] resulting_forces = ChiliFunctions.normalize(forces);    	
    	
    	return resulting_forces;
    }

	public boolean update(DriverInput driver, SensorInput sensor) {
		
		//Trigger change in drive mode
		if(driver.getXboxButtonLeftTrigger() && ( (Timer.getFPGATimestamp() - this.drive_toggle_marker) > ChiliConstants.kTimeBetweenToggle) ) {
			this.selector = (int) ChiliFunctions.overFlowToZero(selector, (ChiliConstants.kDriveTypes - 1));
			this.drive_toggle_marker = Timer.getFPGATimestamp();
		}
		
		this.selector_error = false;
		
		switch(this.selector) {
		//Arcade
		case 0:
			{
				double forward = driver.getXboxLeftY();
				double steer = driver.getXboxLeftX();
				this.forces = this.arcadeDrive(forward, steer);
				break;
			}
		//FPS
		case 1:
			{
				double forward = driver.getXboxLeftY();
				double steer = driver.getXboxRightX();
				this.forces = this.arcadeDrive(forward, steer);
				break;
			}
		//Tank
		case 2:
			{
				double left = driver.getXboxLeftY();
				double right = driver.getXboxRightY();
				this.forces = this.tankDrive(left, right);
				break;
			}
		//Mecanum Arcade
		case 3:
			{
				double ver = driver.getXboxLeftY();
	    		double rotate = driver.getXboxLeftX();
	    		double hor = driver.getXboxRightTrigger() - driver.getXboxLeftTrigger();
	    		double gyro = sensor.getGyroAngle();   		
	    		this.forces = this.mecanumDrive(hor, ver, rotate, gyro);				
				break;
			}
		//Mecanum FPS
		case 4:
			{
				double ver = driver.getXboxLeftY();
	    		double rotate = driver.getXboxRightX();
	    		double hor = driver.getXboxRightTrigger() - driver.getXboxLeftTrigger();
	    		double gyro = sensor.getGyroAngle();   		
	    		this.forces = this.mecanumDrive(hor, ver, rotate, gyro);
				break;
			}
		//Mecanum Tank
		case 5:
			{
	    		double hor = driver.getXboxRightTrigger() - driver.getXboxLeftTrigger();
	    		double ver = (driver.getXboxLeftY() + driver.getXboxRightY()) / 2;
	    		double rotate = (driver.getXboxLeftY() - driver.getXboxRightY()) / 2;    		
	    		double gyro = sensor.getGyroAngle();   		
	    		this.forces =  this.mecanumDrive(hor, ver, rotate, gyro);
	    		break;
			}
		//Drive Error
		default:
			{
				this.forces = ChiliFunctions.fillArrayWithZeros(this.forces);
				this.selector_error = true;
				break;
			}
		}
		
		if(ChiliFunctions.isArrayWithZeros(this.forces) && this.selector_error){
			return false;
		}
		
		this.output.setDriveFromArray(this.forces);
		
		return true;
	}
	
	public void disable() {
		this.output.setDriveFromArray(this.disable_forces);
	}

}
