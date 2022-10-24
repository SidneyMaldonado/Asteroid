package Objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Tiro extends Objetos {
	
	public double speed = 3;

	public Tiro(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void tick() {
		y-=speed;
		if(y<0) {
			Game.objetos.remove(this);
			return;
		}
	}
		public void render(Graphics g) {
			g.setColor(Color.yellow);
			g.fillOval(this.getX(), this.getY(), width, height);
	}
}
