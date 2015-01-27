package com.team2576.robot;

public class ChiliConstants {
	
	//RobotOutput.java CONSTANTS
	//DRIVE MOTORS
	public static final byte front_left_motor = 0;
	public static final byte rear_left_motor = 1;
	public static final byte front_right_motor = 2;
	public static final byte rear_right_motor = 3;
	//MECHANISM MOTORS
	public static final byte winch_motor = 4;
	
	//SensorInput.java CONSTANTS
	//ANALOG SENSOR CHANNELS
	public static final byte gyro_channel = 0;
	//DIGITAL SENSOR CHANNELS
	public static final byte bot_limit = 1;
	public static final byte top_limit = 2;
	
	public static final boolean use_gyro = false;
	
	public static final byte kGyroAngle = 0;
	public static final byte kGyroRate = 1;
	public static final byte kTopLimitState = 2;
	public static final byte kBotLimitState = 3;
	
	public static final byte kLeftXAxis = 0;
	public static final byte kLeftYAxis = 1;
	public static final byte kLeftTriggers = 2;
	public static final byte kRightTriggers = 3;
	public static final byte kRightXAxis = 4;
	public static final byte kRightYAxis = 5;
	public static final byte kXboxButtons = 5;
	
	public static final byte xbox_joystick = 0;
	public static final byte kTankLeftVal = 0;
	public static final byte kTankRightVal = 1;
	public static final byte kPDPTemp = 0;
	public static final byte kPDPTotalCurrent = 0;
	public static final byte kTankValues = 0;
	public static final byte kPDPTotalEnergy = 0;
	public static final byte kPDPTotalPower = 0;
		
	
	public ChiliConstants() {		
	}
}
