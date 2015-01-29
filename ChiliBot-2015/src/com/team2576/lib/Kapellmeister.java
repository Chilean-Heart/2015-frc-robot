package com.team2576.lib;

import java.util.Vector;

import com.team2576.robot.ChiliConstants;
import com.team2576.robot.io.*;
import com.team2576.robot.subsystems.SubComponent;

/**
*
* @author Lucas
*/

public class Kapellmeister {
	
	private final Vector<SubComponent> virtuosen;
	private static Kapellmeister Konzertmeister;
	
	private RobotOutput robotOut;
	private DriverInput driverIn;
	private SensorInput sensorIn;
	private Logger logger;
	
	private Vector<Object> dataOut, dataDriver, dataSensor;
	
	public Kapellmeister() {
		this.virtuosen = new Vector<SubComponent>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		this.dataOut = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		this.dataDriver = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		this.dataSensor = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		this.robotOut = RobotOutput.getInstance();
		this.driverIn = DriverInput.getInstance();
		this.sensorIn = SensorInput.getInstance();
		this.logger = Logger.getInstance();
		this.logger.openLog();
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
		for(int i = 0 ; i < this.virtuosen.size(); i++) {
			dataOut.add(((SubComponent) this.virtuosen.elementAt(i)).update(dataDriver, dataSensor));			
		}		
		robotOut.shareIn(dataOut);	
		logger.addLog(dataDriver, dataSensor, dataOut);
		dataOut.clear();
	}
	
	//Disablea los subsistemas
	public void silence() {
		for(int i = 0 ; i < this.virtuosen.size(); i++) {
			((SubComponent) this.virtuosen.elementAt(i)).disable();
		}
		this.logger.closeLog();
	}
	
	//Agrega Subsistemas al conductor
	public void addTask(SubComponent component, byte index) {
		this.virtuosen.add(index, component);		
	}
}
