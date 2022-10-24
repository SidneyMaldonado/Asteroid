package Main;

import Objetos.Inimigo;
import Objetos.Objetos;

public class Enemyspawn {
	
	public int targetTime = 45;
	public int curTime= 0;
	
	public void tick() {
		curTime++;
		if(curTime == targetTime) {
			curTime =0;
			int yy = 0;
			int xx = Objetos.rand.nextInt(Game.WIDTH-16);
			Inimigo inimigo = new Inimigo(xx,yy,16,16,Game.spritesheet.getSprite(0, 32, 16, 16));
			Game.objetos.add(inimigo);
			
		}
		
	}

}
