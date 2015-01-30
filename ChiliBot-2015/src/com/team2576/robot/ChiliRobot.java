package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.lib.Kapellmeister;
import com.team2576.robot.subsystems.PatoDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Timer;

public class ChiliRobot extends IterativeRobot {
	
	Kapellmeister kapellmeister;
	PatoDrive meca_base;
	
	public ChiliRobot() {
		
	}
	
    public void robotInit() {
    	
    	kapellmeister = Kapellmeister.getInstance();
		meca_base = PatoDrive.getInstance();
		
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