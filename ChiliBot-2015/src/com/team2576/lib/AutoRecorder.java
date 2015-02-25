package com.team2576.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.team2576.robot.io.RobotOutput;
import com.team2576.robot.io.SensorInput;

public class AutoRecorder {
	
	public static boolean record_enabled = false;
	private int index;
	
	private String directory = "/home/lvuser/autos";
	private String file_path;
	private RobotOutput output;
	private SensorInput sensor;
	private Date logger_time;
	private SimpleDateFormat time_format;
	private BufferedWriter writer;
	
	//private String loggables = "time,frontLeftForce,rearLeftForce,frontRightForce,rearRightForce,winchForce,batteryVoltage";
	
	private static AutoRecorder instance;
	
	public static AutoRecorder getInstance() {
		if(instance == null) {
			instance = new AutoRecorder();
		}
		return instance;
	}
	
	private AutoRecorder() {
		this.logger_time = new Date();
		this.time_format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS");
		this.output = RobotOutput.getInstance();
		this.sensor = SensorInput.getInstance();
		
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
	
	private String generateTimeStamp(Date time) {
		time = null;
		time = new Date();
		return (this.time_format.format(time)).toString();
	}
	
	private String generatePath() {
		return String.format("%s/%d_auto.csv", this.directory, this.index);
	}
	
	public void openRecording() {
		try {
			this.file_path = this.generatePath();
			this.writer = new BufferedWriter(new FileWriter(this.file_path));
			//this.writer.write(this.loggables);
			//this.writer.newLine();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	public boolean recordAuto() {
		boolean successful;
		try {
			//TODO get all buttons values in a array within DriverInput and print to writer with Arrays.toString(array);
			this.writer.write(String.format("%s", this.generateTimeStamp(this.logger_time) ));
			this.writer.write(String.format(",%.2f", this.output.getForces(0) ));
			this.writer.write(String.format(",%.2f", this.output.getForces(1) ));
			this.writer.write(String.format(",%.2f", this.output.getForces(2) ));
			this.writer.write(String.format(",%.2f", this.output.getForces(3) ));
			this.writer.write(String.format(",%.2f", this.output.getLeftLifterForce() ));
			this.writer.write(String.format(",%.2f", this.output.getRightLifterForce() ));
			this.writer.write(String.format(",%.2f", this.sensor.getBatteryVoltage() ));
			this.writer.newLine();
			successful = true;
		} catch (IOException err) {
			successful = false;
			err.printStackTrace();
		}
		return successful;
	}
	
	public boolean closeRecording() {
		if(this.writer != null) {
			try {
				this.writer.close();
			} catch (IOException err) {
				err.printStackTrace();
				return false;
			}
			return false;
		}
		return true;
	}
	
}
