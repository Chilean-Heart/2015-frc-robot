package com.team2576.lib.util;

/**
*
* @author Picken
*/


public class ChiliPID {
	
	private double kP, kI, kD; //Constantes del PID
	private double reference; //Referencia del PID
	private double prev_val, prev_D; //Valores anteriores
	private double error_sum; //Integral del Error
	private double epsilon; //Error maximo
	private double max_out; //Salida Maxima
	private boolean flag_first_cycle; //Bandera para inicializar
	private int cycle_count, min_cycle_count; //Conteo para el Deadband

	//Constructor Vacio
	public ChiliPID() {
		this(0.0, 0.0, 0.0, 0.0);
	}
	
	//Contstructor Completo
	public ChiliPID(double p, double i, double d, double e) {
		this.kP = p;
		this.kD = d;
		this.kI = i;
		this.epsilon = e;
		
		this.reference = 0.0;
		this.flag_first_cycle = true;
		this.max_out = 0.0;
		
		this.cycle_count = 0;
		this.min_cycle_count = 5;
		
	}
	
	//Constructor Resumido
	public ChiliPID(double p, double i, double d){
		this(p,i,d,0.0);
	}
	
	//Setter Parametros
	public void setParameters(double p, double i, double d){
		this.kP = p;
		this.kD = d;
		this.kI = i;
	}
	
	//Setter Deadband
	public void setEpsilon(double e){
		this.epsilon = e;
	}
	
	//Setter Referencia
	public void setReference(double r){
		this.reference = r;
	}
	
	//Setter Salida Maxima
	public void setMaxOut(double max){
		if(max < 0.0) {
	        this.max_out = 0.0;
	    } else if(max > 1.0) {
	        this.max_out = 1.0;
	    } else {
	        this.max_out = max;
	    }
	}
	
	//Setter Ciclos minimos
	public void setMinCycleCount(int count){
		this.min_cycle_count=count;
	}
	
	//Resetear Integrador
	public void resetErrorSum(){
		this.error_sum=0.0;
	}
	
	//Getter Referencia
	public double getReference(){
		return this.reference;
	}
	
	public double calcPID(double currentValue){
		
		double kErr = 0.0;
		double dErr = 0.0;
		double iErr = 0.0;
		
		if(this.flag_first_cycle){
			this.prev_val=currentValue;
			this.flag_first_cycle=false;
		}
		
		//Calculo P
		double error = this.reference-currentValue;
		kErr = this.kP * error;
		
		//Calculo D
		double Delta= currentValue - this.prev_val;
		dErr = this.kD * Delta;
		//Calculo I
		this.error_sum += error;
		iErr = this.kI*this.error_sum;
		
		//Calculo General
		double output =kErr + dErr + iErr;
	
		//Satura
		output = ChiliFunctions.clamp_output(output,this.max_out);
		
		//Guarda Valor actual
		this.prev_val = currentValue;
			
		//Devuelve
		return output;
		
	}
	
	public double calcPIDInc(double currentValue){
		
		double kErr = 0.0;
		double dErr = 0.0;
		double iErr = 0.0;
		
		if(this.flag_first_cycle){
			this.prev_val=currentValue;
			this.flag_first_cycle=false;
		}
		
		//Calculo I
		double error = this.reference-currentValue;
		iErr = this.kI * error;
		
		//Calculo P
		double Delta = currentValue - this.prev_val;
		kErr = this.kP * Delta;
		
		//Calculo D
		double DDelta = Delta - this.prev_D; 
		dErr = this.kD * DDelta;
		
		//Calculo General
		double output =kErr + dErr + iErr;
	
		//Satura
		output = ChiliFunctions.clamp_output(output,this.max_out);
		
		//Guarda Valor actual
		this.prev_val = currentValue;
			
		//Devuelve
		return output;
		
	}
	
	//Revisa si llego suficientemente cerca de la referencia.
	public boolean boomDon() {
		double currentError = Math.abs(this.reference - this.prev_val);
		
		//Suficientemente cerca de la meta
		if(currentError <= this.epsilon){
			this.cycle_count++;
		}
		//Lejos de la meta
		else{
			this.cycle_count = 0;
		}
		
		return this.cycle_count>this.min_cycle_count;
	}
	
	public void resetPrevious() {
		this.prev_D = 0.0;
		this.prev_val = 0.0;
	}
}
