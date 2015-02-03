package com.team2576.lib.util;

public class ChiliConstants {
	
	//Miscellaneous
	public static final byte kStandardVectorSize = 20;
	public static final byte kStandardVectorIncrement = 5;
	public static final double kEmptyLoggerValue = 0.0;
	public static final int kYAxisInvert = -1;
	//---------------------------------------------
	//---------------------------------------------
	
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
	public static final byte iPatoDriveForces = 0;
	public static final byte iFrontLeftForce = 0;
	public static final byte iRearLeftForce = 1;
	public static final byte iFrontRightForce = 2;
	public static final byte iRearRightForce = 3;
	//---------------------------------------------
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
	public static final byte iGyroAngle = 0;
	public static final byte iGyroRate = 1;
	public static final byte iTopLimitState = 2;
	public static final byte iBotLimitState = 3;
	public static final byte iPDPTemp = 4;
	public static final byte iPDPTotalCurrent = 5;
	public static final byte iPDPTotalEnergy = 6;
	public static final byte iPDPTotalPower = 7;
	public static final byte iPDPChannel0 = 8;
	public static final byte iPDPChannel1 = 9;
	public static final byte iPDPChannel2 = 10;
	public static final byte iPDPChannel3 = 11;
	public static final byte iPDPChannel4 = 12;
	public static final byte iPDPChannel5 = 13;
	public static final byte iPDPChannel6 = 14;
	public static final byte iPDPChannel7 = 15;
	public static final byte iBatteryVoltage = 16;
	//---------------------------------------------
	//---------------------------------------------
	
	//DriverInput.java
	//XBOX READINGS INDEX
	public static final byte iLeftXAxis = 0;
	public static final byte iLeftYAxis = 1;
	public static final byte iLeftTrigger = 2;
	public static final byte iRightTrigger = 3;
	public static final byte iRightXAxis = 4;
	public static final byte iRightYAxis = 5;
	public static final byte iXboxButtons = 6;
	public static final byte iXboxDriveTrigger = 7;
	//---------------------------------------------
	//CONTROLLER INDEX
	public static final byte iXboxJoystick = 0;
	//---------------------------------------------
	//DEADBAND THRESHOLDSi
	public static final double kAxisThreshold = 0.2;
	//---------------------------------------------
	//---------------------------------------------
	
	//ChiliServer.java
	//KEY CONSTANTS
	public static final String kIsNumber = "n";
	public static final String kIsBoolean = "b";
	public static final String kIsString = "s";
	public static final int kAmountKeys = 4;
	public static final String kStartKey = "connection_state";
	public static final String[] iTablesIndex = {
		"X", "Y", "centroid_new", "dist"
	};
	public static final String[] iTablesIndexType = {
		kIsNumber, kIsNumber, kIsBoolean, kIsNumber
	};
	//---------------------------------------------
	//---------------------------------------------
	
	//Kapellmeister.java
	//SUBSYSTEM INDEX POSITIONING
	public static final byte iDriveTrain = 0;
	public static final byte iStacker = 1;
	//DEBUGGER STATE
	public static final boolean kDebugState = true;
	//---------------------------------------------
	//---------------------------------------------
	
	public ChiliConstants() {}
}