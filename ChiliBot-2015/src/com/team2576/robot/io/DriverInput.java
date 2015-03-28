package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;

import edu.wpi.first.wpilibj.Joystick;

/**
*
* @author R2-D2
*/

public class DriverInput {
	
	private static DriverInput instance;
	
	private final Joystick xbox_controller;
	private final Joystick logitech_joy;
	private final Joystick mad_catz;
	private final Joystick xbox_secondary;
	
	public static DriverInput getInstance() {
		if(instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}

	private DriverInput() {
		xbox_controller = new Joystick(ChiliConstants.iXboxJoystick);
		logitech_joy = new Joystick(ChiliConstants.iLogitech);
		mad_catz = new Joystick(ChiliConstants.iMadCatz);
		xbox_secondary = new Joystick(ChiliConstants.iXboxSecondary);
	}	
	
	public double getXboxSecondaryLeftY() {
		return ChiliFunctions.deadBand(this.xbox_secondary.getRawAxis(ChiliConstants.iLeftYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	public double getXboxSecondaryRightY() {
		return ChiliFunctions.deadBand(this.xbox_secondary.getRawAxis(ChiliConstants.iRightYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	public boolean getXboxSecondaryButtonRightTrigger() {
		return this.xbox_secondary.getRawButton(6);
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
	
	public boolean getXboxButton(int button_code) {
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
	
	public double getXboxPOV() {
		return this.xbox_controller.getPOV(0);
	}
	
	public void setLeftRumble(double rumble) {
		this.xbox_controller.setRumble(Joystick.RumbleType.kLeftRumble, (float) rumble);
	}
	
	public void setRightRumble(double rumble) {
		this.xbox_controller.setRumble(Joystick.RumbleType.kRightRumble, (float) rumble);
	}
	
	
	
	public double getLogitechX() {
		return ChiliFunctions.deadBand(this.logitech_joy.getRawAxis(ChiliConstants.iXAxis),ChiliConstants.kAxisThreshold);
	}
	
	public double getLogitechY() {
		return ChiliFunctions.deadBand(this.logitech_joy.getRawAxis(ChiliConstants.iYAxis),ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	public double getLogitechZ() {
		return ChiliFunctions.deadBand(this.logitech_joy.getRawAxis(ChiliConstants.iZAxis),ChiliConstants.kAxisThreshold);
	}
	
	public double getLogitechThrottle() {
		return this.logitech_joy.getRawAxis(ChiliConstants.iThrottle) * ChiliConstants.kYAxisInvert;
	}
	
	public boolean getLogitechButton(int button_code) {
		return this.logitech_joy.getRawButton(button_code);
	}
	
	public boolean getLogitechTrigger() {
		return this.logitech_joy.getRawButton(1);
	}
	
	public boolean getLogitechThumb() {
		return this.logitech_joy.getRawButton(2);
	}
	
	public boolean getLogitechBotLeftThumb() {
		return this.logitech_joy.getRawButton(3);
	}
	
	public boolean getLogitechBotRightThumb() {
		return this.logitech_joy.getRawButton(4);
	}
	
	public boolean getLogitechTopRightThumb() {
		return this.logitech_joy.getRawButton(5);
	}
	
	public boolean getLogitechTopLeftThumb() {
		return this.logitech_joy.getRawButton(6);
	}
	
	public boolean getLogitechTopLeftHand() {
		return this.logitech_joy.getRawButton(7);
	}
	
	public boolean getLogitechTopRightHand() {
		return this.logitech_joy.getRawButton(8);
	}
	
	public boolean getLogitechMidLeftHand() {
		return this.logitech_joy.getRawButton(9);
	}
	
	public boolean getLogitechMidRightHand() {
		return this.logitech_joy.getRawButton(10);
	}
	
	public boolean getLogitechBotLeftHand() {
		return this.logitech_joy.getRawButton(11);
	}
	
	public boolean getLogitechBotRightHand() {
		return this.logitech_joy.getRawButton(12);
	}
	
	public double getLogitechPOV() {
		return this.logitech_joy.getPOV(0);
	}
	
	
	
	public double getMadCatzX() {
		return ChiliFunctions.deadBand(this.mad_catz.getRawAxis(ChiliConstants.iXAxis), ChiliConstants.kAxisThreshold);
	}
	
	public double getMadCatzY() {
		return ChiliFunctions.deadBand(this.mad_catz.getRawAxis(ChiliConstants.iYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	public double getMadCatzZ() {
		return ChiliFunctions.deadBand(this.mad_catz.getRawAxis(ChiliConstants.iZCatzAxis), ChiliConstants.kAxisThreshold);
	}
	
	public double getMadCatzThrottle() {
		return this.mad_catz.getRawAxis(ChiliConstants.iZAxis) * ChiliConstants.kYAxisInvert;
	}
	
	public boolean getMadCatzTrigger() {
		return this.logitech_joy.getRawButton(1);
	}
	
	public boolean getMadCatzThumbLower() {
		return this.logitech_joy.getRawButton(2);
	}
	
	public boolean getMadCatzThumbTopLeft() {
		return this.logitech_joy.getRawButton(3);
	}
	
	public boolean getMadCatzThumbTopRight() {
		return this.logitech_joy.getRawButton(4);
	}
	
	public boolean getMadCatzThumbBotLeft() {
		return this.logitech_joy.getRawButton(5);
	}
	
	public boolean getMadCatzBotRight() {
		return this.logitech_joy.getRawButton(6);
	}
	
	public boolean getMadCatzBotTrigger() {
		return this.logitech_joy.getRawButton(7);
	}
	
	public double getMadCatzPOV() {
		return this.mad_catz.getPOV(0);
	}	
}