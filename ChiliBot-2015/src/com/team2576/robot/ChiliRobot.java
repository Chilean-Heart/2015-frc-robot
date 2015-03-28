package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.auto.EmergencyAuto;
import com.team2576.lib.AutoRecorder;
import com.team2576.lib.Debugger;
import com.team2576.lib.Kapellmeister;
import com.team2576.lib.Logger;
import com.team2576.lib.VisionServer;
import com.team2576.lib.VisionServer.GameMode;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.io.DriverInput;
import com.team2576.robot.io.RobotOutput;
import com.team2576.robot.io.SensorInput;
import com.team2576.robot.subsystems.PatoDrive;
import com.team2576.robot.subsystems.Toter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ChiliRobot extends IterativeRobot {
	
	Kapellmeister kapellmeister;
	//Maestro maestro;
	PatoDrive chassis;
	Toter stacker;
	VisionServer jetson;
	Debugger messenger;
	Logger loggy;
	AutoRecorder recorder;
	SensorInput sensor;
	DriverInput driver;
	RobotOutput output;
	EmergencyAuto auto;
	PatoDrive controller;
	
	private boolean teleop_first_time;
	private boolean auto_finished = false;
	private double auto_timer;
	public static boolean table_init = false, vision_systems = true;
	
    public void robotInit() {    	
    	this.teleop_first_time = true;
    	this.auto_finished = false;
    	
    	kapellmeister = Kapellmeister.getInstance();
    	//maestro = Maestro.getInstance();
		chassis = PatoDrive.getInstance();
		stacker = Toter.getInstance();
		jetson = VisionServer.getInstance();
		loggy = Logger.getInstance();
		recorder = AutoRecorder.getInstance();
		sensor = SensorInput.getInstance();
		driver = DriverInput.getInstance();
		output = RobotOutput.getInstance();
		auto = EmergencyAuto.getInstance();
		controller = PatoDrive.getInstance();
		
		messenger = new Debugger(Debugger.Debugs.MESSENGER, ChiliConstants.kDefaultDebugState);
		
		//maestro.addRoutine(new DriveForward());
		
    	kapellmeister.addTask(chassis, ChiliConstants.iDriveTrain);
    	kapellmeister.addTask(stacker, ChiliConstants.iStacker);
    	
    	ChiliRobot.vision_systems = jetson.initializeTable();
    }
    
    public void autonomousInit() {
    	//maestro.setRoutine();
    	
    	this.auto_timer = Timer.getFPGATimestamp();
    	jetson.setMode(GameMode.AUTO);
    }

    public void autonomousPeriodic() {
        /*while(!auto_finished && (Timer.getFPGATimestamp() - auto_timer) < ChiliConstants.kAutoTime){
    		this.auto_finished = auto.runAuto();
    	}*/
    	if(!auto_finished){
	    	//
    		while((Timer.getFPGATimestamp() - auto_timer) < 0.4){
	    		output.setAllDrives(0.8);
	    	}
	    	output.setAllDrives(0);
	    	
	    	auto_timer = Timer.getFPGATimestamp();
	    	//
	    	//
	    	/*while((Timer.getFPGATimestamp() - auto_timer) < 3.0){
	    		output.setLifters(0.7, 0.8);  //One must be higher
	    		if((Timer.getFPGATimestamp() - auto_timer) > 2.5){
	    			//output.setDrive(0.5, 0.5, -0.5, -0.5);
	    			output.setDriveFromArray(controller.mecanumDriveWpi(1, 0, 0, 0, sensor.getGyroAngle(), sensor.getAccelX(), sensor.getAccelY()));
	    		} 
	    	}
	    	
	    	auto_timer = Timer.getFPGATimestamp();
	    	output.setLifters(0, 0);
	    	output.setAllDrives(0);
	    	*/
	    	//
	    	auto_finished = true;
	    }
    	
    }

    public void teleopInit() {
    	messenger.println("Finished teleopInit in", Timer.getFPGATimestamp());
    	loggy.openLog();
    	if(AutoRecorder.record_enabled) recorder.openRecording();
    	jetson.setMode(GameMode.TELE);
    }
    
    public void teleopPeriodic() {    	
    	if(this.teleop_first_time) {
    		messenger.println("Made it to the loop in", Timer.getFPGATimestamp());    		
    		this.teleop_first_time = false;
    	}
    	
    	while(isOperatorControl() && isEnabled()) {
    		if(AutoRecorder.record_enabled) recorder.recordAuto();
    		loggy.addLog();
    		kapellmeister.conduct();
    		//SmartDashboard.putNumber("X val", jetson.getX());
    		//SmartDashboard.putNumber("Y val", jetson.getY());
    		SmartDashboard.putNumber("Gyro Angle", kapellmeister.sensorData.getGyroAngle());
    		SmartDashboard.putNumber("Selector", PatoDrive.selector);
    		SmartDashboard.putNumber("ENC Left", sensor.getLeftEncoderRaw());
        	SmartDashboard.putNumber("ENC Right", sensor.getRightEncoderRaw());
    	}
    }
    
    public void disableInit() {    	
    	this.teleop_first_time = true;
    	this.auto_finished = false;
    	//maestro.reset();
    	loggy.closeLog();
    	kapellmeister.silence();
    	if(AutoRecorder.record_enabled) recorder.closeRecording();
    	sensor.resetLeftEncoder();
    	sensor.resetRightEncoder();
    }
    
    public void disabledPeriodic() {
    	SmartDashboard.putNumber("ENC Left", sensor.getLeftEncoderRaw());
    	SmartDashboard.putNumber("ENC Right", sensor.getRightEncoderRaw());
    	SmartDashboard.putBoolean("Limit Left", sensor.getLeftLimit());
    	SmartDashboard.putBoolean("Limit Right", sensor.getRightLimit());
    	SmartDashboard.putNumber("Gyro disabled", sensor.getGyroAngle());
    	if(driver.getXboxButtonY()){
    		sensor.gyroInit();
    	}
    }
}