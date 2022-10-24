package Objetos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Main.Game;

public class Objetos {
	
	public static BufferedImage Jogador01 = 
			Game.spritesheet.getSprite(0, 0, 16, 16);
	
	public static BufferedImage[] Jogador01A = {
			Game.spritesheet.getSprite(0, 0, 16, 16),
			Game.spritesheet.getSprite(16, 0, 16, 16),
			Game.spritesheet.getSprite(32, 0, 16, 16)};
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	
	public static Random rand = new Random();
	
	public Objetos(double x,double y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	public void setX(int newX) {
		this.x = newX;
	}
	public void setY(int newY) {
		this.y = newY;
	}
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getHeight() {
		return (int)this.height;
	}
	public int getWight() {
		return (int)this.width;
	}
	public static boolean isCollidding(Objetos e1, Objetos e2) {
		Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.getWight(), e1.getHeight());
		Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.getWight(), e2.getHeight());
	return e1Mask.intersects(e2Mask);
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(),null);
	}
}
