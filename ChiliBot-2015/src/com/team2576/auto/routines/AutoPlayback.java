package com.team2576.auto.routines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.team2576.auto.AutoCommands;
import com.team2576.auto.AutoGenerator;
import com.team2576.auto.commands.drive.DriveFrontLeftArray;
import com.team2576.auto.commands.drive.DriveRearLeftArray;
import com.team2576.auto.commands.drive.DriveFrontRightArray;
import com.team2576.auto.commands.drive.DriveRearRightArray;
import com.team2576.lib.util.ChiliFunctions;

public class AutoPlayback implements AutoRoutines {
	
	private final String directory = "/home/lvuser/autos/";
	private FileReader autoFile;
	private BufferedReader reader;
	private String line;
	private File[] files;
	private double[][] data;
	private ArrayList<double[]> autoData = new ArrayList<double[]>();
	private int autoIndex = 0, autosCount = 0;
	
	//private String loggables = "time,frontLeftForce,rearLeftForce,frontRightForce,rearRightForce,winchForce,batteryVoltage";
	
	public AutoPlayback() {	
		
		File dir = new File(directory);
		if(!dir.exists()){
			dir.mkdir();
		}
		files = new File(directory).listFiles();
		if(files != null) {
			for(File file : files) {
				if(file.isFile()) {
					autosCount++;
				}
			}
		}		
	}
	
	/*To print data
	for (int j = 0; j < autoData.size(); j++) {
		System.out.println(Arrays.toString(autoData.get(j)));
	}*/
	
	public void selectAutoMode(int selection) {
		
		if(selection <= this.autosCount) {
			autoIndex = selection;
			try {
				autoFile = new FileReader(directory + files[autoIndex].toString());
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
	
	public void readFile() {
		
		try {
			reader = new BufferedReader(autoFile);
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
		data = ChiliFunctions.transpose(autoData);
	}
	
	

	public AutoCommands[] generateSequence() {
		AutoGenerator commands = new AutoGenerator();
		commands.addCommand(new DriveFrontLeftArray(data[0]));
		commands.addCommand(new DriveRearLeftArray(data[1]));
		commands.addCommand(new DriveFrontRightArray(data[2]));
		commands.addCommand(new DriveRearRightArray(data[3]));
		return commands.generateRoutine();
	}

}
 