package com.team2576.robot.subsystems;

/**
*
* @author Lucas
*/

import com.team2576.lib.Debugger;
import com.team2576.lib.VisionServer;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;
import com.team2576.lib.util.ChiliPID;
import com.team2576.robot.io.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChiliDrive implements SubComponent {
	
	public static int selector;
	private boolean selector_error, vision_override;
	private boolean first_strafe;
	private double first_gyro_state, gyroCorrect, accelYCorrect;
    private double drive_toggle_marker;
    private double[] forces, disable_forces;
    
    private ChiliPID vision_strafer;
    
    private static ChiliDrive instance;
    private RobotOutput output;
    private Debugger debug;
    private VisionServer server;
    
    public static ChiliDrive getInstance() {
    	if(instance == null) {
    		instance = new ChiliDrive();
    	}
    	return instance;
    } 
    
    private ChiliDrive() {
    	
        this.drive_toggle_marker = Timer.getFPGATimestamp();
        this.selector_error = false;
        this.vision_override = false;
        this.first_strafe = false;
        ChiliDrive.selector = 5;
        
        this.forces = new double[ChiliConstants.kMotorCount];
        this.disable_forces = new double[ChiliConstants.kMotorCount];
        this.forces = ChiliFunctions.fillArrayWithZeros(this.forces);
        this.disable_forces = ChiliFunctions.fillArrayWithZeros(this.disable_forces);
        this.vision_strafer = new ChiliPID(0.01, 0, 0, 5);
        this.vision_strafer.setReference(ChiliConstants.kFrameWidthCenter);
        
        output = RobotOutput.getInstance();
        server = VisionServer.getInstance();
        debug = new Debugger(Debugger.Debugs.MESSENGER, false);
    }
    
    private double[] mecanumDriveWpi(double x, double y, double rotation, double gyroAngle) {
    	double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compenstate for gyro angle.
        double rotated[] = ChiliFunctions.rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];

        double wheelSpeeds[] = new double[4];
        wheelSpeeds[0] = xIn + yIn + rotation;
        wheelSpeeds[2] = -xIn + yIn - rotation;
        wheelSpeeds[1] = -xIn + yIn + rotation;
        wheelSpeeds[3] = xIn + yIn - rotation;

        ChiliFunctions.normalize(wheelSpeeds);
        
        return wheelSpeeds;

    }
    
    public double[] mecanumDriveWpi(double x, double y, double rotation, double gyroAngle, 
    		double gyroDrift, double accelXDrift, double accelYDrift) {
    	
    	double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        
    	if(Math.abs(x) > ChiliConstants.kStrafeThreshold && !first_strafe) {
    		first_strafe = true;
    		first_gyro_state = gyroDrift;
    	} else if (Math.abs(x) > ChiliConstants.kStrafeThreshold && first_strafe) {
    		gyroCorrect = (Math.abs(gyroDrift - first_gyro_state)) * ChiliConstants.kPatoGyroP * (gyroDrift < 0 ? -1 : 1);
    		accelYCorrect = (accelYDrift < 0 ? 1 : -1) * accelYDrift * ChiliConstants.kPatoAccelYP;
    		y += accelYCorrect;
    		rotation += gyroCorrect;
    	} else if (Math.abs(x) < ChiliConstants.kStrafeThreshold) {
    		first_strafe = false;
    	}   	
        
        System.out.println("X: " + xIn + ", Y: " + yIn);

        double wheelSpeeds[] = new double[4];
        wheelSpeeds[0] = (xIn + yIn + rotation);
        wheelSpeeds[2] = -xIn + yIn - rotation;
        wheelSpeeds[1] = -xIn + yIn + rotation;
        wheelSpeeds[3] = xIn + yIn - rotation;

        ChiliFunctions.normalize(wheelSpeeds);
        
        return wheelSpeeds;
    }
    
	private double[] mecanumDrive(double hor, double ver, double rotate, double gyro) {
        
        //Initialize gyro_deg variable
        //double gyro_deg = gyro % 360 + 180;
        double gyro_deg = gyro;
        
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
        hor = x_comp_hor - x_comp_ver;
        //Sum both Y components and update vertical magnitude
        ver = y_comp_hor + y_comp_ver;
        
        //Calculate forces for all wheels
        
        double force_fl = ver + hor + rotate;
        double force_rl = ver - hor + rotate;
        double force_fr = ver - hor - rotate;
        double force_rr = ver + hor - rotate;
        
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
		
		debug.println("Toggle:", drive_toggle_marker);

		//Trigger change in drive mode
		/*if(driver.getXboxButtonLeftTrigger() && ( (Timer.getFPGATimestamp() - this.drive_toggle_marker) > ChiliConstants.kTimeBetweenToggle) ) {
			debug.println("Got in the changer");
			PatoDrive.selector++;
			if(PatoDrive.selector > (ChiliConstants.kDriveTypes)) {
				PatoDrive.selector = 0;
			}
			this.drive_toggle_marker = Timer.getFPGATimestamp();
		}*/
		
		if(driver.getXboxButtonX()) {
			sensor.gyroReset();
		}		
		
		this.selector_error = false;
		this.vision_override = driver.getXboxButtonRightTrigger() && false;
		
		if(!vision_override){
			switch(ChiliDrive.selector) {
			//Arcade
			case 0:
				{
					double forward = driver.getXboxLeftY();
					double steer = driver.getXboxLeftX();
					/*double forward = driver.getMadCatzY();
					double steer = driver.getMadCatzX();*/
					this.forces = this.arcadeDrive(steer, forward);
					break;
				}
			//FPS
			case 1:
				{
					double forward = driver.getXboxLeftY();
					double steer = driver.getXboxRightX();
					this.forces = this.arcadeDrive(steer, forward);
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
		    		double hor = (driver.getXboxLeftTrigger() - driver.getXboxRightTrigger()) * -1;
		    		double gyro = sensor.getGyroAngle();   		
		    		SmartDashboard.putNumber("Ver", ver);
		    		SmartDashboard.putNumber("Hor", hor);
		    		SmartDashboard.putNumber("Rotate", rotate);
		    		SmartDashboard.putNumber("Gyro", gyro);
		    		this.forces = this.mecanumDrive(rotate, ver, hor, gyro);				
					break;
				}
			//Mecanum FPS
			case 4:
				{
					double ver = driver.getXboxLeftY();
		    		double rotate = driver.getXboxRightX();
		    		double hor = (driver.getXboxLeftTrigger() - driver.getXboxRightTrigger()) * -1;
		    		double gyro = sensor.getGyroAngle();   	
		    		SmartDashboard.putNumber("Ver", ver);
		    		SmartDashboard.putNumber("Hor", hor);
		    		SmartDashboard.putNumber("Rotate", rotate);
		    		SmartDashboard.putNumber("Gyro", gyro);
		    		this.forces = this.mecanumDrive(hor, ver, rotate, gyro);
					break;
				}
			//Mecanum Tank
			case 5:
				{
					double hor = (driver.getXboxLeftTrigger() - driver.getXboxRightTrigger()) * -1;
		        	double rotate = (( (driver.getXboxRightY() * -1) + (driver.getXboxLeftY() * 1)) / 2);
		        	double ver = (( (driver.getXboxRightY() * -1) - (driver.getXboxLeftY() * 1)) / 2);
		        	
		        	if(driver.getXboxButtonLeftTrigger()) {
		        		if(hor > 0) {
		        			hor = hor * hor;
		        		} else {
		        			hor = hor * hor * -1;
		        		}
		        		if(ver > 0) {
		        			ver = ver * ver;
		        		} else {
		        			ver = ver * ver * -1;
		        		}
		        		if(rotate > 0) {
		        			rotate = rotate * rotate;
		        		} else {
		        			rotate = rotate * rotate * -1;
		        		}
		        	}
		        	
		    		/*double hor = driver.getXboxRightTrigger() - driver.getXboxLeftTrigger();
		    		double rotate = (driver.getXboxLeftY() + driver.getXboxRightY()) / 2;
		    		double ver = (driver.getXboxLeftY() - driver.getXboxRightY()) / 2;*/	
		    		
		        	double gyro = sensor.getGyroAngle();
		    		System.out.print("Ver: " + ver);
		    		System.out.print(", Hor: " + hor);
		    		System.out.print(", Rotate: " + rotate);
		    		System.out.println(", Gyro: " + gyro);
		    		
		    		this.forces =  this.mecanumDriveWpi(hor, ver*0.5, rotate, 0, sensor.getGyroAngle(), sensor.getAccelX(), sensor.getAccelY());
		    		break;
				}
				
			case 6:
				{
					double hor = driver.getMadCatzX();
					double rotate = driver.getMadCatzZ();
					double ver = driver.getMadCatzY();
					double gyro = sensor.getGyroAngle();
					this.forces = this.mecanumDrive(hor, ver, rotate, gyro);
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
		} else {
			double error = ChiliConstants.kFrameWidthCenter - server.getX();
			SmartDashboard.putNumber("Error: ", error);
			//double correction = vision_strafer.calcPID(error);
			double correction = error * 0.01;
			SmartDashboard.putNumber("Correction: ", correction);
			this.forces = this.mecanumDriveWpi(correction, 0, 0, 0);
			for (int i = 0; i < this.forces.length; i++) {
				System.out.println(this.forces[i]);
			}
		}
		
		System.out.print("FL Force: " + forces[0]);
		System.out.print(" ,RL Force: " + forces[1]);
		System.out.print(" ,FR Force: " + forces[2]);
		System.out.println(" ,RR Force: " + forces[3]);
		
		debug.println("Mode:", selector);
		
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
