package com.team2576.lib;

import java.util.ArrayList;
import java.util.Vector;

import com.team2576.robot.io.*;
import com.team2576.robot.subsystems.SubComponent;

/**
*
* @author Lucas
*/

public class Kapellmeister {
	
	private final Vector<SubComponent> Virtuosen;
	private static Kapellmeister Konzertmeister;
	
	private RobotOutput robotOut;
	private DriverInput driverIn;
	private SensorInput sensorIn;
	
	private Object[] dataDriver, dataSensor;
	private ArrayList<Object> dataOut;
	
	public Kapellmeister() {
		this.Virtuosen = new Vector<SubComponent>();
		this.robotOut = RobotOutput.getInstance();
		this.driverIn = DriverInput.getInstance();
		this.sensorIn = SensorInput.getInstance();
	}
	
	public static Kapellmeister getInstance() {
		if(Konzertmeister == null) {
			Konzertmeister = new Kapellmeister();
		}
		return Konzertmeister;
	}
	
	public void conduct() {
		dataDriver = driverIn.shareOut();
		dataSensor = sensorIn.shareOut();
		for(int i = 0 ; i < this.Virtuosen.size(); i++) {
			//((SubComponent) this.Virtuosen.elementAt(i)).update();
			dataOut.add(((SubComponent) this.Virtuosen.elementAt(i)).update(dataDriver, dataSensor));			
		}
		Object[] dataOutput = new Object[dataOut.size()];
		robotOut.shareIn(dataOutput);
	}
	
	public void silence() {
		for(int i = 0 ; i < this.Virtuosen.size(); i++) {
			((SubComponent) this.Virtuosen.elementAt(i)).disable();
		}
	}
	
	public void addTask(SubComponent component) {
		this.Virtuosen.addElement(component);
	}
}
