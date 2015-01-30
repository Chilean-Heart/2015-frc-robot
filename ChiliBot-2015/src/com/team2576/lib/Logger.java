package com.team2576.lib;

/**
*
* @author Lucas
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.team2576.lib.util.ChiliFunctions;
import com.team2576.robot.ChiliConstants;

import edu.wpi.first.wpilibj.DriverStation;

public class Logger {
	
	private String directory = "/logs";
	private String file_path;
	private Date logger_time;
	private SimpleDateFormat time_format;
	private BufferedWriter writer;
	private int index;
	private static Logger instance;
	private DriverStation driver;
	
	private String loggables = "time,frontLeftForce,rearLeftForce,frontRightForce,rearRightForce,winchForce,"
							 + "batteryVoltage,pdpTemp,pdpTotalCurrent,current0,current1,current2,current3,"
							 + "current4,current5,current6,current7,";
	
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	private Logger() {
		this.driver = DriverStation.getInstance();
		this.logger_time = new Date();
		this.time_format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS");
		
		File dir = new File(this.directory);
		if(!dir.exists()) {
			dir.mkdir();
		}
		File[] files = new File(directory).listFiles();
		if(files != null) {
			for(File file : files) {
				if(file.isFile()) {
					try {
						int count = Integer.parseInt(file.getName().split("_")[0]);
						if (count > this.index) {
							this.index = count;
						}
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
			}
		} else {
			this.index = 0;
		}
	}
	
	public boolean addLog(Vector<Object> dataDriver, Vector<Object> dataSensor, Vector<Object> dataOut) {
		boolean successful;
		try {
			this.writer.write(String.format("%s", this.generateTimeStamp(this.logger_time)));
			this.writer.write(String.format(",%.2f", ((double[]) ChiliFunctions.doubleDimensionVectorValue(ChiliConstants.iDriveTrain, ChiliConstants.iPatoDriveForces, dataOut))[0]));
			this.writer.write(String.format(",%.2f", ((double[]) ChiliFunctions.doubleDimensionVectorValue(ChiliConstants.iDriveTrain, ChiliConstants.iPatoDriveForces, dataOut))[1]));
			this.writer.write(String.format(",%.2f", ((double[]) ChiliFunctions.doubleDimensionVectorValue(ChiliConstants.iDriveTrain, ChiliConstants.iPatoDriveForces, dataOut))[2]));
			this.writer.write(String.format(",%.2f", ((double[]) ChiliFunctions.doubleDimensionVectorValue(ChiliConstants.iDriveTrain, ChiliConstants.iPatoDriveForces, dataOut))[3]));
			this.writer.write(String.format(",%.2f", ChiliConstants.kEmptyLoggerValue));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iBatteryVoltage)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPTemp)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPTotalCurrent)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel0)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel1)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel2)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel3)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel4)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel5)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel6)));
			this.writer.write(String.format(",%.2f", (double) dataSensor.elementAt(ChiliConstants.iPDPChannel7)));
			this.writer.newLine();
			successful = true;
		} catch (IOException err) {
			successful = false;
			err.printStackTrace();
		}
		return successful;
	}
	
	public void openLog() {
		try {
			this.file_path = this.generatePath();
			this.writer = new BufferedWriter(new FileWriter(this.file_path));
			this.writer.write(this.loggables);
			this.writer.newLine();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	public void closeLog() {
		if(this.writer != null) {
			try {
				this.writer.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
	
	private String generatePath() {		
		if(this.driver.isFMSAttached()) {
			return String.format("%s/%d_%s_%s_position%d_log.txt", this.directory, ++this.index,
					this.generateTimeStamp(this.logger_time), this.driver.getAlliance().toString(),
					this.driver.getLocation());
		} 
		return String.format("%s/%d_%s_log.txt", this.directory, ++this.index, this.generateTimeStamp(this.logger_time));
	}

	private String generateTimeStamp(Date time) {
		time = null;
		time = new Date();
		return (this.time_format.format(time)).toString();
	}
}
