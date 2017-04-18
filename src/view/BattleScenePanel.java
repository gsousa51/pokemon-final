package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;
import model.Pokemon;

public class BattleScenePanel extends JPanel {

	// Constants we use for drawing the healthbar
	final static int healthBarTopLeftX = 97;
	final static int healthBarTopLeftY = 65;
	final static int healthBarHeight = 4;
	// variable for drawing health bar
	// slides.

	private int healthBarLength = 95;
	// Constants used for drawing the pokemon
	final static int pokemonLength = 125;
	final static int pokemonWidth = 125;
	final static int pokemonY = 20;
	final static int pokemonStartingSpotX = 500;
	// Variable used for pokemon's top left x coord.
	// (Not a constant since we use it for sliding pokemon into frame)
	private int pokemonX = pokemonStartingSpotX;

	// Constants for drawing the trainer
	final static int trainerXStartingSpot = -110;
	final static int trainerXEndingSpot = 45;
	final static int trainerY = 85;
	final static int trainerHeight = 140;
	final static int trainerWidth = 115;
	// variable for drawing trainer.
	// Not a constant since we slide trainer onto screen.
	private int trainerX = trainerXStartingSpot;

	// Variables/constants to draw projectile
	// (Bait, ball, rock)
	private int projectileLength = 30;
	private int projectileWidth = 30;
	final static int projectileStartingSpotX = 100;
	final static int projectileStartingSpotY = 180;
	private int projectileX = projectileStartingSpotX;
	private int projectileY = projectileStartingSpotY;

	// Temporary variable used for testing
	// TODO: delete this variable.
	private int clicks = 0;

	// Timers used for different animations.
	private javax.swing.Timer healthBarTimer;
	private javax.swing.Timer startingTimer;
	private javax.swing.Timer projectileTimer;

	// Images used for drawing our panel
	private BufferedImage backGround;
	private BufferedImage trainerBackStanding;
	private BufferedImage spriteSheet;
	private BufferedImage pokemonSpriteSheet;
	private BufferedImage pokeball;
	private BufferedImage selectionBack;
	private BufferedImage rock;
	private BufferedImage bait;

	private BufferedImage currentPokemon;
	private BufferedImage nidoran;
	private BufferedImage paras;
	private BufferedImage doduo;
	private BufferedImage venonat;
	private BufferedImage cubone;
	private BufferedImage nidorina;
	private BufferedImage ryhorn;
	private BufferedImage exeggcute;
	private BufferedImage parasect;
	private BufferedImage chansey;
	// Used for testing
	// TODO: delete pokemon list
	private List<Pokemon> pokemonList;
	// buttons used to trigger animations/actions in game.
	private JButton throwBall;
	private JButton throwRock;
	private JButton throwBait;
	private JButton runAway;
	private JPanel buttonPanel;
	private Game game;
	// variable used to size down projectiles as they move farther.
	private int index = 0;
	private boolean animating = false;
	private projectileType projType = null;

	// enums for what projectile to throw.
	private enum projectileType {
		ROCK, BALL, BAIT;
	}

	// main method for testing
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(520, 550);
		frame.add(new BattleScenePanel(new Game()));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public BattleScenePanel(Game game) {
		this.game = game;
		initializePanel();
		initializePokelist();
	}//end constructor.

	public void initializePokelist() {
		pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(new Pokemon("Nidoran", 41, 235, 55));
		pokemonList.add(new Pokemon("Paras", 25, 190, 35));
		pokemonList.add(new Pokemon("Doduo", 75, 190, 35));
		pokemonList.add(new Pokemon("Venonat", 45, 190, 60));
		pokemonList.add(new Pokemon("Cubone", 35, 190, 50));
		pokemonList.add(new Pokemon("Nidorina", 56, 120, 70));
		pokemonList.add(new Pokemon("Ryhorn", 25, 120, 80));
		pokemonList.add(new Pokemon("Exeggcute", 40, 90, 60));
		pokemonList.add(new Pokemon("Parasect", 30, 75, 60));
		pokemonList.add(new Pokemon("Chansey", 50, 30, 250));
	}

