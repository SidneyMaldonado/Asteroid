package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pausa {
	
	public static boolean pause = false;
	
	public void tick() {
		
	}
	public void render(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(150,0,0,100));
		g2.fillRect(0,0,200,1000);		
		g.setFont(new Font("arial",Font.BOLD,48));			
		g.setColor(Color.white);
		g.drawString("PAUSA",Game.WIDTH/2-60,Game.HEIGHT/2+10);	
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,24));
		g.drawString("Pressione",Game.WIDTH/2-45,Game.HEIGHT/2+90);
		g.drawString("'ENTER'",Game.WIDTH/2-45,Game.HEIGHT/2+110);
		g.drawString("para Continuar",Game.WIDTH/2-45,Game.HEIGHT/2+130);
	}	
}
