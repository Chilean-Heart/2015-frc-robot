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
		sensorOut = new Vector<Object>(20, 1);
		
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
	
	private double getPDPChannelCurrent(byte channel) {
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

	public Vector<Object> shareOut() {
		sensorOut.clear();
		sensorOut.add(ChiliConstants.kGyroAngle, this.getGyroAngle());
		sensorOut.add(ChiliConstants.kGyroRate, this.getGyroRate());
		sensorOut.add(ChiliConstants.kTopLimitState, this.getTopLimit());
		sensorOut.add(ChiliConstants.kBotLimitState, this.getBotLimit());
		sensorOut.add(ChiliConstants.kPDPTemp, this.getPDPTemp());
		sensorOut.add(ChiliConstants.kPDPTotalCurrent, this.getPDPTotalCurrent());
		sensorOut.add(ChiliConstants.kPDPTotalEnergy, this.getPDPTotalEnergy());
		sensorOut.add(ChiliConstants.kPDPTotalPower, this.getPDPTotalPower());
		return sensorOut;		
	}

	public void shareIn(Vector<Object> data) {		
	}
}