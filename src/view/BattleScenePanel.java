package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class BattleScenePanel extends JPanel {
	
	final static int topLeftX = 97;
	final static int topLeftY=65;
	final static int height = 4;
	final static int pokemonLength=125;
	final static int pokemonWidth=125;
	final static int pokemonY=20;
	private int pokemonX=500;
	//g.drawImage(trainerBackStanding, 45, 85, 115, 140, null);
	final static int trainerY=85;
	final static int trainerHeight = 140;
	final static int trainerWidth = 115;
	private int pokeballLength = 30;
	private int pokeballWidth = 30;
	private int pokeballX = 100;
	private int pokeBallY = 180;
	private int trainerX=-110;
	private int clicks=0;
	private int healthBarLength;
	private javax.swing.Timer healthBarTimer;
	private javax.swing.Timer startingTimer;
	private javax.swing.Timer pokeballTimer;
	private BufferedImage backGround;
	private BufferedImage trainerBackStanding;
	private BufferedImage spriteSheet;
	private BufferedImage pokemonSpriteSheet;
	private BufferedImage pokemon;
	private BufferedImage pokeball;
	private int index = 0;
	
	public static void main(String[] args){
		
		JFrame frame  = new JFrame();
		frame.setSize(525, 600);
		frame.add(new BattleScenePanel());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public BattleScenePanel(){
		this.setSize(500, 500);
		healthBarLength = 95;
		try {
			backGround = ImageIO.read(new File("src/view/BattleSceneBackground.png"));
			spriteSheet = ImageIO.read(new File("src/view/pokemonSprite.png"));
			pokemonSpriteSheet = ImageIO.read(new File("src/view/PokemonSprites.png"));
			pokeball = spriteSheet.getSubimage(300, 64, 16, 13);
			trainerBackStanding = spriteSheet.getSubimage(175, 180, 55, 50);
			pokemon = pokemonSpriteSheet.getSubimage(735, 400, 65, 75);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		healthBarTimer = new javax.swing.Timer(30, new drawHealthBarListener());
		startingTimer = new javax.swing.Timer(5, new beginningListener());
		pokeballTimer = new javax.swing.Timer(15, new throwBallListener());
		this.addMouseListener(new mouse());
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0	, 500, 300);
		g.setColor(Color.GREEN);
		g.drawImage(backGround, 0, 0, 500, 300, null);
		g.drawImage(trainerBackStanding, trainerX, trainerY, trainerWidth, trainerHeight, null);
		g.drawImage(pokemon, pokemonX, pokemonY, pokemonWidth, pokemonLength, null);
		g.drawImage(pokeball, pokeballX, pokeBallY, pokeballWidth, pokeballLength, null);
		g.fillRect(topLeftX, topLeftY, healthBarLength, height);
	}
	public class mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent m) {
			clicks++;
		if(clicks==1){
			startingTimer.start();
		}
		else if(clicks==2){
			healthBarTimer.start();
			System.out.println("Y: " + m.getY());
			System.out.println("X: " + m.getX());
			}
		else{
			pokeballTimer.start();
		}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
				
	}
	
	public class drawHealthBarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent action) {
			//Replace this with a condition of where the health bar length should sto
			//PROBABLY set it to newHealth/fullHealth (Which would be percentage of the full
			//Health that the pokemon is now at.
			if(healthBarLength ==0){
				healthBarTimer.stop();
			}
			else{
				System.out.println(healthBarLength);
				healthBarLength--;
				repaint();
			}
			
		}
		
	}
	public class beginningListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent action) {
			//Replace this with a condition of where the health bar length should sto
			//PROBABLY set it to newHealth/fullHealth (Which would be percentage of the full
			//Health that the pokemon is now at.
			if(trainerX ==45){
				startingTimer.stop();
			}
			else{
				trainerX++;
				pokemonX--;
				repaint();
			}
			
		}
		
	}
	
	public class throwBallListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent action) {
			//Replace this with a condition of where the health bar length should sto
			//PROBABLY set it to newHealth/fullHealth (Which would be percentage of the full
			//Health that the pokemon is now at.
			if(pokeballX == 370){
				pokeballTimer.stop();
			}
			else{
				index++;
				pokeballX+=2;
				pokeBallY--;
				if(index%10==0){
					pokeballWidth--;
					pokeballLength--;
				}
				repaint();
			}
			
		}
		
	}
	
}
 