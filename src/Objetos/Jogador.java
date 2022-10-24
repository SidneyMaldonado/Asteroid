package Objetos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Graficos.Spritesheet;
import Graficos.UI;
import Main.Game;


public class Jogador extends Objetos{
	
	public static boolean right, left;
	public double speed = 1.6;
	public static boolean atirando = false;
	
	public static int life = 5;
	
	public int framesAnimation = 0;
	public int maxFrames = 12;
	public int maxSprites = 3;
	public int curSprite = 0;

	public Jogador(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void tick() {
		if(right) {
			x+=speed;
		}
		if(left) {
			x-=speed;
		}
		if(x>=Game.WIDTH) {
			x= -16;
		}
		else if(x+16 <0) {
			x= Game.WIDTH;
		}
		if(life == 0) {
			life = 5;
			Game.score = 0;
			Game.score = 0;
			Game.objetos = new ArrayList<Objetos>();
			Game.spritesheet = new Spritesheet("/playersheet.png");
			Game.jogador = new Jogador((Game.WIDTH/2)-8,Game.HEIGHT-24,0,0,Objetos.Jogador01);
			Game.objetos.add(Game.jogador);
			Game.ui = new UI();
			Game.GameState = "GAME_OVER";
			return;			
		}
		if(atirando) {
			atirando = false;
			int xx = this.getX()+7;
			int yy = this.getY();
			Tiro tiro = new Tiro(xx,yy,3,3,null);
			Game.objetos.add(tiro);
		}
	
	}
	public void render (Graphics g) {
		framesAnimation++;
		if(framesAnimation == maxFrames) {
		curSprite++;
		framesAnimation = 0;
		if(curSprite == maxSprites) {
		curSprite = 0;
			}
		}
		g.drawImage(Jogador01A[curSprite],this.getX(),this.getY(),null);
	}
	
}
