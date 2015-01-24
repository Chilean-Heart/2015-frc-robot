package com.team2576.lib;

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
	
	private Vector<Object> dataOut, dataDriver, dataSensor;
	
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
		
		robotOut.shareIn(dataOut);		
		dataOut.clear();
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
