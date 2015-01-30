package com.team2576.robot.io;

import java.util.Vector;

import com.team2576.lib.util.ChiliFunctions;
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
		driverOut = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		xbox_controller = new Joystick(ChiliConstants.iXboxJoystick);
	}
	
	public static DriverInput getInstance() {
		if(instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}
	
	private double getXboxLeftX() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iLeftXAxis), ChiliConstants.kAxisThreshold);
	}
	
	private double getXboxLeftY() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iLeftYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	private double getXboxLeftTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.iLeftTrigger);
	}
	
	private double getXboxRightTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.iRightTrigger);
	}
	
	private double getXboxRightX() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iRightXAxis), ChiliConstants.kAxisThreshold);
	}
	
	private double getXboxRightY() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iRightYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	
	private boolean[] getXboxButtons(){
		boolean[] buttons = {this.xbox_controller.getRawButton(1), this.xbox_controller.getRawButton(2), 
							 this.xbox_controller.getRawButton(3), this.xbox_controller.getRawButton(4)};
		return buttons;
	}
	
	private boolean getXboxDriveTrigger() {
		return this.xbox_controller.getRawButton(5);
	}

	public Vector<Object> shareOut() {
		driverOut.clear();
		driverOut.add(ChiliConstants.iLeftXAxis, this.getXboxLeftX());		
		driverOut.add(ChiliConstants.iLeftYAxis, this.getXboxLeftY());
		driverOut.add(ChiliConstants.iLeftTrigger, this.getXboxLeftTrigger());
		driverOut.add(ChiliConstants.iRightTrigger, this.getXboxRightTrigger());
		driverOut.add(ChiliConstants.iRightXAxis, this.getXboxRightX());		
		driverOut.add(ChiliConstants.iRightYAxis, this.getXboxRightY());
		driverOut.add(ChiliConstants.iXboxButtons, this.getXboxButtons());
		driverOut.add(ChiliConstants.iXboxDriveTrigger, this.getXboxDriveTrigger());
		return driverOut;
	}

	public void shareIn(Vector<Object> data) {
		
	}
}