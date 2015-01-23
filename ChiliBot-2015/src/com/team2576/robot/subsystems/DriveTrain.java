package com.team2576.robot.subsystems;

import com.team2576.lib.util.ChiliFunctions;
import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;

/**
*
* @author Lucas
*/

public class DriveTrain implements SubComponent {
	
	private final Jaguar front_left, rear_left, front_right, rear_right;
    private final Gyro gyro;
    private boolean use_gyro;
    private final double GAIN = 1;
    private static DriveTrain instance;
    
    public static DriveTrain getInstance() {
    	if(instance == null) {
    		instance = new DriveTrain(ChiliConstants.front_left_motor, ChiliConstants.rear_left_motor, ChiliConstants.front_right_motor, ChiliConstants.rear_right_motor);
    	}
    	return instance;
    }
    
    public DriveTrain(int fl, int rl, int fr, int rr){
        this.front_left = new Jaguar(fl);
        this.rear_left = new Jaguar(rl);
        this.front_right = new Jaguar(fr);
        this.rear_right = new Jaguar(rr);
        use_gyro = false;
        gyro = null;
    }
    
    public DriveTrain(int fl, int rl, int fr, int rr, int g){
        this.front_left = new Jaguar(fl);
        this.rear_left = new Jaguar(rl);
        this.front_right = new Jaguar(fr);
        this.rear_right = new Jaguar(rr);
        use_gyro = true;
        gyro = new Gyro(g);
    }
    
    private void mecanumDrive(double hor, double ver, double rotate){
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
            gyro_deg = gyro.getAngle() % 360 + 180;
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
        
        front_left.set(GAIN * resulting_forces[0]);
        rear_left.set(GAIN * resulting_forces[1]);
        front_right.set(GAIN * resulting_forces[2]);
        rear_right.set(GAIN * resulting_forces[3]);
    }

	public Object[] update(Object[] dataDriver, Object[] dataSensor) {
		return null;
	}

	public void disable() {
		
	}
}
