package com.team2576.lib.sensors;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class MecanumEncoder {
	
	private Counter encoder;
	private final double distance_per_pulse = 1;
	private final double max_period = 0.01;
	
	public MecanumEncoder(int pin) {
		encoder = new Counter(new DigitalInput(pin));
		encoder.setDistancePerPulse(this.distance_per_pulse);
		encoder.setMaxPeriod(this.max_period);
	}
	
	public MecanumEncoder(int pin, double dist, double period) {
		encoder = new Counter(new DigitalInput(pin));
		encoder.setDistancePerPulse(dist);
		encoder.setMaxPeriod(period);
	}
	
	public void setDistance(double dist) {
		encoder.setDistancePerPulse(dist);
	}
	
	public void setMaxPeriod(double max_period) {
		encoder.setMaxPeriod(max_period);
	}
	
	public double getCount() {
		return encoder.get();
	}
	
	public double getRate() {
		return encoder.getRate();
	}
	
	public double getDistance() {
		return encoder.getDistance();
	}
	
	public double getPeriod() {
		return encoder.getPeriod();
	}

}
