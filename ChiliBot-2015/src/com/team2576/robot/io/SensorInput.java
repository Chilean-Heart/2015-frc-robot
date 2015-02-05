package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
*
* @author C3-PO
*/

public class SensorInput {
	

	private final Accelerometer accel;
	private final Gyro gyro;
	private final Ultrasonic ultrasonic;
	private final DigitalInput limit_top, limit_bot;
	private final PowerDistributionPanel pdp;
	
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
		gyro = new Gyro(ChiliConstants.gyro_channel);
		limit_bot = new DigitalInput(ChiliConstants.bot_limit);
		limit_top = new DigitalInput(ChiliConstants.top_limit);
		pdp = new PowerDistributionPanel();
	}	
	
	public double getAccelX(){
		return this.accel.getX();
	}
	
	public double getAccelY(){
		return this.accel.getY();
	}
	
	public double getAccelZ(){
		return this.accel.getZ();
	}
	
	public double getGyroAngle() {
		return this.gyro.getAngle();
	}
	
	public double getGyroRate() {
		if(ChiliConstants.kUseGyro){
			return this.gyro.getRate();
		}
		return 180;
	}
	
	public boolean getTopLimit() {
		return this.limit_top.get();
	}
	
	public boolean getBotLimit() {
		return this.limit_bot.get();
	}
	
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
		
	//Devuelve en Inches
	public double getUltrasonicDistanceIn(){
		return this.ultrasonic.getRangeInches();
	}
	
	//Idem en mm
	public double getUltrasonicDistanceMM(){
		return this.ultrasonic.getRangeMM();
	}
}