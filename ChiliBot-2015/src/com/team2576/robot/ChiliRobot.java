package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.lib.Kapellmeister;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.subsystems.PatoDrive;
import com.team2576.robot.subsystems.Toter;

import edu.wpi.first.wpilibj.IterativeRobot;


public class ChiliRobot extends IterativeRobot {
	
	Kapellmeister kapellmeister;
	PatoDrive meca_base;
	Toter stacker;
	
	public ChiliRobot() {
		
	}
	
    public void robotInit() {
    	
    	kapellmeister = Kapellmeister.getInstance();
		meca_base = PatoDrive.getInstance();
		stacker = Toter.getInstance();
    	kapellmeister.addTask(meca_base, ChiliConstants.iDriveTrain);
    }
    
    public void autonomousInit() {
    	
    }

    public void autonomousPeriodic() {

    }

    public void teleopInit() {
    	System.out.println("Finished teleop init");
    }
    
    public void teleopPeriodic() {
    	System.out.println("Made it to the loop");
    	
    	while(isOperatorControl() && isEnabled()) {
    		kapellmeister.conduct();
    	}
    }
    
    public void disableInit() {
    	kapellmeister.silence();
    }
    
    public void disabledPeriodic() {
    	
    }
}