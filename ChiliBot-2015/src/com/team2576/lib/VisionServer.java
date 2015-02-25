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
	
	public enum GameMode {
		AUTO,
		TELE
	};
	
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
		this.data.add(ChiliConstants.iX, ChiliConstants.kFrameWidthCenter);
		try {
			table.putBoolean(ChiliConstants.kStartKey, true);
			table.putBoolean(ChiliConstants.kVisionClientConnected, false);
		} catch (TableKeyExistsWithDifferentTypeException err) {
			err.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean awaitClient() {
		boolean confirmation = false;
		try {
			confirmation = table.getBoolean(ChiliConstants.kVisionClientConnected, false);
			//confirmation = true;
		} catch (TableKeyNotDefinedException err){
			err.printStackTrace();
			confirmation = false;
			return confirmation;
		}
		return confirmation;
	}
	
	public boolean setMode(VisionServer.GameMode type) {
		boolean isYellow = (type == VisionServer.GameMode.AUTO) ? true : false;
		try {
			table.putBoolean(ChiliConstants.kAutoMode, isYellow);
		} catch (TableKeyNotDefinedException err) {
			err.printStackTrace();
			return false;
		}		
		return true;
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
		double x;
		try{
			x =  (double) this.data.elementAt(ChiliConstants.iX);
		} catch (TableKeyNotDefinedException err){
			err.printStackTrace();
			x = -1;
		} return x;
	}
	
	public double getY() {
		try{
			return (double) this.data.elementAt(ChiliConstants.iY);
		} catch (TableKeyNotDefinedException err){
			err.printStackTrace();
		} return -1;
	}
	
	public double getDist() {
		try{
			return (double) this.data.elementAt(ChiliConstants.iD);
		} catch (TableKeyNotDefinedException err){
			err.printStackTrace();
		} return -1;
	}
	
	public boolean getNewCentroid() {
		try{
			return (boolean) this.data.elementAt(ChiliConstants.iN);
		} catch (TableKeyNotDefinedException err){
			err.printStackTrace();
		} return false;
	}

}
