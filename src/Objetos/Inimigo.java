package Objetos;

import java.awt.image.BufferedImage;

import Main.Game;
import Main.Sound;

public class Inimigo extends Objetos{
	
	public double speed = Objetos.rand.nextInt(2-1)+1;
	
	public int life = 1;

	public Inimigo(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	
	}
	public void tick() {
		y+=speed;
		if(y >=Game.HEIGHT) {
			Game.objetos.remove(this);
			Jogador.life -=1;
			return;
		}
		for ( int i = 0; i < Game.objetos.size(); i++) {
			Objetos e = Game.objetos.get(i);
			if(e instanceof Tiro) {
				if(Objetos.isCollidding(this, e)) {
					life--;
					Game.objetos.remove(e);
					if(life == 0) {
					Explosao explosao = new Explosao(x,y,16,16,null);
					Game.objetos.add(explosao);
					Game.score += 100;
					Game.objetos.remove(this);
					Sound.hit.play();						
						return;
					}
				}
			}
	}
	}
}
