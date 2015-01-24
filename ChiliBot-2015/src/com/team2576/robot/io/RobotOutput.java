package com.team2576.robot.io;

import java.util.Vector;

import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;

/**
*
* @author Lucas
*/

public class RobotOutput implements IOComponent {
	
	private static RobotOutput instance;
	private Vector<Object> data;
	
	private final Talon front_left, rear_left, front_right, rear_right;
	private final Jaguar winch;
	
	public RobotOutput() {
		front_left = new Talon(ChiliConstants.front_left_motor);
		rear_left = new Talon(ChiliConstants.rear_left_motor);
		front_right = new Talon(ChiliConstants.front_right_motor);
		rear_right = new Talon(ChiliConstants.rear_right_motor);
		winch = new Jaguar(ChiliConstants.winch_motor);
	}
	
	public static RobotOutput getInstance() {
		if(instance == null) {
			instance = new RobotOutput();
		}
		return instance;
	}

	public Vector<Object> shareOut() {
		return null;
	}

	public void shareIn(Vector<Object> dataOut) {
		this.data = dataOut;
	}
}