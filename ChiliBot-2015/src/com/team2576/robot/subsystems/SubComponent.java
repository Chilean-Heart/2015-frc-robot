package com.team2576.robot.subsystems;

/**
*
* @author Lucas
*/

public interface SubComponent {
   public Object[] update(Object[] dataDriver, Object[] dataSensor);
   public void disable();
}
