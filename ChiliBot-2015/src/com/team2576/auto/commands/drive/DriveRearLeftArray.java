package com.team2576.auto.commands.drive;
/**
*
* @author Robocop
*/
import com.team2576.auto.AutoCommands;

public class DriveRearLeftArray extends AutoCommands {
	
	double[] data;
	int index;
	
	public DriveRearLeftArray(double[] data) {
		this.data = data;
		this.index = 1;
		super.first_cycle = true;
	}
	
	public boolean update() {
		
		if(first_cycle) {
			super.start_time = super.getTime();
			super.elapsed_time = this.start_time;
			super.first_cycle = false;
		}
		if(index < data.length) {
			super.output.setRearLeftDrive(data[index]);
			index++;
			return false;
		}
		
		return true;
	}

}