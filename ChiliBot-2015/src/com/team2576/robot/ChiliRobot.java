package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.lib.Kapellmeister;
import com.team2576.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;

public class ChiliRobot extends IterativeRobot {
	
	Kapellmeister kapellmeister;
	DriveTrain meca_base;
	
	public ChiliRobot() {
		kapellmeister = Kapellmeister.getInstance();
		meca_base = DriveTrain.getInstance();
	}
	
    public void robotInit() {
    	kapellmeister.addTask(meca_base);
    }
    
    public void autonomousInit() {
    	
    }

    public void autonomousPeriodic() {

    }

    public void teleopInit() {
    	
    }
    
    public void teleopPeriodic() {
    	kapellmeister.conduct();         
    }
    
    public void disabledPeriodic() {
    	kapellmeister.silence();
    }
}