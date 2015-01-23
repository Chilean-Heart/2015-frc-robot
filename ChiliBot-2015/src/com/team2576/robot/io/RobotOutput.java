package com.team2576.robot.io;

/**
*
* @author Lucas
*/

public class RobotOutput implements IOComponent {
	
	private static RobotOutput instance;

	public static RobotOutput getInstance() {
		if(instance == null) {
			instance = new RobotOutput();
		}
		return instance;
	}

	public Object[] shareOut() {
		return null;
	}

	public void shareIn(Object[] data) {
		
	}
}