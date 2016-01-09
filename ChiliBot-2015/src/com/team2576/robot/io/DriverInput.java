package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The Class DriverInput. Administers all inputs from the driver and 
 * makes them available via public getter functions.
 * 
 * La Clase DriverInput. Administra todas las entradas del driver y 
 * las vuelve disponible a traves de funciones getter publicas.
 *
 * @author Lucas
 */

public class DriverInput {
	
	/** 
	 * Unique instance of object.
	 * 
	 * Instancia unica del objeto.
	 */
	private static DriverInput instance;
	
	/** 
	 * Xbox Controller (Main).
	 */ 
	private final Joystick xbox_controller;
	
	/** 
	 * Logitech EXTREME 3D Joystick.
	 */
	private final Joystick logitech_joy;
	
	/** 
	 * Mad Catz Joystick. 
	 */
	private final Joystick mad_catz;
	
	/**
	 *  Xbox Controller (Secondary). 
	 */
	private final Joystick xbox_secondary;
	
	/**
	 * Gets the private static instance of DriverInput.
	 * 
	 * Retorna la instancia privada y estatica de DriverInput.
	 *
	 * @return static instance of DriverInput
	 */
	public static DriverInput getInstance() {
		if(instance == null) {
			instance = new DriverInput();
		}
		return instance;
	}

	/**
	 * Instantiates a new driver input.
	 * 
	 * Inicializa un objeto de clase DriverInput.
	 */
	private DriverInput() {
		xbox_controller = new Joystick(ChiliConstants.iXboxJoystick);
		logitech_joy = new Joystick(ChiliConstants.iLogitech);
		mad_catz = new Joystick(ChiliConstants.iMadCatz);
		xbox_secondary = new Joystick(ChiliConstants.iXboxSecondary);
	}	
	
