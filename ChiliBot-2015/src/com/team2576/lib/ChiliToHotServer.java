package com.team2576.lib;

import com.team2576.lib.util.ChiliConstants;

//NOT IN USE

public class ChiliToHotServer extends Thread{
	
	private Thread thread;
	private String thread_name;
	private Servers server_to_start;
	private Debugger debug;
	
	private volatile boolean running = true;
	private static boolean goodbye_vision = false;
	private boolean client = false;
	
	private ChiliToHotServer(String name, Servers server) {
		this.thread_name = name;
		this.server_to_start = server;
		debug = new Debugger(Debugger.Debugs.MESSENGER, ChiliConstants.kDefaultDebugState);
	}
	
	public void terminateConnectionAttempt() {
		running = false;
	}
	
	public void run() {
		while(running && !client) {
			try {
				client = server_to_start.awaitClient();
				Thread.sleep(ChiliConstants.kRetryConnectionInterval);
			} catch(InterruptedException err) {
				debug.println("Thread " + " failed to load. Manual override ");
				this.terminateConnectionAttempt();
				ChiliToHotServer.setVisionStatus(true);
			}
		}
	}
	
	public void start() {
		if(thread == null) {
			thread = new Thread(this, thread_name);
			thread.start();
		}
	}

	public static boolean getVisionStatus() {
		return goodbye_vision;
	}

	public static void setVisionStatus(boolean state) {
		ChiliToHotServer.goodbye_vision = state;
	}
}
	