	public void setPokemon(Pokemon poke) {
		switch (poke.toString()) {
		case "Nidoran":
			currentPokemon = nidoran;
			break;
		case "Paras":
			currentPokemon = paras;
			break;
		case "Doduo":
			currentPokemon = doduo;
			break;
		case "Venonat":
			currentPokemon = venonat;
			break;
		case "Cubone":
			currentPokemon = cubone;
			break;
		case "Nidorina":
			currentPokemon = nidorina;
			break;
		case "Ryhorn":
			currentPokemon = ryhorn;
			break;
		case "Exeggcute":
			currentPokemon = exeggcute;
			break;
		case "Parasect":
			currentPokemon = parasect;
			break;
		case "Chansey":
			currentPokemon = chansey;
			break;
		default:
			break;
		}
		animating = true;
		startingTimer.start();
		repaint();
	}

	private void initializePanel() {

		this.setSize(500, 500);
		this.setLayout(null);
		Font font = new Font("Dialog.bold", Font.PLAIN, 14);

		// Button panel will hold the buttons.
		// (Believe it or not)
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.setSize(500, 200);
		buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));

		// Read in all the images we need.
		try {
			backGround = ImageIO.read(new File("src/view/BattleSceneBackground.png"));
			spriteSheet = ImageIO.read(new File("src/view/pokemonSprite.png"));
			pokemonSpriteSheet = ImageIO.read(new File("src/view/PokemonSprites.png"));
			selectionBack = ImageIO.read(new File("src/view/SelectionBackground.PNG"));
			rock = ImageIO.read(new File("src/view/rock.png"));
			BufferedImage baitSprite = ImageIO.read(new File("src/view/berry.png"));
			bait = baitSprite.getSubimage(280, 8, 73, 40);
			pokeball = spriteSheet.getSubimage(300, 64, 16, 13);
			trainerBackStanding = spriteSheet.getSubimage(175, 180, 55, 50);
			nidoran = pokemonSpriteSheet.getSubimage(265, 98, 40, 38); 
			paras = pokemonSpriteSheet.getSubimage(1374, 99, 50, 33);
			doduo = pokemonSpriteSheet.getSubimage(2168, 180, 70, 45);
			venonat = pokemonSpriteSheet.getSubimage(1531, 84, 52, 65);
			cubone = pokemonSpriteSheet.getSubimage(1539, 260, 35, 42);
			nidorina = pokemonSpriteSheet.getSubimage(100, 105, 50, 40);
			ryhorn = pokemonSpriteSheet.getSubimage(2094, 240, 60, 65);
			exeggcute = pokemonSpriteSheet.getSubimage(1370, 244, 65, 53);
			parasect = pokemonSpriteSheet.getSubimage(1441, 79, 93, 65);
			chansey = pokemonSpriteSheet.getSubimage(5, 336, 58, 46);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create and initialize throw ball button
		JButton throwBall = new JButton("Throw Ball");
		throwBall.setContentAreaFilled(false);
		throwBall.setEnabled(true);
		throwBall.addActionListener(new ProjectileButtonListener());

		throwBall.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		throwBall.setFont(font);
		JButton throwRock = new JButton("Throw Rock");
		throwRock.addActionListener(new ProjectileButtonListener());
		
		throwRock.setContentAreaFilled(false);
		throwRock.setFont(font);
		throwRock.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		
		JButton throwBait = new JButton("Throw Bait");
		throwBait.setContentAreaFilled(false);
		throwBait.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		throwBait.setFont(font);
		throwBait.addActionListener(new ProjectileButtonListener());

		JButton runAway = new JButton("Run Away");
		runAway.setContentAreaFilled(false);
		runAway.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		runAway.setFont(font);
		runAway.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Add run away functionality here.
				 */
			}
		});

		buttonPanel.add(throwBait);
		buttonPanel.add(throwRock);
		buttonPanel.add(throwBall);
		buttonPanel.add(runAway);
		this.add(buttonPanel);
		
		buttonPanel.setLocation(0, 300);
		// Temp mouse listener for testing
		// TODO: delete mouse listener.
		this.addMouseListener(new mouse());

		healthBarTimer = new javax.swing.Timer(30, new DrawHealthBarListener());
		startingTimer = new javax.swing.Timer(5, new BeginningListener());
		projectileTimer = new javax.swing.Timer(15, new throwProjectileListener());
		repaint();
	}

	public void setCurrentPokemon(Pokemon poke) {

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		// First draw a white rectangle the size of the screen.
		// This is necessary since to "refresh" the screen.
		g2.fillRect(0, 0, 500, 300);
		// Draw the background
		g2.drawImage(backGround, 0, 0, 500, 300, null);
		// Draw the health bar.
		g2.setColor(Color.GREEN);
		g2.fillRect(healthBarTopLeftX, healthBarTopLeftY, healthBarLength, healthBarHeight);
		// Draw a replica image of our button panel, otherwise it flickers...
		g2.drawImage(selectionBack, -1, 298, 500, 200, null);
		// Draw the trainer
		g2.drawImage(trainerBackStanding, trainerX, trainerY, trainerWidth, trainerHeight, null);
		// Draw the pokemon we're facing.
		g2.drawImage(currentPokemon, pokemonX, pokemonY, pokemonWidth, pokemonLength, null);

		// If we're throwing something, draw it.
		if (animating) {
			if (projType == projectileType.BALL) {
				g2.drawImage(pokeball, projectileX, projectileY, projectileWidth, projectileLength, null);
			} else if (projType == projectileType.ROCK) {
				g2.drawImage(rock, projectileX, projectileY, projectileWidth, projectileLength, null);
			} else {
				g2.drawImage(bait, projectileX, projectileY, projectileWidth, projectileLength, null);
			}
		}
		
		//If there isn't any animation happening,
		//Repaint the buttonPanel.
		if(!animating){
			// Cuts down on flickering.
			buttonPanel.repaint();
		}

	}

	// Temp mouselistener for testing
	// TODO : delete mouse listener...
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
			if (!startingTimer.isRunning()) {
				setPokemon(pokemonList.get(clicks));
				clicks++;
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class ProjectileButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent buttonPressed) {
			JButton button = (JButton)buttonPressed.getSource();
			switch(button.getText()){
			case "Throw Ball" :
				/*
				 * if(trainer.hasBall(){
				 * 		trainer.throwBall();	
				 */
				projType = projectileType.BALL;
				animating = true;
				projectileTimer.start();
				
				break;
			case "Throw Bait" :
				/*
				 * if(trainer.hasBait(){
				 * 		trainer.throwBait();	
				 */
				projType = projectileType.BAIT;
				animating = true;
				projectileTimer.start();
				break;
			case "Throw Rock" :
				/*
				 * if(trainer.hasRock(){
				 * 		trainer.throwRock();	
				 */
				projType = projectileType.ROCK;
				animating=true;
				projectileTimer.start();
				break;
			default: 
				break;
			}
			/*
			 * if(trainer.hasRock()){
			 * 		trainer.throwRock();
			 * 		projectileTimer.start();
			 * }
			 */
			
			
		}
		
	}
	private class DrawHealthBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			// Replace this with a condition of where the health bar length
			// should sto
			// PROBABLY set it to newHealth/fullHealth (Which would be
			// percentage of the full
			// Health that the pokemon is now at.
			if (healthBarLength == 0) {
				animating=false;
				healthBarTimer.stop();
			} else {
				System.out.println(healthBarLength);
				healthBarLength--;
				repaint();
			}

		}

	}

	private class BeginningListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			// Replace this with a condition of where the health bar length
			// should sto
			// PROBABLY set it to newHealth/fullHealth (Which would be
			// percentage of the full
			// Health that the pokemon is now at.
			if (trainerX == 45) {
				animating=false;
				startingTimer.stop();
				
			} else {
				trainerX++;
				pokemonX--;
				repaint();
			}

		}

	}

	public class throwProjectileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			// Replace this with a condition of where the health bar length
			// should sto
			// PROBABLY set it to newHealth/fullHealth (Which would be
			// percentage of the full
			// Health that the pokemon is now at.
			if (projectileX == 370) {
				projectileTimer.stop();
				projectileX = projectileStartingSpotX;
				projectileY = projectileStartingSpotY;
				projectileWidth = 30;
				projectileLength = 30;
				animating = false;
				repaint();
			} else {
				index++;
				projectileX += 2;
				projectileY--;
				if (index % 10 == 0) {
					projectileWidth--;
					projectileLength--;
				}
				repaint();
			}

		}

	}

}
