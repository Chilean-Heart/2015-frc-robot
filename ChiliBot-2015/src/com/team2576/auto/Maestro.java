package com.team2576.auto;

import java.util.Vector;

import com.team2576.auto.routines.AutoRoutines;
import com.team2576.lib.util.ChiliConstants;

public class Maestro {

	private Vector<AutoRoutines> discipulos;
	private static Maestro Concertista;
	private AutoRoutines auton_mode;
	private AutoCommands[] commands;

	private int auton_selector = 0;
	private boolean[] all_done;
	private boolean finished;

	public static Maestro getInstance() {
		if (Concertista == null) {
			Concertista = new Maestro();
		}
		return Concertista;
	}

	private Maestro() {
		this.discipulos = new Vector<AutoRoutines>(
				ChiliConstants.kStandardVectorSize,
				ChiliConstants.kStandardVectorIncrement);
	}
	
	public void reset() {
		this.finished = false;
		for (int i = 0; i < all_done.length; i++) {
			all_done[i] = false;
		}
	}

	public boolean conduct() {
		for (int i = 0; i < this.commands.length; i++) {
			if(commands[i] instanceof AutoCommands) {
				//System.out.println("Yep");
				this.all_done[i] = this.commands[i].update();
			}
		}
		for (int j = 0; j < this.all_done.length; j++) {
			this.finished = all_done[j];
			if(!this.finished) {
				this.finished = false;
				break;
			}
		}
		return this.finished;
	}

	public void addRoutine(AutoRoutines routine) {
		this.discipulos.add(routine);
	}

	public void setRoutine() {
		this.auton_mode = this.discipulos.elementAt(this.auton_selector);
		this.commands = this.auton_mode.generateSequence();
		this.all_done = new boolean[commands.length];
		for (int i = 0; i < commands.length; i++) {
			System.out.print(this.commands[i] + ", ");
		}
	}
}
