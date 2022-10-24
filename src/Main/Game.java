package Main;

	import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Graficos.Spritesheet;
import Graficos.UI;
import Objetos.Jogador;
import Objetos.Objetos;

	public class Game extends Canvas implements Runnable, KeyListener,MouseListener,MouseMotionListener  {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static JFrame frame;
		private Thread thread;
		private boolean isRunning = true;
		public static final int WIDTH = 160;
		public static final int HEIGHT = 240;
		public static final int SCALE = 3;
		
		public static BufferedImage image;
		
		public static Spritesheet spritesheet;

		public static List<Objetos> objetos;
		
		public static Jogador jogador;
		
		public static Enemyspawn enemyspawn;
		
		private BufferedImage Game_Background;
		private BufferedImage Game_Background2;
		public int backy = 0;
		public int backy2 = 160;
		public int backspd = 2;
		
		public static UI ui;
		
		public static int score = 0;
		
		public static String GameState = "STARTGAME";
		private boolean showMessageGameOver = true;
		private int framesGameOver = 0;
		
		static boolean ENTER = false;
		
		public static StartGame startgame;
	
		public static Pausa pausa;
		
		public Game() {
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
			initFrame();
			image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
			
			objetos = new ArrayList<Objetos>();
			spritesheet = new Spritesheet("/playersheet.png");
			
			enemyspawn = new Enemyspawn();
			
			jogador = new Jogador((Game.WIDTH/2)-8,Game.HEIGHT-24,0,0,Objetos.Jogador01);
			objetos.add(jogador);
			
			startgame = new StartGame();
			
			pausa = new Pausa();
			
			ui = new UI();
			
			try {

				String currentPath = new java.io.File(".").getCanonicalPath()+"/res/background.png";

			Game_Background = ImageIO.read(new File(currentPath));
			Game_Background2 = ImageIO.read(new File(currentPath));
			}catch(IOException e) {
				e.printStackTrace();
			}
		
		}
			public void initFrame() {
			frame = new JFrame("Asteroid Destruct");		
			frame.add(this);
			frame.setResizable(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		public synchronized void Start() {
			thread = new Thread(this);
			isRunning = true;
			thread.start();
		}
		
		public synchronized void Stop() {		
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		
		public void tick() {
			if(GameState == "NORMAL") {
				this.ENTER = false;
			for(int i = 0; i < objetos.size(); i++) {
				Objetos e = objetos.get(i);
				e.tick();			
			}
			enemyspawn.tick();
			
			backy-=backspd;
			if(backy+160<=0) {
			backy=160;			
			}
			backy2-=backspd;
			if(backy2+160<=0) {
				backy2=160;
			}
			
			ui.tick();
			}else if(GameState == "GAME_OVER") {
				this.framesGameOver++;
				if(this.framesGameOver == 30) {
					this.framesGameOver = 0;
					if(this.showMessageGameOver)
						this.showMessageGameOver = false;
					else
						this.showMessageGameOver = true;
				}if(ENTER) {
					this.ENTER = false;
					this.GameState = "NORMAL";
				}
			}else if(GameState == "STARTGAME") {
				startgame.tick();
				
			}else if(GameState == "PAUSA") {
				pausa.tick();
			}
			
			}
		
		public void render() {
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			requestFocus();		
			Graphics g = image.getGraphics();
			g.setColor(new Color(0,0,0));
			g.fillRect(0, 0, (Game.WIDTH*Game.SCALE),(Game.HEIGHT*Game.SCALE) );
			
			g.drawImage(Game_Background,0,backy,null);
			g.drawImage(Game_Background2,0,backy2,null);
			//inst�nciar Graf�cos	
			for(int i = 0; i < objetos.size(); i++) {
				Objetos e = objetos.get(i);
				e.render(g);
			}
			ui.render(g);
			//l�gica importante
				
			g.dispose();
			g = bs.getDrawGraphics();
			g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
			
			
			if(GameState == "GAME_OVER") {
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(25,0,100,100));
				g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
				g.setFont(new Font("arial",Font.BOLD,56));
				g.setColor(Color.white);
				g.drawString("Game Over",Game.WIDTH/2+5,Game.HEIGHT/2+200);					
				
				g.setFont(new Font("arial",Font.BOLD,28));
				if(showMessageGameOver)
				g.drawString("Pressione 'ENTER' para reiniciar",Game.WIDTH/2-60,Game.HEIGHT/2+250);
				}
			
			else if(GameState == "STARTGAME") {
				startgame.render(g);
			}
			else if(GameState == "PAUSA") {
				pausa.render(g);
			}
			
			bs.show();
		}
		@Override
		public void run() {
			long lastTime = System.nanoTime();
			double amountofTicks = 60.0;
			double ns = 1000000000 / amountofTicks;
			double delta = 0;
			int frames = 0;
			double timer = System.currentTimeMillis();
			while(isRunning) {
				long now = System.nanoTime();
				delta +=(now - lastTime)/ns;
				lastTime = now;
				if(delta>= 1) {
					tick();
					render();
					frames++;
					delta--;
				}
			if(System.currentTimeMillis()- timer>=1000) {
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			}
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			Jogador.right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			Jogador.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Jogador.atirando = true;
			Sound.Shoot.play();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(GameState == "STARTGAME") {
				Game.ENTER = true;
				GameState = "NORMAL";
			}
			else if(GameState == "GAME_OVER") {
				Game.ENTER = true;
				GameState = "NORMAL";
				}
			}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(Pausa.pause == true) {
				GameState = "PAUSA";
			}
			else if(Pausa.pause == true) {
				GameState = "NORMAL";
			}
			
		}
		
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_D) {
				Jogador.right = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				Jogador.left = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				Jogador.atirando = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(GameState == "STARTGAME") {
					this.ENTER = false;
					GameState = "NORMAL";
				}
				else if(GameState == "GAME_OVER") {
					this.ENTER = false;
					GameState = "NORMAL";
					}
				else if(GameState == "PAUSA") {
					this.ENTER = false;
					GameState = "NORMAL";
				}
				}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				GameState = "PAUSA";
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			Jogador.atirando = true;
			Sound.Shoot.play();
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			Jogador.atirando = false;
			Sound.Shoot.play();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}