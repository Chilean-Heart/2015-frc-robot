package com.team2576.lib.util;

public class ChiliPID {
	
	private double kP, kI, kD;
	private double desired_val, prev_val;
	private double error_sum, error_increment, error_epsilon;
	private double accept_range;
	private double max_out;
	private boolean flag_first_cycle;
	private int cycle_count, min_cycle_count;
}
