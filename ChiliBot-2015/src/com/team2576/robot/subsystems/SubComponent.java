package com.team2576.robot.subsystems;

import com.team2576.robot.io.DriverInput;
import com.team2576.robot.io.SensorInput;


/**
 * The Interface SubComponent. Includes 2 basic functions to implement by all subsystems. All other
 * classes within the com.team2576.robot.subsystems must implement this interface.
 *
 * La Interfaz SubComponent. Incluye 2 funciones basica para ser implementadas por todos los subsistemas.
 * Todas las demas clases contenidas al interior de com.team2576.robot.subsystems deben implementar
 * esta interfaz.
 *
 * @author Lucas
 */

public interface SubComponent {
   
   /**
    * Update the subsystem.
    * 
    * Actualiza el subsistema. Recibe dos objectos de entrada I/O y retorna true si es exitosa.
    *
    * @param driver A driver instance object
    * @param sensor A sensor instance object
    * @return true, if successful
    */
   public boolean update(DriverInput driver, SensorInput sensor);
   
   /**
    * Disable the subsystems. Takes no argument, because all disabling must occur within it.
    */
   public void disable();
}
