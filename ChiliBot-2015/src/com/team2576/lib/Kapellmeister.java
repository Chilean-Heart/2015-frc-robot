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
		this.Virtuosen = new Vector<SubComponent>(20, 1);
		this.dataOut = new Vector<Object>(20, 1);
		this.dataDriver = new Vector<Object>(20, 1);
		this.dataSensor = new Vector<Object>(20, 1);
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
	
	//1)Obtiene la informacion del DS y de los sensores
	//2)Le entrega esta informacion a cada subsistema y recibe de este un Vector con la informacion para las salidas
	//3)Envia la informacion para las salidas al encargado de los actuadores.
	public void conduct() {		
		dataDriver = driverIn.shareOut();
		dataSensor = sensorIn.shareOut();		
		for(int i = 0 ; i < this.Virtuosen.size(); i++) {
			dataOut.add(((SubComponent) this.Virtuosen.elementAt(i)).update(dataDriver, dataSensor));			
		}		
		robotOut.shareIn(dataOut);		
		dataOut.clear();
	}
	
	//Disablea los subsistemas
	public void silence() {
		for(int i = 0 ; i < this.Virtuosen.size(); i++) {
			((SubComponent) this.Virtuosen.elementAt(i)).disable();
		}
	}
	
	//Agrega Subsistemas al conductor
	public void addTask(SubComponent component) {
		this.Virtuosen.addElement(component);
	}
}
