package com.team2576.robot.subsystems;

import java.util.Vector;

/**
*
* @author Lucas
*/

public interface SubComponent {
   public Vector<Object> update(Vector<Object>dataDriver, Vector<Object> dataSensor);
   public void disable();
}
