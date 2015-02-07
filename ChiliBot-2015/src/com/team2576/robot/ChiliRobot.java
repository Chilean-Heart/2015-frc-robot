package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.lib.Debugger;
import com.team2576.lib.Kapellmeister;
import com.team2576.lib.Logger;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.subsystems.PatoDrive;
import com.team2576.robot.subsystems.Toter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;


public class ChiliRobot extends IterativeRobot {
	
	Kapellmeister kapellmeister;
	PatoDrive chassis;
	Toter stacker;
	Debugger messenger;
	Logger loggy;
	
	private boolean teleop_first_time;
	
	public ChiliRobot() {
		
	}
	
    public void robotInit() {
    	
    	this.teleop_first_time = true;
    	
    	kapellmeister = Kapellmeister.getInstance();
		chassis = PatoDrive.getInstance();
		stacker = Toter.getInstance();
		loggy = Logger.getInstance();
		
		messenger = new Debugger(Debugger.Debugs.MESSENGER, ChiliConstants.kDefaultDebugState);
		
    	kapellmeister.addTask(chassis, ChiliConstants.iDriveTrain);
    	kapellmeister.addTask(stacker, ChiliConstants.iStacker);
    }
    
    public void autonomousInit() {
    	
    }

    public void autonomousPeriodic() {
    	
    }

    public void teleopInit() {
    	
    	messenger.println("Finished teleopInit in", Timer.getFPGATimestamp());
    	loggy.openLog();
    }
    
    public void teleopPeriodic() {
    	
    	if(this.teleop_first_time) {
    		messenger.println("Made it to the loop in", Timer.getFPGATimestamp());
    		this.teleop_first_time = false;
    	}
    	
    	while(isOperatorControl() && isEnabled()) {
    		loggy.addLog();
    		kapellmeister.conduct();
    	}
    }
    
    public void disableInit() {
    	
    	this.teleop_first_time = true;
    	loggy.closeLog();
    	kapellmeister.silence();
    }
    
    public void disabledPeriodic() {
    	
    }
}