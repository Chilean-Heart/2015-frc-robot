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

	public boolean conduct() {
		for (int i = 0; i < this.commands.length; i++) {
			this.all_done[i] = this.commands[i].update();
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
	}
}
