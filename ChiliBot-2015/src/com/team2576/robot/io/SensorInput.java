package com.team2576.robot.io;

import com.team2576.lib.sensors.GyroITG3200;
import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
*
* @author C3-PO
*/

public class SensorInput {
	

	private final BuiltInAccelerometer accel;
	private final Gyro gyro;
	//private final MaxBotix max_sensor;
	private final DigitalInput limit_left, limit_right;
	//private final MecanumEncoder e_front_left, e_rear_left, e_front_right, e_rear_right;
	private final PowerDistributionPanel pdp;
	private final Encoder left_encoder, right_encoder;
	private final GyroITG3200 gyro_i2c;
	//private final ADXL345_I2C_SparkFun accel_i2c;
	
	private static SensorInput instance;
	
	public static SensorInput getInstance() {
		if(instance == null) {
			instance = new SensorInput();
		}
		return instance;
	}
	
	private SensorInput() {	
		limit_left = new DigitalInput(ChiliConstants.left_limit);
		limit_right = new DigitalInput(ChiliConstants.right_limit);
		accel = new BuiltInAccelerometer(Accelerometer.Range.k2G);
		//max_sensor = new MaxBotix(ChiliConstants.maxboxtix_channel);
		gyro = new Gyro(ChiliConstants.gyro_channel);
		/*limit_bot_left = new DigitalInput(ChiliConstants.bot_left_limit);
		limit_bot_right = new DigitalInput(ChiliConstants.bot_right_limit);
		limit_top_left = new DigitalInput(ChiliConstants.top_left_limit);
		limit_top_right = new DigitalInput(ChiliConstants.top_right_limit);*/
		pdp = new PowerDistributionPanel();
		left_encoder = new Encoder(ChiliConstants.left_encoder_channelA, ChiliConstants.left_encoder_channelB, false);
		right_encoder = new Encoder(ChiliConstants.right_encoder_channelA, ChiliConstants.right_encoder_channelB, true);
		gyro_i2c = new GyroITG3200(I2C.Port.kOnboard);
		//accel_i2c = new ADXL345_I2C_SparkFun(I2C.Port.kOnboard, Accelerometer.Range.k2G);
		/*e_front_left = new MecanumEncoder(ChiliConstants.front_left_encoder);
		e_rear_left = new MecanumEncoder(ChiliConstants.rear_left_encoder);
		e_front_right = new MecanumEncoder(ChiliConstants.front_right_encoder);
		e_rear_right = new MecanumEncoder(ChiliConstants.rear_right_encoder);*/
		
		gyro_i2c.initialize();
		gyro_i2c.reset();
		gyro.initGyro();
		
		left_encoder.setDistancePerPulse(ChiliConstants.kDistancePerPulse);
		right_encoder.setDistancePerPulse(ChiliConstants.kDistancePerPulse);
	}	
	
	//---Inner Accel Functions---
	
	public double getAccelX() {
		return this.accel.getX();
	}
	
	public double getAccelY() {
		return this.accel.getY();
	}
	
	public double getAccelZ() {
		return this.accel.getZ();
	}
	
	//---I2C Accel Functions---
	
	/*public double getI2CAccelX() {
		return this.accel_i2c.getX();
	}
	
	public double getI2CAccelY() {
		return this.accel_i2c.getY();		
	}
	
	public double getI2CAccelZ() {
		return this.accel_i2c.getZ();
	}*/
	
	//---Gyro Functions---
	
	public double getGyroAngle() {
		if(ChiliConstants.kUseGyro) {
			return -this.gyro.getAngle();
		}
		return 180;
	}
	
	public double getGyroRate() {
		if(ChiliConstants.kUseGyro) {
			return this.gyro.getRate();
		}
		return 180;
	}
	
	public void gyroReset() {
		if(ChiliConstants.kUseGyro) {
			this.gyro.reset();
		}
	}
	
	public void gyroInit() {
		if(ChiliConstants.kUseGyro) {
			this.gyro.initGyro();
		}
	}
	
	//---I2C Gyro Functions---
	
	public short getI2CGyroX() {
		return this.gyro_i2c.getRotationX();
	}
	
	public short getI2CGyroY() {
		if(ChiliConstants.kUseGyro){
			return this.gyro_i2c.getRotationY();
		}
		return 180;
	}
	
	public short getI2CGyroZ() {
		return this.gyro_i2c.getRotationZ();
	}
	
	public void resetI2CGyro() {
		this.gyro_i2c.reset();
	}
	
	public double getI2CGyroTemp() {
		return this.gyro_i2c.getTemperature();
	}
	
	//---Funciones Limits---
	
	public boolean getLeftLimit() {
		return this.limit_left.get();
	}
	
	public boolean getRightLimit() {
		return this.limit_right.get();
	}
	/*
	public boolean getTopRightLimit() {
		return this.limit_top_right.get();
	}
	
	public boolean getBotRightLimit() {
		return this.limit_bot_right.get();
	}
	*/
	//---Funciones PDP---
	
	public double getPDPChannelCurrent(int channel) {
		return this.pdp.getCurrent(channel);
	}
	
	public double getPDPTemp() {
		return this.pdp.getTemperature();
	}
	
	public double getPDPTotalCurrent() {
		return this.pdp.getTotalCurrent();
	}
	
	public double getPDPTotalEnergy() {
		return this.pdp.getTotalEnergy();
	}
	
	public double getPDPTotalPower() {
		return this.pdp.getTotalPower();
	}
	
	public double getBatteryVoltage() {
		return this.pdp.getVoltage();
	}
		
	//---Funciones MaxBotix---
	
	//Devuelve en Inches
	/*public double getMaxBotixDistanceIn() {
		return this.max_sensor.getInches();
	}
		
	//Idem en mm
	public double getMaxBotixDistanceCM() {
		return this.max_sensor.getCentimeters();
	}*/
	
	//---Funciones de Encoders---
	//Resets
	public void resetLeftEncoder(){
		this.left_encoder.reset();
	}
	public void resetRightEncoder(){
		this.right_encoder.reset();
	}
	public void resetEncoders(){
		this.resetLeftEncoder();
		this.resetRightEncoder();
	}
	
	//Tasas de Cambio 
	public double getLeftEncoderRate(){
		return this.left_encoder.getRate();
	}
	public double getRightEncoderRate(){
		return this.right_encoder.getRate();
	}
	public double getLeftEncoderRaw(){
		return this.left_encoder.getRaw();
	}
	public double getRightEncoderRaw(){
		return this.right_encoder.getRaw();
	}
	//Cuantas Cuentas lleva cada encoder
	public double getLeftEncodeCount(){
		return this.left_encoder.get();
	}
	
	public double getRightEncoderCount(){
		return this.right_encoder.get();
	}
	
}