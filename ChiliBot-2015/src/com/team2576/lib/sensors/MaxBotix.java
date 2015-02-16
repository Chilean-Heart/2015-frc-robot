package com.team2576.lib.sensors;

import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.AnalogInput;

public class MaxBotix {
	
	private AnalogInput sensor;
	
	
	public MaxBotix(int port) {
		this.sensor = new AnalogInput(port);
	}
	
	private double getSensorVoltage() {
		return this.sensor.getVoltage();
	}
	
	public double getCentimeters() {
		double voltage = this.getSensorVoltage();
		return voltage / ChiliConstants.kVoltsPerCm;
	}
	
	public double getInches() {
		double voltage = this.getSensorVoltage();
		return voltage / ChiliConstants.kVoltsPerInch;
	}
}
