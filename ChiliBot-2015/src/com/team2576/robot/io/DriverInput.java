package com.team2576.robot.io;

/**
*
* @author Lucas
*/

public class DriverInput implements IOComponent {
	
	private static DriverInput instance;

	public static DriverInput getInstance() {
		if(instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}

	public Object[] shareOut() {
		return null;		
	}

	public void shareIn(Object[] data) {
		
	}
}