package Graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import Objetos.Jogador;

public class UI {

	public static BufferedImage HEART = Game.spritesheet.getSprite(0, 16, 16, 16);
	
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,10));
		g.drawString("Score: " +  Game.score, 1, 13);
		
		for(int i=0;i < (int)(Jogador.life); i++) {
			g.drawImage(HEART, 125 + (i*6),5,10,10,null);
		}
		
	}
}
