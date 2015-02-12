package com.team2576.auto.routines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.team2576.auto.AutoCommands;
import com.team2576.auto.AutoGenerator;

public class AutoPlayback implements AutoRoutines {
	
	private final String directory = "/home/lvuser/autos/";
	private String autoFile = "";
	private BufferedReader reader;
	private String line;
	private ArrayList<double[]> autoData = new ArrayList<double[]>();
	
	//private String loggables = "time,frontLeftForce,rearLeftForce,frontRightForce,rearRightForce,winchForce,batteryVoltage";
	
	public AutoPlayback() {	
		
		try {
			reader = new BufferedReader(new FileReader(directory + autoFile));
			while((line = reader.readLine()) != null) {
				String[] vals = line.split(",");
				double [] dvals = new double[vals.length];
				for (int i = 1; i < vals.length; i++) {
					dvals[i] = Double.parseDouble(vals[i]);
				}
				autoData.add(dvals);
			}
		} catch (IOException err) {
			err.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		}
	}
	
	/*To print data
	for (int j = 0; j < autoData.size(); j++) {
		System.out.println(Arrays.toString(autoData.get(j)));
	}*/
	
	

	public AutoCommands[] generateSequence() {
		AutoGenerator commmands = new AutoGenerator();
		
		return commmands.generateRoutine();
	}

}
 