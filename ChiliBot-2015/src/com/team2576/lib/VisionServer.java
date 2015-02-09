package com.team2576.lib;

import java.util.Vector;

import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.ChiliRobot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.TableKeyExistsWithDifferentTypeException;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class VisionServer implements Servers{
	
	private static VisionServer instance;
	private NetworkTable table;
	
	private Vector<Object> data;
	
	/**
	 * Gets the single instance of VisionServer.
	 *
	 * @return single instance of VisionServer
	 */
	public static VisionServer getInstance() {
		if(instance == null) {
			instance = new VisionServer(ChiliConstants.kDataTable);
		}
		return instance;
	}

	/**
	 * Instantiates a new vision server.
	 *
	 * @param name the name
	 */
	private VisionServer(String name) {
		if(!ChiliRobot.table_init) {
			NetworkTable.setServerMode();
			NetworkTable.setTeam(ChiliConstants.kTeamNumber);
			ChiliRobot.table_init = true;
		}
		this.table = NetworkTable.getTable(name);
		this.data = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
	}
	
	/**
	 * Initialize table.
	 *
	 * @return true, if successful
	 */
	public boolean initializeTable() {
		try {
			table.putBoolean(ChiliConstants.kStartKey, true);
		} catch (TableKeyExistsWithDifferentTypeException err) {
			err.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean awaitClient() {
		boolean confirmation = false;
		try {
			confirmation =  table.getBoolean(ChiliConstants.kVisionClientConnected, false);
		} catch (TableKeyNotDefinedException err){
			err.printStackTrace();
			confirmation = false;
			return false;
		}
		return confirmation;
	}
	

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Vector<Object> getData() {
		
		this.data.clear();
		try{
			this.data.add(ChiliConstants.iX, this.table.getNumber(ChiliConstants.iTablesIndex[ChiliConstants.iX], -1));
			this.data.add(ChiliConstants.iY, this.table.getNumber(ChiliConstants.iTablesIndex[ChiliConstants.iY], -1));
			this.data.add(ChiliConstants.iN, this.table.getBoolean(ChiliConstants.iTablesIndex[ChiliConstants.iN], false));
			this.data.add(ChiliConstants.iD, this.table.getNumber(ChiliConstants.iTablesIndex[ChiliConstants.iD], -1));
		} catch (TableKeyNotDefinedException err) {
			err.printStackTrace();
		}		
		return this.data;		
	}
	
	public double getX() {
		return (double) this.data.elementAt(ChiliConstants.iX);
	}
	
	public double getY() {
		return (double) this.data.elementAt(ChiliConstants.iY);
	}
	
	public double getDist() {
		return (double) this.data.elementAt(ChiliConstants.iD);
	}
	
	public boolean getNewCentroid() {
		return (boolean) this.data.elementAt(ChiliConstants.iN);
	}

}
