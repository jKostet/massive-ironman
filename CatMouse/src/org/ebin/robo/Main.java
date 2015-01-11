package org.ebin.robo;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cat kissa = new Cat(5.6f, 11f, new DifferentialPilot(5.6f,11f, Motor.A, Motor.B), 50);
		
		
		Button.LEFT.waitForPress();
		
		kissa.connect();

	}
}
