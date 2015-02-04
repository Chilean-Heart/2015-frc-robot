package com.team2576.lib;

import java.util.Vector;

import com.team2576.lib.Debugger;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.io.*;
import com.team2576.robot.subsystems.SubComponent;

/**
*
* @author Lucas
*/

public class Kapellmeister {
	
	private final Vector<SubComponent> virtuosen;
	private static Kapellmeister Konzertmeister;	
	
	private Kapellmeister() {		
		this.virtuosen = new Vector<SubComponent>(ChiliConstants.kStandardVectorSize, ChiliConstants.kStandardVectorIncrement);
	}
	
	public static Kapellmeister getInstance() {
		if(Konzertmeister == null) {
			Konzertmeister = new Kapellmeister();
		}
		return Konzertmeister;
	}	
	
	public void conduct() {		
		for(int i = 0 ; i < this.virtuosen.size(); i++) {
			((SubComponent) this.virtuosen.elementAt(i)).update();			
		}
	}

	public void silence() {
		for(int i = 0 ; i < this.virtuosen.size(); i++) {
			((SubComponent) this.virtuosen.elementAt(i)).disable();
		}		
	}	

	public void addTask(SubComponent component, byte index) {
		this.virtuosen.add(index, component);		
	}
}
