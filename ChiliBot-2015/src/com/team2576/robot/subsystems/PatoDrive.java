package com.team2576.robot.subsystems;

import java.util.Vector;

import com.team2576.lib.util.ChiliFunctions;
import com.team2576.robot.ChiliConstants;


/**
*
* @author Pato
*/

public class PatoDrive implements SubComponent {
	
	/*private final Jaguar front_left, rear_left, front_right, rear_right;
    private final Gyro gyro;*/
    private boolean use_gyro;
    private final double GAIN = 1;
    private static PatoDrive instance;
    private Vector<Object> dataDrive;
    
    public static PatoDrive getInstance() {
    	if(instance == null) {
    		instance = new PatoDrive();
    	}
    	return instance;
    }
    
    public PatoDrive(){
    	dataDrive = new Vector<Object>(20, 1);
        this.use_gyro = ChiliConstants.use_gyro;
    }
    
    
    @SuppressWarnings("unused")
	private double[] mecanumDrive(double hor, double ver, double rotate){
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
            //gyro_deg = gyro.getAngle() % 360 + 180;
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
        
        /*front_left.set(GAIN * resulting_forces[0]);
        rear_left.set(GAIN * resulting_forces[1]);
        front_right.set(GAIN * resulting_forces[2]);
        rear_right.set(GAIN * resulting_forces[3]);*/
        
        return resulting_forces;
    }
    
    public double[] tankDrive(double left, double right) {
    	double[] power_set = {left, right};    	
    	return power_set;
    }
    
    public double[] arcadeDrive(double forward, double steer){
    	double forceL = forward + steer;
    	double forceR = forward - steer;
    	
    	double[] forces = {forceL,forceR};
    	
    	double[] resulting_forces = ChiliFunctions.normalize(forces);
    	
    	return resulting_forces;
    }
    
    public double[] patoDrive(Vector<Object> dataDriver, Vector<Object> dataSensor,double Selector){
    	
    	if(Selector == 1){ //Arcade
    		double forward = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double steer = (double) dataDriver.elementAt(ChiliConstants.kLeftXAxis);
    		
    		return arcadeDrive(forward, steer);
    	}
    	else if(Selector == 2){ //FPS
    		double forward = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double steer = (double) dataDriver.elementAt(ChiliConstants.kRightXAxis);
    		
    		return arcadeDrive(forward, steer);  		
    	}
    	else if(Selector == 3){ //Tank
    		double left = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double right = (double) dataDriver.elementAt(ChiliConstants.kRightYAxis);
    		
    		return tankDrive(left,right);
    	}
    	else if(Selector == 4){ //Mecanum Arcade
    		double ver = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double rotate = (double) dataDriver.elementAt(ChiliConstants.kLeftXAxis);
    		double hor = (double) dataDriver.elementAt(ChiliConstants.kRLTriggers);
    		
    		return mecanumDrive(hor, ver, rotate);
    	}
    	else if(Selector == 5){ //Mecanum FPS
    		double ver = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double rotate = (double) dataDriver.elementAt(ChiliConstants.kRightXAxis);
    		double hor = (double) dataDriver.elementAt(ChiliConstants.kRLTriggers);
    		
    		return mecanumDrive(hor, ver, rotate);
    	}
    	else { //Mecanum Tank
    		double left = (double) dataDriver.elementAt(ChiliConstants.kLeftYAxis);
    		double right = (double) dataDriver.elementAt(ChiliConstants.kRightYAxis);
    		double hor = (double) dataDriver.elementAt(ChiliConstants.kRLTriggers);
    		
    		double ver = (left+right)/2;
    		double rotate = (left-right)/2;
    		
    		return mecanumDrive(hor, ver, rotate);
    	}
    	
    }

	public Vector<Object> update(Vector<Object> dataDriver, Vector<Object> dataSensor) {
		//dataDrive.add(ChiliConstants.kTankValues, this.tankDrive(((double) dataDriver.elementAt(ChiliConstants.kLeftYAxis)), ((double) dataDriver.elementAt(ChiliConstants.kRightYAxis))));
		dataDrive.add(ChiliConstants.kTankLeftVal, dataDriver.elementAt(ChiliConstants.kLeftYAxis));
		dataDrive.add(ChiliConstants.kTankRightVal, dataDriver.elementAt(ChiliConstants.kRightYAxis));
		System.out.println("this: " + dataDrive.elementAt(ChiliConstants.kTankLeftVal) + " and this: " + dataDrive.elementAt(ChiliConstants.kTankRightVal));
		return this.dataDrive;
	}

	public void disable() {
		
	}
}
