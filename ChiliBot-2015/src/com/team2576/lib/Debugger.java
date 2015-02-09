package com.team2576.lib;

/**
*
* @author Lucas
*/

import java.util.Vector;

public class Debugger {
	
	private String printFlag;
	private boolean debug_on;
	
	public enum Debugs {
		ROBOTOUT,
		DRIVERIN,
		SENSORIN,
		TESTER,
		MESSENGER
	};
	
	public Debugger(Debugger.Debugs type, boolean state) {
		this.debug_on = state;
		switch (type) {
		case ROBOTOUT:
			this.printFlag = "[DEBUGGER: OUTPUT] ";
			break;
		case DRIVERIN:
			this.printFlag = "[DEBUGGER: DINPUT] ";
			break;
		case SENSORIN:
			this.printFlag = "[DEBUGGER: SINPUT] ";
			break;
		case TESTER:
			this.printFlag = "[DEBUGGER: TESTER] ";
			break;
		case MESSENGER:
			this.printFlag = "[DEBUGGER: MESSAGE] ";
			break;
		default:
			break;
		}
	}
	
	public void debugSwitch(boolean state) {
		this.debug_on = state;
	}
	
	public void println(String message) {
		this.debugPrint(message);
	}
	
	public void println(double message) {
		this.debugPrint(Double.toString(message));
	}
	
	public void println(Object message) {
		this.debugPrint(message.toString());
	}
	
	public void println(Vector<Object> message) {
		this.debugPrint(message);
	}
	
	public void println(String message, double val) {
		this.debugPrint(message + ": " + Double.toString(val));
	}
	
	private void debugPrint(String message) {
		if(debug_on) {
			System.out.println(this.printFlag + message);
		}
	}
	
	private void debugPrint(Vector<Object> message) {
		if(debug_on) {
			System.out.println(this.printFlag + message.toString());
		}
	}

}
