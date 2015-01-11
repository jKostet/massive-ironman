package org.ebin.robo;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Cat {
	private DifferentialPilot pilot;
	private TuioListener eyes;
	private Arbitrator arbitrator;


	private final int MOUSE = 84;
	
	class QuitProgram implements Behavior {
		public void action() { System.exit(0); }
		public void suppress() {}
		public boolean takeControl() {
			return Button.ENTER.isPressed();
		}
	}
	
	//Hiiren haku, toimii hyvin kyseenalaisesti
	class SearchForMouse implements Behavior {
		TuioObjectNXT[] targets = new TuioObjectNXT[0];
		
		int turnsLeft = 24;
		
		@Override
		public void action() {
			
			
			if ( turnsLeft == -18 || turnsLeft == 0 ) {
				
				pilot.forward();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				pilot.stop();
			}
			pilot.rotate(15);
			pilot.stop();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			turnsLeft--;
			
			if ( turnsLeft < -19 ) {
				turnsLeft = 24;
			}
		}

		@Override
		public void suppress() {
			pilot.stop();
			
		}

		@Override
		public boolean takeControl() {
			return targets.length == 0;
		} 
		
	}
	
	class Drive implements Behavior {
		TuioObjectNXT[] targets = new TuioObjectNXT[0];
		public void action() {
			TuioObjectNXT target;
			if(targets.length >= 1)
				target = targets[0];
			else return;
			pilot.setTravelSpeed(100);
			Sound.buzz();
			pilot.forward();
		}

		@Override
		public void suppress() {
			pilot.stop();
			
		}

		@Override
		public boolean takeControl() {
			targets = eyes.getObjects();
			if(targets.length == 1) return targets[0].getId() == MOUSE;
			else return false;
		}
	}
	
	class EmergencyStop implements Behavior {
		
		TouchSensor touch = new TouchSensor(SensorPort.S4);
		
		@Override
		public void action() {
			System.exit(0);
		}
		@Override
		public void suppress() {
			System.exit(0);
		}
		@Override
		public boolean takeControl() {
			return touch.isPressed();
		}
	}
	
	//Jos ei muuta tekemistä, pidetään mölyä
	class AyyLmao implements Behavior {
		public void action() {
			Sound.playTone(220, 10);
			while(Sound.getTime() > 0);
			Sound.playTone(247, 10);
			while(Sound.getTime() > 0);
			Sound.playTone(147, 10);
			while(Sound.getTime() > 0);
		}
		public void suppress() {  }
		public boolean takeControl() {
			return true;
		}
	}
	
	//silmät käyntiin
	public void connect () {
		(new Thread(eyes)).start();
		arbitrator.start();
	}
	
	
	public Cat (float wheelDiameter, float trackWidth, DifferentialPilot pilot, int polldelay) {
		this.pilot = pilot;
		eyes = new TuioListener();
		eyes.setPollDelay(polldelay);
		arbitrator = new Arbitrator(new Behavior[]{new AyyLmao(), new SearchForMouse(), new Drive(), new QuitProgram(), new EmergencyStop()});
		
	}
}
