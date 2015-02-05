package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;

import edu.wpi.first.wpilibj.Joystick;

/**
*
* @author Arturito
*/

public class DriverInput {
	
	private static DriverInput instance;
	
	private final Joystick xbox_controller;
	private final Joystick joystick;
	
	public static DriverInput getInstance() {
		if(instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}

	private DriverInput() {
		xbox_controller = new Joystick(ChiliConstants.iXboxJoystick);
		joystick = new Joystick(ChiliConstants.iJoytstick);
	}
	
	public double getXboxLeftX() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iLeftXAxis), ChiliConstants.kAxisThreshold);
	}
	
	public double getXboxLeftY() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iLeftYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	public double getXboxLeftTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.iLeftTrigger);
	}
	
	public double getXboxRightTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.iRightTrigger);
	}
	
	public double getXboxRightX() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iRightXAxis), ChiliConstants.kAxisThreshold);
	}
	
	public double getXboxRightY() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iRightYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}	
	
	public boolean getXboxButton(int button_code){
		return this.xbox_controller.getRawButton(button_code);
	}
	
	public boolean getXboxButtonA() {
		return this.xbox_controller.getRawButton(1);
	}
	
	public boolean getXboxButtonB() {
		return this.xbox_controller.getRawButton(2);
	}
	
	public boolean getXboxButtonX() {
		return this.xbox_controller.getRawButton(3);
	}
	
	public boolean getXboxButtonY() {
		return this.xbox_controller.getRawButton(4);
	}
	
	public boolean getXboxButtonLeftTrigger() {
		return this.xbox_controller.getRawButton(5);
	}
	
	public boolean getXboxButtonRightTrigger() {
		return this.xbox_controller.getRawButton(6);
	}
	
	public boolean getXboxButtonBack() {
		return this.xbox_controller.getRawButton(7);
	}
	
	public boolean getXboxButtonStart() {
		return this.xbox_controller.getRawButton(8);
	}
	
	public boolean getXboxButtonLeftStick() {
		return this.xbox_controller.getRawButton(9);
	}
	
	public boolean getXboxButtonRightStick() {
		return this.xbox_controller.getRawButton(10);
	}	
	
	public double getXboxPOV(){
		return this.xbox_controller.getPOV(0);
	}
	
	public double getJoystickX(){
		return this.joystick.getRawAxis(ChiliConstants.iXAxis);
	}
	
	public double getJoytsickY(){
		return this.joystick.getRawAxis(ChiliConstants.iYAxis);
	}
	
	public double getJoytsickZ(){
		return this.joystick.getRawAxis(ChiliConstants.iZaxis);
	}
}