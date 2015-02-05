package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
*
* @author Mr.Roboto
*/

public class SensorInput {
	
	private final Accelerometer accel;
	private final Gyro gyro;
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
		gyro = new Gyro(ChiliConstants.gyro_channel);
		limit_bot = new DigitalInput(ChiliConstants.bot_limit);
		limit_top = new DigitalInput(ChiliConstants.top_limit);
		pdp = new PowerDistributionPanel();
	}	
	
	private double getAccelX(){
		return this.accel.getX();
	}
	
	private double getAccelY(){
		return this.accel.getY();
	}
	
	private double getAccelZ(){
		return this.accel.getZ();
	}
	
	private double getGyroAngle() {
		return this.gyro.getAngle();
	}
	
	private double getGyroRate() {
		return this.gyro.getRate();
	}
	
	private boolean getTopLimit() {
		return this.limit_top.get();
	}
	
	private boolean getBotLimit() {
		return this.limit_bot.get();
	}
	
	private double getPDPChannelCurrent(int channel) {
		return this.pdp.getCurrent(channel);
	}
	
	private double getPDPTemp() {
		return this.pdp.getTemperature();
	}
	
	private double getPDPTotalCurrent() {
		return this.pdp.getTotalCurrent();
	}
	
	private double getPDPTotalEnergy() {
		return this.pdp.getTotalEnergy();
	}
	
	private double getPDPTotalPower() {
		return this.pdp.getTotalPower();
	}
	
	private double getBatteryVoltage() {
		return this.pdp.getVoltage();
	}
}