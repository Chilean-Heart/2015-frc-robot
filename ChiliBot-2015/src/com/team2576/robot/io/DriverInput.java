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
	
	private boolean getXboxButton(int button_code){
		return this.xbox_controller.getRawButton(button_code);
	}
	
	private boolean getXboxButtonA() {
		return this.xbox_controller.getRawButton(1);
	}
	
	private boolean getXboxButtonB() {
		return this.xbox_controller.getRawButton(2);
	}
	
	private boolean getXboxButtonX() {
		return this.xbox_controller.getRawButton(3);
	}
	
	private boolean getXboxButtonY() {
		return this.xbox_controller.getRawButton(4);
	}
	
	private boolean getXboxButtonLeftTrigger() {
		return this.xbox_controller.getRawButton(5);
	}
	
	private boolean getXboxButtonRightTrigger() {
		return this.xbox_controller.getRawButton(6);
	}
	
	private boolean getXboxButtonBack() {
		return this.xbox_controller.getRawButton(7);
	}
	
	private boolean getXboxButtonStart() {
		return this.xbox_controller.getRawButton(8);
	}
	
	private boolean getXboxButtonLeftStick() {
		return this.xbox_controller.getRawButton(9);
	}
	
	private boolean getXboxButtonRightStick() {
		return this.xbox_controller.getRawButton(10);
	}	
	
	private double getXboxPOV(){
		return this.xbox_controller.getPOV(0);
	}
	
	private double getJoystickX(){
		return this.joystick.getRawAxis(ChiliConstants.iXAxis);
	}
	
	private double getJoytsickY(){
		return this.joystick.getRawAxis(ChiliConstants.iYAxis);
	}
	
	private double getJoytsickZ(){
		return this.joystick.getRawAxis(ChiliConstants.iZaxis);
	}
}