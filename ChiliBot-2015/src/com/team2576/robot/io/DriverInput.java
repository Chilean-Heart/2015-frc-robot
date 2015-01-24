package com.team2576.robot.io;

import java.util.Vector;

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

	public Vector<Object> shareOut() {
		return null;		
	}

	public void shareIn(Vector<Object> data) {
		
	}
}