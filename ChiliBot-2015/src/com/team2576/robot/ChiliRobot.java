package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.auto.Maestro;
import com.team2576.auto.routines.*;
import com.team2576.lib.AutoRecorder;
import com.team2576.lib.Debugger;
import com.team2576.lib.Kapellmeister;
import com.team2576.lib.Logger;
import com.team2576.lib.VisionServer;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.subsystems.PatoDrive;
import com.team2576.robot.subsystems.Toter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ChiliRobot extends IterativeRobot {
	
	Kapellmeister kapellmeister;
	Maestro maestro;
	PatoDrive chassis;
	Toter stacker;
	VisionServer jetson;
	Debugger messenger;
	Logger loggy;
	AutoRecorder recorder;
	
	private boolean teleop_first_time, auto_finished;
	private double auto_timer;
	public static boolean table_init = false, vision_systems = true;
	
    public void robotInit() {    	
    	this.teleop_first_time = true;
    	this.auto_finished = false;
    	
    	kapellmeister = Kapellmeister.getInstance();
    	maestro = Maestro.getInstance();
		chassis = PatoDrive.getInstance();
		stacker = Toter.getInstance();
		jetson = VisionServer.getInstance();
		loggy = Logger.getInstance();
		recorder = AutoRecorder.getInstance();
		
		messenger = new Debugger(Debugger.Debugs.MESSENGER, ChiliConstants.kDefaultDebugState);
		
		maestro.addRoutine(new DriveForward());
		
    	kapellmeister.addTask(chassis, ChiliConstants.iDriveTrain);
    	//kapellmeister.addTask(stacker, ChiliConstants.iStacker);
    	
    	ChiliRobot.vision_systems = jetson.initializeTable();
    }
    
    public void autonomousInit() {
    	maestro.setRoutine();
    	this.auto_timer = Timer.getFPGATimestamp();
    }

    public void autonomousPeriodic() {
    	while(!auto_finished && (Timer.getFPGATimestamp() - auto_timer) < ChiliConstants.kAutoTime){
    		this.auto_finished = maestro.conduct();
    	}
    }

    public void teleopInit() {    	
    	messenger.println("Finished teleopInit in", Timer.getFPGATimestamp());
    	loggy.openLog();
    	if(AutoRecorder.record_enabled) recorder.openRecording();
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
    	}
    }
    
    public void disableInit() {    	
    	this.teleop_first_time = true;
    	loggy.closeLog();
    	kapellmeister.silence();
    	if(AutoRecorder.record_enabled) recorder.closeRecording();
    }
    
    public void disabledPeriodic() {
    	
    }
}