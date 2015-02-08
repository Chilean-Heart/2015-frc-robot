package com.team2576.auto.commands;

import com.team2576.auto.AutoCommands;

public class AutoStop extends AutoCommands{

	public boolean update() {
		super.output.stopAll();
		return true;
	}

}
