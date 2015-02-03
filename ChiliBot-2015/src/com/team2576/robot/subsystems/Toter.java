package com.team2576.robot.subsystems;

import java.util.Vector;

import com.team2576.lib.util.ChiliConstants;

public class Toter implements SubComponent{
	
	private static Toter instance;
	private Vector<Object> dataTotes;
	private int totes;
	
	public static Toter getInstance() {
		if(instance == null) {
			instance = new Toter();
		}
		return instance;
	}
	
	private Toter() {
		dataTotes = new Vector<Object>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
		this.totes = 0;
	}

	@Override
	public Vector<Object> update(Vector<Object> dataDriver,
			Vector<Object> dataSensor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}	
}
