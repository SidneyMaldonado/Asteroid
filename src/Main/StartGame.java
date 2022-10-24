package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class StartGame {
	
	private boolean showMessageIniciar = true;
	private int framesIniciar = 0;
	
	static boolean startGame = false;
	
	public void tick() {
		Game.ENTER = true;
		startGame = false;
	}
	
	public void render (Graphics g) {
		if(Game.GameState == "STARTGAME") {
			//8 Game Over
			this.framesIniciar++;
			if(this.framesIniciar== 30) {
				this.framesIniciar = 0;
				if(this.showMessageIniciar)
					this.showMessageIniciar = false;
					else
						this.showMessageIniciar = true;
				}		
			Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(0,0,0));
				g2.fillRect(0,0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
				
				g.setFont(new Font("arial",Font.BOLD,56));			
				g.setColor(Color.white);
				g.drawString("ASTEROID",Game.WIDTH/2-5,Game.HEIGHT/2+170);
				g.drawString("DESTRUCT",Game.WIDTH/2+15,Game.HEIGHT/2+220);
				
				g.setFont(new Font("arial",Font.BOLD,28));
				if(showMessageIniciar)
				g.drawString("Pressione 'ENTER' para iniciar",Game.WIDTH/2-45,Game.HEIGHT/2+290);
				
				g.setFont(new Font("arial",Font.BOLD,16));
				g.drawString("Produzido por 'Leandro Massarico'",Game.WIDTH/2+30,Game.HEIGHT/2+500);
			}
		}

}
