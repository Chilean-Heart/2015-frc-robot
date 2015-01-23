package com.team2576.robot.io;

/**
*
* @author Lucas
*/

public interface IOComponent {
	public Object[] shareOut();
	public void shareIn(Object[] data);
}
