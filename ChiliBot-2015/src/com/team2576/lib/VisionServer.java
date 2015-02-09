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
		for (int i = 0; i < ChiliConstants.iTablesIndex.length; i++) {			
			if (ChiliConstants.iTablesIndexType[i] == ChiliConstants.kIsNumber) {
				double d_temp;
				try{
					d_temp = this.table.getNumber(ChiliConstants.iTablesIndex[i], -1);
					this.data.add(i, d_temp);
				} catch (TableKeyNotDefinedException err) {
					err.printStackTrace();
				}				
			}
			else if (ChiliConstants.iTablesIndexType[i] == ChiliConstants.kIsBoolean) {
				boolean b_temp;
				try{
					b_temp = this.table.getBoolean(ChiliConstants.iTablesIndex[i]);
					this.data.add(i, b_temp);
				} catch (TableKeyNotDefinedException err) {
					err.printStackTrace();
				}
			}
			else if (ChiliConstants.iTablesIndexType[i] == ChiliConstants.kIsString) {
				String s_temp;
				try{
					s_temp = this.table.getString(ChiliConstants.iTablesIndex[i], null);
					this.data.add(i, s_temp);
				} catch (TableKeyNotDefinedException err) {
					err.printStackTrace();
				}				
			}
		}		
		return this.data;		
	}

}
