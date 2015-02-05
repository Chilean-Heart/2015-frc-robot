package com.team2576.robot.subsystems;

import com.team2576.robot.io.DriverInput;
import com.team2576.robot.io.SensorInput;

/**
*
* @author Lucas
*/

public interface SubComponent {
   public boolean update(DriverInput driver, SensorInput sensor);
   public void disable();
}