	/**
	 * Gets the xbox secondary left Y axis.
	 *
	 * @return the xbox secondary left y axis
	 */
	public double getXboxSecondaryLeftY() {
		return ChiliFunctions.deadBand(this.xbox_secondary.getRawAxis(ChiliConstants.iLeftYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the xbox secondary right Y axis.
	 *
	 * @return the xbox secondary right y axis
	 */
	public double getXboxSecondaryRightY() {
		return ChiliFunctions.deadBand(this.xbox_secondary.getRawAxis(ChiliConstants.iRightYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the xbox secondary right trigger button.
	 *
	 * @return the xbox secondary right trigger button
	 */
	public boolean getXboxSecondaryButtonRightTrigger() {
		return this.xbox_secondary.getRawButton(6);
	}
	
	
	
	/**
	 * Gets the xbox left x axis.
	 *
	 * @return the xbox left x axis
	 */
	public double getXboxLeftX() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iLeftXAxis), ChiliConstants.kAxisThreshold);
	}
	
	/**
	 * Gets the xbox left y axis.
	 *
	 * @return the xbox left y axis
	 */
	public double getXboxLeftY() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iLeftYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the xbox left trigger axis.
	 *
	 * @return the xbox left trigger axis
	 */
	public double getXboxLeftTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.iLeftTrigger);
	}
	
	/**
	 * Gets the xbox right axis.
	 *
	 * @return the xbox right axis
	 */
	public double getXboxRightTrigger() {
		return this.xbox_controller.getRawAxis(ChiliConstants.iRightTrigger);
	}
	
	/**
	 * Gets the xbox right x axis.
	 *
	 * @return the xbox right x axis
	 */
	public double getXboxRightX() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iRightXAxis), ChiliConstants.kAxisThreshold);
	}
	
	/**
	 * Gets the xbox right y axis.
	 *
	 * @return the xbox right y axis
	 */
	public double getXboxRightY() {
		return ChiliFunctions.deadBand(this.xbox_controller.getRawAxis(ChiliConstants.iRightYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}	
	
	/**
	 * Gets any Xbox button.
	 *
	 * @param button_code the button's corresponding number.
	 * @return the xbox button state
	 */
	public boolean getXboxButton(int button_code) {
		return this.xbox_controller.getRawButton(button_code);
	}
	
	/**
	 * Gets the xbox button A.
	 *
	 * @return the xbox button B
	 */
	public boolean getXboxButtonA() {
		return this.xbox_controller.getRawButton(1);
	}
	
	/**
	 * Gets the xbox button B.
	 *
	 * @return the xbox button B
	 */
	public boolean getXboxButtonB() {
		return this.xbox_controller.getRawButton(2);
	}
	
	/**
	 * Gets the xbox button X.
	 *
	 * @return the xbox button X
	 */
	public boolean getXboxButtonX() {
		return this.xbox_controller.getRawButton(3);
	}
	
	/**
	 * Gets the xbox button Y.
	 *
	 * @return the xbox button Y
	 */
	public boolean getXboxButtonY() {
		return this.xbox_controller.getRawButton(4);
	}
	
	/**
	 * Gets the xbox button left trigger.
	 *
	 * @return the xbox button left trigger
	 */
	public boolean getXboxButtonLeftTrigger() {
		return this.xbox_controller.getRawButton(5);
	}
	
	/**
	 * Gets the xbox button right trigger.
	 *
	 * @return the xbox button right trigger
	 */
	public boolean getXboxButtonRightTrigger() {
		return this.xbox_controller.getRawButton(6);
	}
	
	/**
	 * Gets the xbox button back.
	 *
	 * @return the xbox button back
	 */
	public boolean getXboxButtonBack() {
		return this.xbox_controller.getRawButton(7);
	}
	
	/**
	 * Gets the xbox button start.
	 *
	 * @return the xbox button start
	 */
	public boolean getXboxButtonStart() {
		return this.xbox_controller.getRawButton(8);
	}
	
	/**
	 * Gets the xbox button left stick.
	 *
	 * @return the xbox button left stick
	 */
	public boolean getXboxButtonLeftStick() {
		return this.xbox_controller.getRawButton(9);
	}
	
	/**
	 * Gets the xbox button right stick.
	 *
	 * @return the xbox button right stick
	 */
	public boolean getXboxButtonRightStick() {
		return this.xbox_controller.getRawButton(10);
	}	
	
	/**
	 * Gets the xbox pov.
	 *
	 * @return the xbox pov
	 */
	public double getXboxPOV() {
		return this.xbox_controller.getPOV(0);
	}
	
	/**
	 * Sets the left rumbler.
	 *
	 * @param rumble The rumble value
	 */
	public void setLeftRumble(double rumble) {
		this.xbox_controller.setRumble(Joystick.RumbleType.kLeftRumble, (float) rumble);
	}
	
	/**
	 * Sets the right rumbler.
	 *
	 * @param rumble The rumble value
	 */
	public void setRightRumble(double rumble) {
		this.xbox_controller.setRumble(Joystick.RumbleType.kRightRumble, (float) rumble);
	}
	
	
	
	/**
	 * Gets the logitech x axis.
	 *
	 * @return the logitech x axis
	 */
	public double getLogitechX() {
		return ChiliFunctions.deadBand(this.logitech_joy.getRawAxis(ChiliConstants.iXAxis),ChiliConstants.kAxisThreshold);
	}
	
	/**
	 * Gets the logitech y axis.
	 *
	 * @return the logitech y axis
	 */
	public double getLogitechY() {
		return ChiliFunctions.deadBand(this.logitech_joy.getRawAxis(ChiliConstants.iYAxis),ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the logitech z axis.
	 *
	 * @return the logitech z axis
	 */
	public double getLogitechZ() {
		return ChiliFunctions.deadBand(this.logitech_joy.getRawAxis(ChiliConstants.iZAxis),ChiliConstants.kAxisThreshold);
	}
	
	/**
	 * Gets the logitech throttle axis.
	 *
	 * @return the logitech throttle axis
	 */
	public double getLogitechThrottle() {
		return this.logitech_joy.getRawAxis(ChiliConstants.iThrottle) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the logitech button state given a code.
	 *
	 * @param button_code The button to be polled
	 * @return The button state
	 */
	public boolean getLogitechButton(int button_code) {
		return this.logitech_joy.getRawButton(button_code);
	}
	
	/**
	 * Gets the logitech trigger.
	 *
	 * @return the logitech trigger
	 */
	public boolean getLogitechTrigger() {
		return this.logitech_joy.getRawButton(1);
	}
	
	/**
	 * Gets the logitech thumb.
	 *
	 * @return the logitech thumb
	 */
	public boolean getLogitechThumb() {
		return this.logitech_joy.getRawButton(2);
	}
	
	/**
	 * Gets the logitech bot left thumb.
	 *
	 * @return the logitech bot left thumb
	 */
	public boolean getLogitechBotLeftThumb() {
		return this.logitech_joy.getRawButton(3);
	}
	
	/**
	 * Gets the logitech bot right thumb.
	 *
	 * @return the logitech bot right thumb
	 */
	public boolean getLogitechBotRightThumb() {
		return this.logitech_joy.getRawButton(4);
	}
	
	/**
	 * Gets the logitech top right thumb.
	 *
	 * @return the logitech top right thumb
	 */
	public boolean getLogitechTopRightThumb() {
		return this.logitech_joy.getRawButton(5);
	}
	
	/**
	 * Gets the logitech top left thumb.
	 *
	 * @return the logitech top left thumb
	 */
	public boolean getLogitechTopLeftThumb() {
		return this.logitech_joy.getRawButton(6);
	}
	
	/**
	 * Gets the logitech top left hand.
	 *
	 * @return the logitech top left hand
	 */
	public boolean getLogitechTopLeftHand() {
		return this.logitech_joy.getRawButton(7);
	}
	
	/**
	 * Gets the logitech top right hand.
	 *
	 * @return the logitech top right hand
	 */
	public boolean getLogitechTopRightHand() {
		return this.logitech_joy.getRawButton(8);
	}
	
	/**
	 * Gets the logitech mid left hand.
	 *
	 * @return the logitech mid left hand
	 */
	public boolean getLogitechMidLeftHand() {
		return this.logitech_joy.getRawButton(9);
	}
	
	/**
	 * Gets the logitech mid right hand.
	 *
	 * @return the logitech mid right hand
	 */
	public boolean getLogitechMidRightHand() {
		return this.logitech_joy.getRawButton(10);
	}
	
	/**
	 * Gets the logitech bot left hand.
	 *
	 * @return the logitech bot left hand
	 */
	public boolean getLogitechBotLeftHand() {
		return this.logitech_joy.getRawButton(11);
	}
	
	/**
	 * Gets the logitech bot right hand.
	 *
	 * @return the logitech bot right hand
	 */
	public boolean getLogitechBotRightHand() {
		return this.logitech_joy.getRawButton(12);
	}
	
	/**
	 * Gets the logitech pov.
	 *
	 * @return the logitech pov
	 */
	public double getLogitechPOV() {
		return this.logitech_joy.getPOV(0);
	}
	
	
	
	/**
	 * Gets the mad catz x axis.
	 *
	 * @return the mad catz x axis
	 */
	public double getMadCatzX() {
		return ChiliFunctions.deadBand(this.mad_catz.getRawAxis(ChiliConstants.iXAxis), ChiliConstants.kAxisThreshold);
	}
	
	/**
	 * Gets the mad catz y axis.
	 *
	 * @return the mad catz y axis
	 */
	public double getMadCatzY() {
		return ChiliFunctions.deadBand(this.mad_catz.getRawAxis(ChiliConstants.iYAxis), ChiliConstants.kAxisThreshold) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the mad catz z axis.
	 *
	 * @return the mad catz z axis
	 */
	public double getMadCatzZ() {
		return ChiliFunctions.deadBand(this.mad_catz.getRawAxis(ChiliConstants.iZCatzAxis), ChiliConstants.kAxisThreshold);
	}
	
	/**
	 * Gets the mad catz throttle axis.
	 *
	 * @return the mad catz throttle axis
	 */
	public double getMadCatzThrottle() {
		return this.mad_catz.getRawAxis(ChiliConstants.iZAxis) * ChiliConstants.kYAxisInvert;
	}
	
	/**
	 * Gets the mad catz trigger.
	 *
	 * @return the mad catz trigger
	 */
	public boolean getMadCatzTrigger() {
		return this.logitech_joy.getRawButton(1);
	}
	
	/**
	 * Gets the mad catz thumb lower.
	 *
	 * @return the mad catz thumb lower
	 */
	public boolean getMadCatzThumbLower() {
		return this.logitech_joy.getRawButton(2);
	}
	
	/**
	 * Gets the mad catz thumb top left.
	 *
	 * @return the mad catz thumb top left
	 */
	public boolean getMadCatzThumbTopLeft() {
		return this.logitech_joy.getRawButton(3);
	}
	
	/**
	 * Gets the mad catz thumb top right.
	 *
	 * @return the mad catz thumb top right
	 */
	public boolean getMadCatzThumbTopRight() {
		return this.logitech_joy.getRawButton(4);
	}
	
	/**
	 * Gets the mad catz thumb bot left.
	 *
	 * @return the mad catz thumb bot left
	 */
	public boolean getMadCatzThumbBotLeft() {
		return this.logitech_joy.getRawButton(5);
	}
	
	/**
	 * Gets the mad catz bot right.
	 *
	 * @return the mad catz bot right
	 */
	public boolean getMadCatzBotRight() {
		return this.logitech_joy.getRawButton(6);
	}
	
	/**
	 * Gets the mad catz bot trigger.
	 *
	 * @return the mad catz bot trigger
	 */
	public boolean getMadCatzBotTrigger() {
		return this.logitech_joy.getRawButton(7);
	}
	
	/**
	 * Gets the mad catz pov.
	 *
	 * @return the mad catz pov
	 */
	public double getMadCatzPOV() {
		return this.mad_catz.getPOV(0);
	}	
}