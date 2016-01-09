package com.team2576.robot;

/**
*
* @author Lucas
*/

import com.team2576.auto.Maestro;
import com.team2576.lib.AutoRecorder;
import com.team2576.lib.Debugger;
import com.team2576.lib.Kapellmeister;
import com.team2576.lib.Logger;
import com.team2576.lib.VisionServer;
import com.team2576.lib.VisionServer.GameMode;
import com.team2576.lib.util.ChiliConstants;
import com.team2576.robot.io.DriverInput;
import com.team2576.robot.io.RobotOutput;
import com.team2576.robot.io.SensorInput;
import com.team2576.robot.subsystems.ChiliDrive;
import com.team2576.robot.subsystems.Toter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ChiliRobot extends IterativeRobot {
	
	/*
	 * Declaration of manager objects.
	 * 
	 * Declaracion de objectos administradores. 
	 */
	
	Kapellmeister kapellmeister;
	//Maestro maestro;
	
	/*
	 * Declaration of subsystems.
	 * 
	 * Declaracion de subsistemas.
	 */
	
	ChiliDrive chassis;
	Toter stacker;
	
	/*
	 * Declaration of I/O objects.
	 * 
	 * Declaracion de objetos I/O.
	 */
	
	SensorInput sensor;
	DriverInput driver;
	RobotOutput output;
	
	/*
	 * Declaration of lib objects.
	 * 
	 * Declaracion de objectos lib. 
	 */
	
	VisionServer jetson;
	Debugger messenger;
	Logger loggy;
	AutoRecorder recorder;	
	
	/*
	 * State variables.
	 * 
	 * Variables de estado.
	 */
	private boolean teleop_first_time;
	private boolean auto_finished = false;
	private double auto_timer;
	public static boolean table_init = false, vision_systems = true;
	
	
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#robotInit()
     */
    public void robotInit() {    
    	
    	/*
    	 * Initialization of state variables.
    	 * 
    	 * Inicializacion de variables de estado.
    	 */
    	this.teleop_first_time = true;
    	this.auto_finished = false;
    	
    	/*
    	 * Initialization of objects. Instead of calling the class' constructor, which is private and static to assure that there shall only 
    	 * be one instance of each object, we call a public method, getInstance(), which returns a private static instance of the object. In the
    	 * case that this function is called more than once, even in other classes, getInstance() will only return an unique instance, which 
    	 * shall be shared between all the clases which have an object of said class.
    	 * 
    	 * Inicializacion de objetos. En vez de llamar el constructor de cada clase, el cual es privado y estatico para asegurar la existencia
    	 * de una unica instancia de cada objeto, llamamos un metodo publico, getInstance(), el cual retorno una instance privada y estatica del
    	 * objeto. En caso de llamar mas de una vez a esta funcion, aunque sea en otras clases, getInstance() solo retornara un unica instancia,
    	 * la cual sera compartida entre todas las clases que tengan un objeto de dicha clase.
    	 */
    	kapellmeister = Kapellmeister.getInstance();
    	//maestro = Maestro.getInstance();
		chassis = ChiliDrive.getInstance();
		stacker = Toter.getInstance();
		//jetson = VisionServer.getInstance();
		loggy = Logger.getInstance();
		recorder = AutoRecorder.getInstance();
		sensor = SensorInput.getInstance();
		driver = DriverInput.getInstance();
		output = RobotOutput.getInstance();
		
		
		/*
		 * Initialization of Debugger object.
		 * 
		 * Inicializacion de objeto Debugger.
		 */
		messenger = new Debugger(Debugger.Debugs.MESSENGER, ChiliConstants.kDefaultDebugState);
		
		/*
		 * The corresponding subsystems are added to the Kappelmeister Subsystem Manager. The constants assign the index for each object in the
		 * subsystems vector.
		 * 
		 * Los subsistemas correspondientes son agregeados al Administrador de Subsistemas Kappelmeister. La constante assigna un indice a cada
		 * objeto en el vector de subsistemas.
		 */
    	kapellmeister.addTask(chassis, ChiliConstants.iDriveTrain);
    	kapellmeister.addTask(stacker, ChiliConstants.iStacker);
    	
    	/*
    	 * The NetworkTable for the Jetson TK1 Vision system is initialized.
    	 * 
    	 * Se inicializa la NetworkTable para el sistema de Vision de Jetson TK1. 
    	 */
    	//ChiliRobot.vision_systems = jetson.initializeTable();
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#autonomousInit()
     */
    public void autonomousInit() {
    	
    	/*
    	 * The auto timer is started.
    	 * 
    	 * Se inicia el temporizador de autonomo.
    	 */
    	this.auto_timer = Timer.getFPGATimestamp();
    	
    	/*
    	 * The vision system updates its detection mode.
    	 * 
    	 * Se actualiza el modo de deteccion del sistema de vision. 
    	 */
    	//jetson.setMode(GameMode.AUTO);
    }

    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#autonomousPeriodic()
     */
    public void autonomousPeriodic() {
    	
    	/*
    	 * Autonomous which moves the robot into the auto zone from either the landfill or begin zone.
    	 * 
    	 * Modo autonomo que mueve el robot al interior de la zona autonoma desde cualquier posicion de inicio. 
    	 */

    	/*
    	 * Checks if the auto has finished.
    	 * 
    	 * Revisa si ha terminado el autonomo. 
    	 */
    	if(!auto_finished){
    		
    		while((Timer.getFPGATimestamp() - auto_timer) < 0.4){
	    		output.setAllDrives(0.8);
	    	}
	    	output.setAllDrives(0);
	    	
	    	auto_timer = Timer.getFPGATimestamp();

	    	auto_finished = true;
	    }
    	
    }

    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#teleopInit()
     */
    public void teleopInit() {
    	
    	//Shows the current system time on STDOUT.
    	//Muestra el tiempo actual del sistema en STDOUT.
    	messenger.println("Finished teleopInit in", Timer.getFPGATimestamp());
    	
    	//Opens the robot log file.
    	//Abre el archivo de registro del robot.
    	loggy.openLog();
    	
    	//Starts the autonomous recorder if it enabled.
    	//Inicia el grabador de autonomo en caso que se encuentre habilitado.
    	//if(AutoRecorder.record_enabled) recorder.openRecording();
    	
    	//Updates the vision system mode.
    	//Actualiza el modo del sistema de vision.
    	//jetson.setMode(GameMode.TELE);
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#teleopPeriodic()
     */
    public void teleopPeriodic() {    
    	
    	//On first iteration of the loop, print current system time.
    	//En la primera iteracion del ciclo, imprimir el tiempo actual del sistema.
    	if(this.teleop_first_time) {
    		messenger.println("Made it to the loop in", Timer.getFPGATimestamp());    		
    		this.teleop_first_time = false;
    	}
    	
    	/*
    	 * Main loop of teleop. 3 important tasks happen within the while loop:
    	 * a)The AutoRecorder stores the states of all outputs within a file.
    	 * b)The Logger registers all input and output states to a CSV log file.
    	 * c)The Kappelmeister updates all subsystems contained within the subsystem vector.
    	 * 
    	 * Loop principal de teleop. Suceden 3 tareas importantes al interior del while:
    	 * a)El AutoRecorder almacena todos los estados de salida al interior de un archivo.
    	 * b)El Logger registra todos los estados de entradas y salidas a un archivo CSV.
    	 * c)El Kappelmeister actualiza todos los subsistemas contenidos al interior de su vector de subsistemas.
    	 */
    	while(isOperatorControl() && isEnabled()) {
    		//if(AutoRecorder.record_enabled) recorder.recordAuto();
    		loggy.addLog();
    		kapellmeister.conduct();

    		//Smartdashboard information.
    		//Informacion para la Smartdashboard.
    		SmartDashboard.putNumber("Gyro Angle", kapellmeister.sensorData.getGyroAngle());
    		SmartDashboard.putNumber("Selector", ChiliDrive.selector);
    		SmartDashboard.putNumber("ENC Left", sensor.getLeftEncoderRaw());
        	SmartDashboard.putNumber("ENC Right", sensor.getRightEncoderRaw());
    	}
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#disabledInit()
     */
    public void disabledInit() {    	
    	
    	//Resets state variables.
    	//Resetea variables de estado.
    	this.teleop_first_time = true;
    	this.auto_finished = false;
    	
    	//Handles all objects by pausing or silencing.
    	//Detiene o silencia diversos objetos que corren en el ciclo.
    	//maestro.reset();
    	loggy.closeLog();
    	kapellmeister.silence();
    	//if(AutoRecorder.record_enabled) recorder.closeRecording();
    	
    	//Reset encoder count.
    	//Resetear contador de encoders.
    	sensor.resetLeftEncoder();
    	sensor.resetRightEncoder();
    }
    
    /* (non-Javadoc)
     * @see edu.wpi.first.wpilibj.IterativeRobot#disabledPeriodic()
     */
    public void disabledPeriodic() {
    	//Updates sensor information and resets sensors.
    	//Actualiza informacion de sensores y resetea sensores
    	SmartDashboard.putNumber("ENC Left", sensor.getLeftEncoderRaw());
    	SmartDashboard.putNumber("ENC Right", sensor.getRightEncoderRaw());
    	SmartDashboard.putBoolean("Limit Left", sensor.getLeftLimit());
    	SmartDashboard.putBoolean("Limit Right", sensor.getRightLimit());
    	SmartDashboard.putNumber("Gyro disabled", sensor.getGyroAngle());
    	if(driver.getXboxButtonY()){
    		sensor.gyroInit();
    	}
    }
}