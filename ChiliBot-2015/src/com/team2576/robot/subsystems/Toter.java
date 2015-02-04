package com.team2576.robot.subsystems;

/**
*
* @author Lucas
*/

import com.team2576.lib.util.ChiliConstants;

public class Toter implements SubComponent{
	
	private static Toter instance;

	public static int totes = 0;
	
	public static Toter getInstance() {
		if(instance == null) {
			instance = new Toter();
		}
		return instance;
	}
	
	private Toter() {
	}



	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}	
}
