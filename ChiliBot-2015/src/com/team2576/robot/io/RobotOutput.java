package com.team2576.robot.io;

import com.team2576.lib.util.ChiliConstants;
import com.team2576.lib.util.ChiliFunctions;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;

/**
*
* @author Lucashhh
*/

public class RobotOutput {
	
	//Global and static instance of the RobotOutput class
	//Instancia global e unica de la clase RobotOutput
	private static RobotOutput instance;
	private SensorInput sensor;
	
	//Motor Controllers for drive and mechanisms
	//Controladores de motor para el chasis y los mecanismos
	private final Talon front_left, rear_left, front_right, rear_right;
	private final Jaguar left_lifter, right_lifter;
	
	private double[] forces = new double[ChiliConstants.kMotorCount];
	public static double left_lifter_force, right_lifter_force;

	//Getter de instancia para asegurarse de la existencia global de un solo objeto
	/**
	 * Gets the single instance of RobotOutput.
	 *
	 * @return single instance of RobotOutput
	 */
	public static RobotOutput getInstance() {
		if(instance == null) {
			instance = new RobotOutput();
		}
		return instance;
	}
	
	//Constructor de la clase para generar objetos de controladores
	/**
	 * Instantiates a new robot output.
	 */
	private RobotOutput() {		
		
		this.forces = ChiliFunctions.fillArrayWithZeros(this.forces);
		
		front_left = new Talon(ChiliConstants.front_left_motor);
		rear_left = new Talon(ChiliConstants.rear_left_motor);
		front_right = new Talon(ChiliConstants.front_right_motor);
		rear_right = new Talon(ChiliConstants.rear_right_motor);
		left_lifter = new Jaguar(ChiliConstants.left_lifter_motor);
		right_lifter = new Jaguar(ChiliConstants.right_lifter_motor);
		
		sensor = SensorInput.getInstance();
		
		/*front_left.setSafetyEnabled(true);
		rear_left.setSafetyEnabled(true);
		front_right.setSafetyEnabled(true);
		rear_right.setSafetyEnabled(true);
		left_lifter.setSafetyEnabled(true);
		right_lifter.setSafetyEnabled(true);*/
	}

	//Metodo para fijar potencia del motor izquierdo delantero
	/**
	 * Sets the front left drive.
	 *
	 * @param x the new front left drive speed
	 */
	public void setFrontLeftDrive(double x) {
		this.front_left.set(x);
	}

	//Metodo para fijar potencia del motor izquierdo trasero
	/**
	 * Sets the rear left drive.
	 *
	 * @param x the new rear left drive speed
	 */
	public void setRearLeftDrive(double x) {
		this.rear_left.set(x);
	}

	//Metodo para fijar potencia del motor derecho delantero
	/**
	 * Sets the front right drive.
	 *
	 * @param x the new front right drive speed
	 */
	public void setFrontRightDrive(double x) {
		this.front_right.set(-x);
	}

	//Metodo para fijar potencia del motor derecho trasero
	/**
	 * Sets the rear right drive.
	 *
	 * @param x the new rear right drive speed
	 */
	public void setRearRightDrive(double x) {
		this.rear_right.set(-x);
	}

	//Metodo para fijar potencia de los motores izquierdos
	/**
	 * Sets the left drive.
	 *
	 * @param x the new left drive speed
	 */
	public void setLeftDrive(double x) {
		this.front_left.set(x);
		this.rear_left.set(x);
	}

	//Metodo para fijar potencia de los motores derecho
	/**
	 * Sets the right drive.
	 *
	 * @param x the new right drive speed
	 */
	public void setRightDrive(double x) {
		this.front_right.set(-x);
		this.rear_right.set(-x);
	}

	//Metodo para fijar velocidades de todos los motores
	/**
	 * Sets the drive speed for all motors
	 *
	 * @param fl the front left motor speed
	 * @param rl the rear left motor speed
	 * @param fr the front right motor speed
	 * @param rr the rear right motor speed
	 */
	public void setDrive(double fl, double rl, double fr, double rr) {
		this.front_left.set(fl);
		this.rear_left.set(rl);
		this.front_right.set(-fr);
		this.rear_right.set(-rr);
	}

	//Metodo para fijar las velocidades de todos los motores del chasis desde un arreglo
	/**
	 * Sets the drive from an array with four doubles.
	 *
	 * @param array the array with speeds {fl, rl, fr, rr}
	 */
	public void setDriveFromArray(double[] array) {
		this.front_left.set(array[0]);
		this.rear_left.set(array[1]);
		this.front_right.set(-1 * array[2]);
		this.rear_right.set(-1 * array[3]);
		this.forces = array.clone();
	}

	//Metodo para fijar la velocidad del winch
	/**
	 * Sets the lifter speed.
	 *
	 * @param x the new lifter speed
	 */
	public void setLifters(double left, double right) {
		this.setLeftLifter(left);
		this.setRightLifter(right);
	}
	
	public void setLeftLifter(double x){
		if(sensor.getLeftLimit() && x < 0) {
			this.left_lifter.set(0);
			this.setLeftLifterForce(0);
		} else {
			this.left_lifter.set(x);
			this.setLeftLifterForce(x);
		}
		
	}
	
	public void setRightLifter(double x){
		if(sensor.getRightLimit() && x < 0) {
			this.right_lifter.set(0);
			this.setRightLifterForce(0);			
		} else {
			this.right_lifter.set(-1 * x);
			this.setRightLifterForce(-1 * x);
		}		
	}
	
	public void setLeftLifterForce(double x){
		RobotOutput.left_lifter_force = x;
	}
	public void setRightLifterForce(double x){
		RobotOutput.right_lifter_force = x;
	}
	
	
	public double getForces(int index) {
		if (index > this.forces.length) {
			return -1;
		}
		return this.forces[index];
	}
	
	public double getLeftLifterForce() {
		return RobotOutput.left_lifter_force;
	}
	public double getRightLifterForce() {
		return RobotOutput.right_lifter_force;
	}
	
	public void stopAll() {
		this.front_left.set(0);
		this.rear_left.set(0);
		this.front_right.set(0);
		this.rear_right.set(0);
		this.left_lifter.set(0);
		this.right_lifter.set(0);
	}
	
	public void setAllDrives(double x) {
		this.front_left.set(x);
		this.rear_left.set(x);
		this.front_right.set(x);
		this.rear_right.set(x);
	}
}