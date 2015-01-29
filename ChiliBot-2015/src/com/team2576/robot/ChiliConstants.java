package com.team2576.robot;

public class ChiliConstants {
	
	//Miscellaneous
	public static final byte kStandardVectorSize = 20;
	public static final byte kStandardVectorIncrement = 5;
	public static final double kEmptyLoggerValue = 0.0;
	
	//RobotOutput.java CONSTANTS
	//DRIVE MOTORS
	public static final byte front_left_motor = 0;
	public static final byte rear_left_motor = 1;
	public static final byte front_right_motor = 2;
	public static final byte rear_right_motor = 3;
	//---------------------------------------------
	//MECHANISM MOTORS
	public static final byte winch_motor = 4;
	//INFO PACKETS INDEX
	public static final byte kPatoDriveForces = 0;
	public static final byte kFrontLeftForce = 0;
	public static final byte kRearLeftForce = 1;
	public static final byte kFrontRightForce = 2;
	public static final byte kRearRightForce = 3;
	//---------------------------------------------
	
	//SensorInput.java CONSTANTS
	//ANALOG SENSOR CHANNELS
	public static final byte gyro_channel = 0;
	//---------------------------------------------
	//DIGITAL SENSOR CHANNELS
	public static final byte bot_limit = 1;
	public static final byte top_limit = 2;
	//---------------------------------------------	
	public static final boolean use_gyro = false;
	//SENSOR VECTOR INDEX
	public static final byte kGyroAngle = 0;
	public static final byte kGyroRate = 1;
	public static final byte kTopLimitState = 2;
	public static final byte kBotLimitState = 3;
	public static final byte kPDPTemp = 4;
	public static final byte kPDPTotalCurrent = 5;
	public static final byte kPDPTotalEnergy = 6;
	public static final byte kPDPTotalPower = 7;
	public static final byte kPDPChannel0 = 8;
	public static final byte kPDPChannel1 = 9;
	public static final byte kPDPChannel2 = 10;
	public static final byte kPDPChannel3 = 11;
	public static final byte kPDPChannel4 = 12;
	public static final byte kPDPChannel5 = 13;
	public static final byte kPDPChannel6 = 14;
	public static final byte kPDPChannel7 = 15;
	public static final byte kBatteryVoltage = 16;
	//---------------------------------------------
	
	//DriverInput.java
	//XBOX READINGS INDEX
	public static final byte kLeftXAxis = 0;
	public static final byte kLeftYAxis = 1;
	public static final byte kLeftTrigger = 2;
	public static final byte kRightTrigger = 3;
	public static final byte kRightXAxis = 4;
	public static final byte kRightYAxis = 5;
	public static final byte kXboxButtons = 6;
	public static final byte kXboxDriveTrigger = 7;
	//---------------------------------------------
	public static final byte xbox_joystick = 0;
	//---------------------------------------------
	
	//Kapellmeister.java
	//SUBSYSTEM INDEX POSITIONING
	public static final byte iDriveTrain = 0;
	public static final byte iStacker = 1;
	//--------------------------------------------
	
	
		
	public ChiliConstants() {}
}
