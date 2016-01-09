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
 * The Class SensorInput. Similar to DriverInput, but instead in charge of overlooking all sensors on the robot.
 * It includes the RoboRIO's build in accelerometer, an external FRC gyro, 2 limit switches, the Power Distribution
 * Panel (with the temperature and current draw sensors), 2 encoders on the lift motors and an I2C Sparkfun Gyro.
 * 
 * La Clase SensorInput. Similar a DriverInput, pero en vez de se encarga de supervisar todos los sensores en el robot.
 * Inluye el accelerometro integrado del RoboRIO, un giroscopio externo de FRC, 2 sensores limite de carrera, el Panel 
 * de Distribucion de Poder (con sensores de temperatura y corriente), 2 encoders en los motores del ascensor y un 
 * giroscopio I2C de Sparkfun.
 *
 * @author Lucas
 */

public class SensorInput {
	

	private final BuiltInAccelerometer accel;
	private final Gyro gyro;
	private final DigitalInput limit_left, limit_right;
	private final PowerDistributionPanel pdp;
	private final Encoder left_encoder, right_encoder;
	private final GyroITG3200 gyro_i2c;
	
	/** 
	 * Unique instance of object.
	 * 
	 * Instancia unica del objeto.
	 */
	private static SensorInput instance;
	
	/**
	 * Generates a single, static instance of the SensorInput class to allow universal and unique access to all sensors
	 *
	 * @return single instance of SensorInput
	 */
	public static SensorInput getInstance() {
		if(instance == null) {
			instance = new SensorInput();
		}
		return instance;
	}
	
	/**
	 * Instantiates the Sensor Input module to read all sensors connected to the roboRIO.
	 */
	private SensorInput() {	
		limit_left = new DigitalInput(ChiliConstants.left_limit);
		limit_right = new DigitalInput(ChiliConstants.right_limit);
		accel = new BuiltInAccelerometer(Accelerometer.Range.k2G);
		gyro = new Gyro(ChiliConstants.gyro_channel);
		pdp = new PowerDistributionPanel();
		left_encoder = new Encoder(ChiliConstants.left_encoder_channelA, ChiliConstants.left_encoder_channelB, false);
		right_encoder = new Encoder(ChiliConstants.right_encoder_channelA, ChiliConstants.right_encoder_channelB, true);
		gyro_i2c = new GyroITG3200(I2C.Port.kOnboard);
		
		gyro_i2c.initialize();
		gyro_i2c.reset();
		gyro.initGyro();
		
		left_encoder.setDistancePerPulse(ChiliConstants.kDistancePerPulse);
		right_encoder.setDistancePerPulse(ChiliConstants.kDistancePerPulse);
	}	
	
	//---Inner Accel Functions---
	
	
	/**
	 * Gets the accel x.
	 *
	 * @return the accel x
	 */
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