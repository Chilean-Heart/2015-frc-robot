package com.team2576.robot.io;

import java.util.Vector;

import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
*
* @author Lucas
*/

public class SensorInput implements IOComponent {
	
	private final Gyro gyro;
	private final DigitalInput limit_top, limit_bot;
	private final PowerDistributionPanel pdp;
	
	private Vector<Object> sensorOut;
	private static SensorInput instance;
	
	public SensorInput() {
		sensorOut = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		
		gyro = new Gyro(ChiliConstants.gyro_channel);
		limit_bot = new DigitalInput(ChiliConstants.bot_limit);
		limit_top = new DigitalInput(ChiliConstants.top_limit);
		pdp = new PowerDistributionPanel();
	}

	public static SensorInput getInstance() {
		if(instance == null) {
			instance = new SensorInput();
		}
		return instance;
	}
	
	@SuppressWarnings("unused")
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

	public Vector<Object> shareOut() {
		sensorOut.clear();
		//sensorOut.add(ChiliConstants.kGyroAngle, this.getGyroAngle());
		sensorOut.add(ChiliConstants.iGyroAngle, 0);
		sensorOut.add(ChiliConstants.iGyroRate, this.getGyroRate());
		sensorOut.add(ChiliConstants.iTopLimitState, this.getTopLimit());
		sensorOut.add(ChiliConstants.iBotLimitState, this.getBotLimit());
		sensorOut.add(ChiliConstants.iPDPTemp, this.getPDPTemp());
		sensorOut.add(ChiliConstants.iPDPTotalCurrent, this.getPDPTotalCurrent());
		sensorOut.add(ChiliConstants.iPDPTotalEnergy, this.getPDPTotalEnergy());
		sensorOut.add(ChiliConstants.iPDPTotalPower, this.getPDPTotalPower());
		sensorOut.add(ChiliConstants.iPDPChannel0, this.getPDPChannelCurrent(0));
		sensorOut.add(ChiliConstants.iPDPChannel1, this.getPDPChannelCurrent(1));
		sensorOut.add(ChiliConstants.iPDPChannel2, this.getPDPChannelCurrent(2));
		sensorOut.add(ChiliConstants.iPDPChannel3, this.getPDPChannelCurrent(3));
		sensorOut.add(ChiliConstants.iPDPChannel4, this.getPDPChannelCurrent(4));
		sensorOut.add(ChiliConstants.iPDPChannel5, this.getPDPChannelCurrent(5));
		sensorOut.add(ChiliConstants.iPDPChannel6, this.getPDPChannelCurrent(6));
		sensorOut.add(ChiliConstants.iPDPChannel7, this.getPDPChannelCurrent(7));
		sensorOut.add(ChiliConstants.iBatteryVoltage, this.getBatteryVoltage());
		return sensorOut;		
	}

	public void shareIn(Vector<Object> data) {		
	}
}