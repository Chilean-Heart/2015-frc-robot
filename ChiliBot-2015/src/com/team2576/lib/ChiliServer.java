package com.team2576.lib;

import java.util.Vector;

import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.TableKeyExistsWithDifferentTypeException;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

//NOT IN USE

/*//Put default values to SmartDashboard so fields will appear
SmartDashboard.putNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
SmartDashboard.putNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
SmartDashboard.putNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
SmartDashboard.putNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
SmartDashboard.putNumber("Tote val min", TOTE_VAL_RANGE.minValue);
SmartDashboard.putNumber("Tote val max", TOTE_VAL_RANGE.maxValue);
SmartDashboard.putNumber("Area min %", AREA_MINIMUM);
}

public void autonomous() {
while (isAutonomous() && isEnabled())
{
	//read file in from disk. For this example to run you need to copy image20.jpg from the SampleImages folder to the
	//directory shown below using FTP or SFTP: http://wpilib.screenstepslive.com/s/4485/m/24166/l/282299-roborio-ftp
	NIVision.imaqReadFile(frame, "/home/lvuser/SampleImages/image20.jpg");

	//Update threshold values from SmartDashboard. For performance reasons it is recommended to remove this after calibration is finished.
	TOTE_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
	TOTE_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
	TOTE_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
	TOTE_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
	TOTE_VAL_RANGE.minValue = (int)SmartDashboard.getNumber("Tote val min", TOTE_VAL_RANGE.minValue);
	TOTE_VAL_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote val max", TOTE_VAL_RANGE.maxValue);*/

public class ChiliServer {
	
	private NetworkTable n_table;
	//private String name_of_table;
	public static Vector<NetworkTable> tables;
	
	public ChiliServer(String name) {
		//this.name_of_table = name;
		this.n_table = NetworkTable.getTable(name);
		ChiliServer.tables.add(this.n_table);
	}
	
	public boolean startTable(ChiliServer table) {
		boolean success = true;
		try{
			table.putBooleanValue(ChiliConstants.kStartKey, true);
		} catch (TableKeyExistsWithDifferentTypeException err) {
			err.printStackTrace();
			success = false;
		}
		return success;
	}
	
	public void updateTable(ChiliServer tableFrom, ChiliServer tableTo) {
		for (int i = 0; i < ChiliConstants.iTablesIndex.length; i++) {
			if (ChiliConstants.iTablesIndexType[i] == ChiliConstants.kIsNumber) {
				double d_temp;
				try{
					d_temp = tableFrom.getNumberValue(ChiliConstants.iTablesIndex[i]);
					try {
						tableTo.putNumberValue(ChiliConstants.iTablesIndex[i], d_temp);
					} catch (TableKeyExistsWithDifferentTypeException err) {
						err.printStackTrace();
					}
				} catch (TableKeyNotDefinedException err) {
					err.printStackTrace();
				}
			}
			else if (ChiliConstants.iTablesIndexType[i] == ChiliConstants.kIsBoolean) {
				boolean b_temp;
				try{
					b_temp = tableFrom.getBooleanValue(ChiliConstants.iTablesIndex[i]);
					try {
						tableTo.putBooleanValue(ChiliConstants.iTablesIndex[i], b_temp);
					} catch (TableKeyExistsWithDifferentTypeException err) {
						err.printStackTrace();
					}
				} catch (TableKeyNotDefinedException err) {
					err.printStackTrace();
				}
			}
			else if (ChiliConstants.iTablesIndexType[i] == ChiliConstants.kIsString) {
				String s_temp;
				try{
					s_temp = tableFrom.getStringValue(ChiliConstants.iTablesIndex[i]);
					try {
						tableTo.putStringValue(ChiliConstants.iTablesIndex[i], s_temp);
					} catch (TableKeyExistsWithDifferentTypeException err) {
						err.printStackTrace();
					}
				} catch (TableKeyNotDefinedException err) {
					err.printStackTrace();
				}				
			}
		}
	}
	
	//REMEMBER TO ADD TRY/CATCH WHEN CALLING THESE FUNCTIONS
	//Error: TableKeyNotDefinedException
	public double getNumberValue(String key) {
		return n_table.getNumber(key);
	}
	
	public boolean getBooleanValue(String key) {
		return n_table.getBoolean(key);
	}
	
	public String getStringValue(String key) {
		return n_table.getString(key);
	}
	
	//REMEMBER TO ADD TRY/CATCH WHEN CALLING THESE FUNCTIONS
	//Error: TableKeyExistsWithDifferentTypeException
	public void putNumberValue(String key, double value) {
		n_table.putNumber(key, value);
	}
	
	public void putBooleanValue(String key, boolean value) {
		n_table.putBoolean(key, value);
	}
	
	public void putStringValue(String key, String value) {
		n_table.putString(key, value);
	}
}
