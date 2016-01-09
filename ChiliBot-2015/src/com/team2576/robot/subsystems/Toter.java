package com.team2576.robot.subsystems;

/**
* Tote lifting mechanism subsystem.
* 
* Subsistema de mecanismo de alzado de totes.
*
* @author Lucas
*/

import com.team2576.robot.io.*;
import com.team2576.lib.util.ChiliConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Toter implements SubComponent {
	
	private int totes;
	private double lifter_force;
	private boolean toter_error = false;
	private boolean correct = false;
	private boolean first_cycle = true;
	private double time_flag;
	private static Toter instance;
	private RobotOutput output;
	
	private double left_encoder_raw, right_encoder_raw;
	public static Toter getInstance() {
		if(instance == null) {
			instance = new Toter();
		}
		return instance;
	}
	
	/**
	 * Instantiates a new toter class object.
	 * 
	 * Contructor para un nuevo objeto de clase toter.
	 */
	private Toter() {
		
		this.totes = 0;
		this.lifter_force = 0;
		this.left_encoder_raw = 0;
		this.right_encoder_raw = 0;
		time_flag = Timer.getFPGATimestamp();
		output = RobotOutput.getInstance();		
	}
	
	/** NOT IMPLEMENTED
	 * Gets the amount of totes in the lift.
	 * 
	 * Retorna el numero de totes en el ascensor.
	 *
	 * @return the number of totes
	 */
	public int getTotes() {
		return this.totes;
	}

	public boolean update(DriverInput driver, SensorInput sensor) {
		
		//Asumir que 0 es abajo
		//Asume that 0 is the encoder count value at a lower position.
		
		if(first_cycle){
			time_flag = Timer.getFPGATimestamp();
			first_cycle = false;
		}
		
		//Obtain joystick values.
		//Obtener valores de joystick.
		//this.lifter_force = driver.getXboxSecondaryRightY();
		this.lifter_force = driver.getLogitechY();
		this.left_encoder_raw = sensor.getLeftEncoderRaw();
    	this.right_encoder_raw = sensor.getRightEncoderRaw();
    	
    	SmartDashboard.putNumber("CDCH LEFT ENCODER", left_encoder_raw);
    	SmartDashboard.putNumber("CDCH RIGHT ENCODER", right_encoder_raw);
    	
		if(toter_error) {
			return false;
		}
		
		//Calculates values for motors
		//Calcula valor para los motores.
		double dif = Math.abs(left_encoder_raw - right_encoder_raw);
		
		double regulate = 1 - (Math.min(dif, ChiliConstants.kMaxPulse) / ChiliConstants.kMaxPulse);
		regulate *= lifter_force;
		
		double time_dif = Timer.getFPGATimestamp() - time_flag;
		
		
		//Read driver inputs to control lift with manual or automatic levelling controls.
		//Lee entradas del driver para controlar el ascensor de manera manual o con nivelacion automatica.
		if(driver.getXboxSecondaryButtonRightTrigger()) {
			this.output.setLifters(driver.getXboxSecondaryLeftY(), driver.getXboxSecondaryRightY());
			return true;
		}
    	
    	if(sensor.getLeftLimit() && !sensor.getRightLimit() && (time_dif > ChiliConstants.kToterTimeThreshold)) {
    		this.output.setRightLifter(-0.6);
    		this.output.setLeftLifter(0);    		
    		correct = true;
    	} 
    	if(sensor.getRightLimit() && !sensor.getLeftLimit() && (time_dif > ChiliConstants.kToterTimeThreshold)) {    		
    		this.output.setRightLifter(0);
    		this.output.setLeftLifter(-0.6);
    		correct = true;
    	}
    	if(sensor.getRightLimit() && sensor.getLeftLimit()) {
    		time_flag = Timer.getFPGATimestamp();
    		sensor.resetRightEncoder();
    		sensor.resetLeftEncoder();
    		correct = false;
    	} else if (!sensor.getRightLimit() && !sensor.getLeftLimit()) {
    		correct = false;
    	}
		
    	//Corrects the difference of heights between both sides.
    	//Corrige la diferencia de altura entre ambos lados.
    	if(!correct){    		
			if(this.lifter_force > 0){
	        	if(this.left_encoder_raw > this.right_encoder_raw) {
	        		//this.output.setLifters(regulate, this.lifter_force);
	        		this.output.setLifters(regulate, this.lifter_force);
	        		//System.out.println(String.format("%.2f , %.2f - 1", output.getLeftLifterForce(), output.getRightLifterForce()));

	        	}
	        	else if(this.left_encoder_raw < this.right_encoder_raw) {
	        		this.output.setLifters(this.lifter_force, regulate);
	        		
	        		//System.out.println(this.lifter_force + ", " + (-1 * regulate) + " - 2");
	        		//System.out.println(String.format("%.2f , %.2f - 2", (this.lifter_force), (-1 * regulate)));
	        	}
	        	else if(this.left_encoder_raw == this.right_encoder_raw) {
	        		this.output.setLifters(this.lifter_force, this.lifter_force);
	        		
	        		//System.out.println(this.lifter_force + ", " + (-1 * this.lifter_force) + " - 3");
 	        		//System.out.println(String.format("%.2f , %.2f - 3", (this.lifter_force), (-1 * this.lifter_force)));
	        	}
	    	}
	    	else if(this.lifter_force < 0) {
	    		if(this.left_encoder_raw > this.right_encoder_raw) {
	    			this.output.setLifters(this.lifter_force, regulate);
	    			
	    			//System.out.println(this.lifter_force + ", " + (-1 * regulate) + " - 4");
	    			//System.out.println(String.format("%.2f , %.2f - 4", (this.lifter_force), (-1 * regulate)));
	        	}
	        	else if(this.left_encoder_raw < this.right_encoder_raw) {
	        		this.output.setLifters(regulate, this.lifter_force);
	        		
	        		//System.out.println(regulate + ", " + (-1 * this.lifter_force) + " - 5");
	        		//System.out.println(String.format("%.2f , %.2f - 5", regulate, (-1 * this.lifter_force)));
	        	}
	        	else if(this.left_encoder_raw == this.right_encoder_raw) {
	        		this.output.setLifters(this.lifter_force, this.lifter_force);
	        		
	        		//System.out.println(this.lifter_force + ", " + (-1 * this.lifter_force) + " - 6");
	        		//System.out.println(String.format("%.2f , %.2f - 6", (this.lifter_force), (-1 * this.lifter_force)));
	        	}
	    	} else {
	    		this.output.setLifters(0, 0);
	    	}
			/*System.out.println(String.format("LEnc: %f (%b); REnc: %f (%b)", sensor.getLeftEncoderRaw(), sensor.getLeftLimit(), 
					sensor.getRightEncoderRaw(), sensor.getRightLimit()));*/
    	}
    	
		//SmartDashboard.putNumber("Left power", RobotOutput.left_lifter_force);
		//SmartDashboard.putNumber("Right power", RobotOutput.right_lifter_force);
		
		
		return true;
	}
	
	//Stops the motors
	//Detiene los motores
	public void disable() {
		this.toter_error = false;
		this.output.setLifters(0, 0);
	}
}
