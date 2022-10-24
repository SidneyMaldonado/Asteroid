package Objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Explosao extends Objetos {
	
	private int frames = 0;
	private int targetFrames = 3;
	private int maxAnimation = 2;
	private int curAnimation = 0;
	public BufferedImage[] explosionSprites = new BufferedImage[3];

	public Explosao(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		explosionSprites[0] = Game.spritesheet.getSprite(0, 48, 16, 16);
		explosionSprites[1] = Game.spritesheet.getSprite(16, 48, 16, 16);
		explosionSprites[2] = Game.spritesheet.getSprite(32, 48, 16, 16);
	
	}
	public void tick() {
		y--;
		frames++;
		if(frames == targetFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation > maxAnimation) {
				Game.objetos.remove(this);
				return;
			}
		}
	}
	public void render (Graphics g){
		g.drawImage(explosionSprites[curAnimation],this.getX(),this.getY(), null);
	}
}
