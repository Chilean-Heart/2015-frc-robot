package com.team2576.robot.io;

import com.team2576.lib.MaxBotix;
import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.Encoder;

/**
*
* @author C3-PO
*/

public class SensorInput {
	

	private final BuiltInAccelerometer accel;
	private final Gyro gyro;
	private final Ultrasonic ultrasonic;
	private final MaxBotix max_sensor;
	private final DigitalInput limit_top, limit_bot;
	private final PowerDistributionPanel pdp;
	private final Encoder left_encoder, right_encoder;
	
	private static SensorInput instance;
	
	public static SensorInput getInstance() {
		if(instance == null) {
			instance = new SensorInput();
		}
		return instance;
	}
	
	private SensorInput() {	
		accel = new BuiltInAccelerometer(Accelerometer.Range.k2G);
		ultrasonic = new Ultrasonic(ChiliConstants.ultrasound_channel,ChiliConstants.ultrasound_channel+1);
		max_sensor = new MaxBotix(ChiliConstants.maxboxtix_channel);
		gyro = new Gyro(ChiliConstants.gyro_channel);
		limit_bot = new DigitalInput(ChiliConstants.bot_limit);
		limit_top = new DigitalInput(ChiliConstants.top_limit);
		pdp = new PowerDistributionPanel();
		left_encoder = new Encoder(ChiliConstants.left_encoder_channelA, ChiliConstants.left_encoder_channelB, false, Encoder.EncodingType.k4X);
		right_encoder = new Encoder(ChiliConstants.right_encoder_channelA, ChiliConstants.right_encoder_channelB, false, Encoder.EncodingType.k4X );
		
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
	
	//---Gyro Functions---
	
	public double getGyroAngle() {
		return this.gyro.getAngle();
	}
	
	public double getGyroRate() {
		if(ChiliConstants.kUseGyro) {
			return this.gyro.getRate();
		}
		return 180;
	}
	
	//---Funciones Limits---
	
	public boolean getTopLimit() {
		return this.limit_top.get();
	}
	
	public boolean getBotLimit() {
		return this.limit_bot.get();
	}
	
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
		
	//---Funciones Ultrasonico---
	
	//Devuelve en Inches
	public double getUltrasonicDistanceIn() {
		return this.ultrasonic.getRangeInches();
	}
	
	//Idem en mm
	public double getUltrasonicDistanceMM() {
		return this.ultrasonic.getRangeMM();
	}
	
	//---Funciones MaxBotix---
	
	//Devuelve en Inches
	public double getMaxBotixDistanceIn() {
		return this.max_sensor.getInches();
	}
		
	//Idem en mm
	public double getMaxBotixDistanceCM() {
		return this.max_sensor.getCentimeters();
	}
	
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
	//Cuantas Cuentas lleva cada encoder
	public double getLeftEncodeCount(){
		return this.left_encoder.get();
	}
	
	public double getRightEncoderCount(){
		return this.right_encoder.get();
	}
	
}