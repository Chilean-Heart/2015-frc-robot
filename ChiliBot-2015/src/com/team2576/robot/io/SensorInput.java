package com.team2576.robot.io;

import java.util.Vector;

import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.Gyro;

/**
*
* @author Lucas
*/

public class SensorInput implements IOComponent {
	
	private final Gyro gyro;
	
	private static SensorInput instance;
	
	public SensorInput() {
		gyro = new Gyro(ChiliConstants.gyro_channel);
	}

	public static SensorInput getInstance() {
		if(instance == null) {
			instance = new SensorInput();
		}
		return instance;
	}

	public Vector<Object> shareOut() {
		return null;		
	}

	public void shareIn(Vector<Object> data) {
		
	}
}