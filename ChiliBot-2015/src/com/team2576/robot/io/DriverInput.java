package com.team2576.robot.io;

import java.util.Vector;

import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.Joystick;

/**
*
* @author Lucas
*/

public class DriverInput implements IOComponent {
	
	private final Joystick xbox_controller;
	
	private Vector<Object> driverOut;	
	private static DriverInput instance;

	public DriverInput() {
		driverOut = new Vector<Object>(20, 1);
		xbox_controller = new Joystick(ChiliConstants.xbox_joystick);
	}
	
	public static DriverInput getInstance() {
		if(instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}
	
	public double getXboxLeftX() {
		return this.xbox_controller.getRawAxis(ChiliConstants.kLeftXAxis);
	}
	
	public double getXboxLeftY() {
		return this.xbox_controller.getRawAxis(ChiliConstants.kLeftYAxis);
	}
	
	public double getXboxLeftTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.kLeftTriggers);
	}
	
	public double getXboxRightTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.kRightTriggers);
	}
	
	public double getXboxRightX() {
		return this.xbox_controller.getRawAxis(ChiliConstants.kRightXAxis);
	}
	
	public double getXboxRightY() {
		return this.xbox_controller.getRawAxis(ChiliConstants.kRightYAxis);
	}
	
	
	public boolean[] getXboxButtons(){
		boolean[] buttons = {this.xbox_controller.getRawButton(1), this.xbox_controller.getRawButton(2), 
							 this.xbox_controller.getRawButton(3), this.xbox_controller.getRawButton(4)};
		return buttons;
	}

	public Vector<Object> shareOut() {
		driverOut.clear();
		driverOut.add(ChiliConstants.kLeftXAxis, this.getXboxLeftX());		
		driverOut.add(ChiliConstants.kLeftYAxis, this.getXboxLeftY());
		driverOut.add(ChiliConstants.kLeftTriggers, this.getXboxLeftTrigger());
		driverOut.add(ChiliConstants.kRightTriggers, this.getXboxRightTrigger());
		driverOut.add(ChiliConstants.kRightXAxis, this.getXboxRightX());		
		driverOut.add(ChiliConstants.kRightYAxis, this.getXboxRightY());
		driverOut.add(ChiliConstants.kXboxButtons, this.getXboxButtons());
		System.out.println(driverOut.elementAt(ChiliConstants.kLeftYAxis));
		return driverOut;
	}

	public void shareIn(Vector<Object> data) {
		
	}
}