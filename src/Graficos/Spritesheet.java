package Graficos;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	private BufferedImage spritesheet;
	
	public Spritesheet(String path) {
		
		try {
			String currentPath = new java.io.File(".").getCanonicalPath()+"/res/"+path;
			spritesheet = ImageIO.read(new File(currentPath));
		} catch (IOException e) {
			System.out.println(getClass().getResource(path));
			e.printStackTrace();
			}
				
	}
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}
