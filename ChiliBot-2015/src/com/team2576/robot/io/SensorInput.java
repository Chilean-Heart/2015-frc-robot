package com.team2576.robot.io;

/**
*
* @author Lucas
*/

public class SensorInput implements IOComponent {
	
	private static SensorInput instance;

	public static SensorInput getInstance() {
		if(instance == null) {
			instance = new SensorInput();
		}
		return instance;
	}

	public Object[] shareOut() {
		return null;		
	}

	public void shareIn(Object[] data) {
		
	}
}