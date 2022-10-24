package Main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
 
	private AudioClip clip;
	
	public static final Sound Shoot = new Sound("/Shoot.wav");
	public static final Sound hit = new Sound("/hit.wav");
	
	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}
		catch(Throwable e) {}
	}
		public void play() {
			try {
				new Thread() {
					public void run(){
						clip.play();
					}
				}.start();
			}
			
			catch(Throwable e) {			
			}
			}
		public void loop() {
			
		}
	}
	
