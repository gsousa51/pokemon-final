/*****************************************
 * 										 
 * 
 * NOTE: THIS IS NOT USED IN ITERATION 1.
 * 
 ******************************/
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameFrame;
import interfaceEnumMocks.Direction;
import interfaceEnumMocks.GameOverOptions;
import model.Game;
import model.Pokemon;

public class BattleScenePanel extends JPanel {

	// Constants we use for drawing the healthbar
	final static int healthBarTopLeftX = 97;
	final static int healthBarTopLeftY = 65;
	final static int healthBarHeight = 4;
	final static int startingHealthBarLength = 95;
	// variable for drawing health bar
	// slides.

	private int redBarStartingLength = startingHealthBarLength;
	private double healthBarLength = startingHealthBarLength;
	// Constants used for drawing the pokemon
	final static int pokemonLength = 125;
	final static int pokemonWidth = 125;
	final static int pokemonY = 20;
	final static int pokemonStartingSpotX = 350;
	final static int pokemonOffScreen = -200;
	// Variable used for pokemon's top left x coord.
	// (Not a constant since we use it for sliding pokemon into frame)
	private int pokemonX = pokemonStartingSpotX;
	private int trainerIndex = 0;
	private int slidingWidth = 1;
	private int wobbleY = 45;
	private int wobbleX = 370;
	// Constants for drawing the trainer
	final static int trainerStartingSpotX = -110;
	final static int trainerXEndingSpot = 45;
	final static int trainerY = 85;
	final static int trainerHeight = 140;
	final static int trainerWidth = 115;
	// variable for drawing trainer.
	// Not a constant since we slide trainer onto screen.
	private int trainerX = trainerStartingSpotX;

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
	private javax.swing.Timer shakeTimer;
	private javax.swing.Timer wobbleTimer;

	// Images used for drawing our panel
	private BufferedImage backGround;
	private BufferedImage trainerBackStanding;
	private BufferedImage spriteSheet;
	// Sprite sheet for various pokeballs.
	private BufferedImage pokeballSpriteSheet;
	private BufferedImage pokemonSpriteSheet;
	private BufferedImage pokeball;
	private BufferedImage selectionBack;
	private BufferedImage rock;
	private BufferedImage bait;
	// A list of different pokeball images for animation.
	private List<BufferedImage> pokeballThrowList;
	private List<BufferedImage> pokeballWobbleList;
	// 0 is the normal pokeball picture.
	private int pokeballIndex = 0;
	private int wobbleIndex = 0;
	private final static int pokeballWobbleListSize = 7;
	private final static int pokeballThrowListSize = 8;
	private BufferedImage currentPokemonImage;
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
	private JLabel itemCount;
	private Game game;
	// variable used to size down projectiles as they move farther.
	private int index = 0;
	private int shakeIndex = 0;
	private Direction shakeDirection = Direction.WEST;
	private boolean animating = false;
	private projectileType projType = null;
	private Pokemon currentPokemon;

	private double pokemonFullHealth;
	private double pokemonCurrentHealth;
	private GameFrame container;
	private String name;

	private boolean caught = false;

	// enums for what projectile to throw.
	private enum projectileType {
		ROCK, BALL, BAIT;
	}

	// // main method for testing
	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// frame.setSize(520, 550);
	// frame.add(new BattleScenePanel(new Game(1,
	// GameOverOptions.NO_BALL),frame));
	// frame.setVisible(true);
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// }

	public BattleScenePanel(Game game, GameFrame frame) {
		this.game = game;
		this.container = frame;
		initializePanel();
		initializePokelist();
	}// end constructor.

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

	// Method called by GameFrame after popping this panel.
	// It switches the pokemon in the frame to the correct one and starts the
	// animation
	// of the pokemon and trainer sliding in.
	public void setPokemon(Pokemon poke) {
		currentPokemon = poke;
		switch (poke.toString()) {
		case "Nidoran":
			currentPokemonImage = nidoran;
			break;
		case "Paras":
			currentPokemonImage = paras;
			break;
		case "Doduo":
			currentPokemonImage = doduo;
			break;
		case "Venonat":
			currentPokemonImage = venonat;
			break;
		case "Cubone":
			currentPokemonImage = cubone;
			break;
		case "Nidorina":
			currentPokemonImage = nidorina;
			break;
		case "Ryhorn":
			currentPokemonImage = ryhorn;
			break;
		case "Exeggcute":
			currentPokemonImage = exeggcute;
			break;
		case "Parasect":
			currentPokemonImage = parasect;
			break;
		case "Chansey":
			currentPokemonImage = chansey;
			break;
		default:
			break;
		}

		pokemonFullHealth = poke.getHealth()[1];
		pokemonCurrentHealth = poke.getHealth()[0];
		healthBarLength = (((pokemonCurrentHealth / pokemonFullHealth) * 100) - 5);
		redBarStartingLength = (int) healthBarLength;
		animating = true;
		caught = false;
		startingTimer.start();
		name = poke.toString().toUpperCase();
		repaint();
	}

	private void initializePanel() {

		pokeballThrowList = new ArrayList<>();
		pokeballWobbleList = new ArrayList<>();
		name = "Pokemon Name";

		this.setSize(500, 500);
		this.setLayout(null);
		createButtonPanel();
		this.add(buttonPanel);
		buttonPanel.setLocation(0, 300);

		// Read in all the images we need.
		try {
			backGround = ImageIO.read(new File("src/view/BattleSceneBackground.png"));
			spriteSheet = ImageIO.read(new File("src/view/pokemonSprite.png"));
			pokemonSpriteSheet = ImageIO.read(new File("src/view/PokemonSprites.png"));
			pokeballSpriteSheet = ImageIO.read(new File("src/view/pokeballSprite.png"));
			selectionBack = ImageIO.read(new File("src/view/SelectionBackground.PNG"));
			rock = ImageIO.read(new File("src/view/rock.png"));
			BufferedImage baitSprite = ImageIO.read(new File("src/view/berry.png"));
			bait = baitSprite.getSubimage(280, 8, 73, 40);
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 10, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 290, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 250, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 210, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 170, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 130, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 90, 22, 22));
			pokeballThrowList.add(pokeballSpriteSheet.getSubimage(133, 50, 22, 22));

			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(133, 10, 23, 23));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(132, 450, 21, 21));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(131, 490, 21, 21));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(133, 10, 22, 22));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(130, 530, 21, 21));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(134, 570, 21, 21));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(135, 610, 21, 21));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(136, 650, 21, 21));
			pokeballWobbleList.add(pokeballSpriteSheet.getSubimage(131, 405, 24, 28));

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

			e.printStackTrace();
		}

		healthBarTimer = new javax.swing.Timer(30, new DrawHealthBarListener());
		startingTimer = new javax.swing.Timer(2, new BeginningListener());
		projectileTimer = new javax.swing.Timer(2, new throwProjectileListener());
		shakeTimer = new javax.swing.Timer(15, new PokemonShakeListener());
		wobbleTimer = new javax.swing.Timer(5, new PokeballWobbleListener());

		repaint();
	}

	private void createButtonPanel() {
		Font font = new Font("Dialog.bold", Font.PLAIN, 14);
		// Button panel will hold the buttons.
		// (Believe it or not)
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.setSize(500, 200);
		buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));

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
				if (!animating && !game.gameOver()) {
					animating = true;
					new Timer(5, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (trainerX == trainerStartingSpotX) {
								((Timer) e.getSource()).stop();
								animating = false;
								endOfBattle();

							} else {
								trainerX--;
								repaint();
							}
						}

					}).start();
				}
			}
		});

		buttonPanel.add(throwBait);
		buttonPanel.add(throwRock);
		buttonPanel.add(throwBall);
		buttonPanel.add(runAway);
	}

	private void endOfTurn() {
		projectileWidth = 30;
		projectileLength = 30;
		projectileX = projectileStartingSpotX;
		projectileY = projectileStartingSpotY;
		// Put the wobbleIndex back to the start.
		wobbleIndex = 0;
		// Set the index we use for timers to 0.
		index = 0;
		wobbleY = 45;
		if (game.gameOver()) {
			container.gameOver();
		} else if (!caught && pokemonCurrentHealth > 0) {
			if (!currentPokemon.doTurn()) {
				pokemonX = pokemonOffScreen;
				repaint();
				JOptionPane.showMessageDialog(null, name.toUpperCase() + " RAN AWAY!", "",
						JOptionPane.INFORMATION_MESSAGE);
				endOfBattle();
			} else if (currentPokemon.isAngry()) {
				JOptionPane.showMessageDialog(null, name.toUpperCase() + " IS ANGRY!", "",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

	}

	private void endOfBattle() {
		trainerX = trainerStartingSpotX;
		pokemonX = pokemonStartingSpotX;
		healthBarLength = startingHealthBarLength;
		redBarStartingLength = startingHealthBarLength;
		caught = false;
		container.switchPanels();

	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.white);
		// First draw a white rectangle the size of the screen.
		// This is necessary since to "refresh" the screen.
		g2.fillRect(0, 0, 500, 300);
		// Draw the background
		g2.drawImage(backGround, 0, 0, 500, 300, null);
		g2.setColor(Color.black);
		g2.setFont(new Font("Dialog.bold", Font.BOLD, 12));
		g2.drawString("Balls Left: " + game.ballsLeft(), 350, 180);
		g2.drawString(name, 40, 55);
		g2.drawString(pokemonCurrentHealth + " / " + pokemonFullHealth, 120, 55);
		// Draw the health bar.
		g2.setColor(Color.GREEN);
		g2.fillRect(healthBarTopLeftX, healthBarTopLeftY, (int) healthBarLength, healthBarHeight);
		if (healthBarTimer.isRunning()) {
			g2.setColor(Color.red);
			g2.fillRect(healthBarTopLeftX + (int) healthBarLength, healthBarTopLeftY,
					redBarStartingLength - (int) healthBarLength, healthBarHeight);
		}
		// Draw a replica image of our button panel, otherwise it flickers...
		g2.drawImage(selectionBack, -1, 298, 500, 200, null);
		// Draw the trainer
		g2.drawImage(trainerBackStanding, trainerX, trainerY, trainerWidth, trainerHeight, null);
		// Draw the pokemon we're facing.

		g2.drawImage(currentPokemonImage, pokemonX, pokemonY, pokemonWidth, pokemonLength, null);

		// If we're throwing something, draw it.
		if (projectileTimer.isRunning()) {
			if (projType == projectileType.BALL) {
				g2.drawImage(pokeballThrowList.get(pokeballIndex % pokeballThrowListSize), projectileX, projectileY,
						projectileWidth, projectileLength, null);
			} else if (projType == projectileType.ROCK) {
				g2.drawImage(rock, projectileX, projectileY, projectileWidth, projectileLength, null);
			} else {
				g2.drawImage(bait, projectileX, projectileY, projectileWidth, projectileLength, null);
			}
		}
		if (wobbleTimer.isRunning()) {
			g2.drawImage(pokeballWobbleList.get(wobbleIndex), wobbleX, wobbleY, 20, 20, null);
		}

		// If there isn't any animation happening,
		// Repaint the buttonPanel.
		if (!animating) {
			// Cuts down on flickering.
			buttonPanel.repaint();
		}
	}

	private class ProjectileButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent buttonPressed) {
			JButton button = (JButton) buttonPressed.getSource();
			if (!animating) {
				switch (button.getText()) {
				case "Throw Ball":
					if (game.ballsLeft() > 0 && !game.gameOver()) {
						projType = projectileType.BALL;
						animating = true;
						projectileTimer.start();
						game.throwBall();
					} else {
						JOptionPane.showMessageDialog(null, "OUT OF BALLS!", "", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				case "Throw Bait":
					if (!game.gameOver()) {
						projType = projectileType.BAIT;
						animating = true;
						projectileTimer.start();
					}
					break;
				case "Throw Rock":
					if (!game.gameOver()) {
						projType = projectileType.ROCK;
						animating = true;
						projectileTimer.start();
					}
					break;
				default:
					break;
				}
			}
		}

	}

	private class PokemonShakeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (shakeIndex == 50) {
				shakeTimer.stop();
				healthBarTimer.start();
				shakeIndex = 0;
				endOfTurn();

			}
			if (shakeIndex % 5 == 0) {
				if (shakeDirection == Direction.EAST) {
					shakeDirection = Direction.WEST;
				} else {
					shakeDirection = Direction.EAST;
				}
			}
			if (shakeDirection == Direction.EAST) {
				pokemonX++;
			} else {
				pokemonX--;
			}
			repaint();
			shakeIndex++;
		}
	}

	private class DrawHealthBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			// If the length is zero, the pokemon was killed.
			if (healthBarLength <= 0) {
				JOptionPane.showMessageDialog(null, name.toUpperCase() + " WAS RUTHLESSLY MURDERED!", "",
						JOptionPane.INFORMATION_MESSAGE);
				healthBarTimer.stop();
				// Set caught to true so we don't allow the pokemon to do their
				// move.
				caught = true;
				// Call end of turn and end of battle.
				endOfTurn();
				endOfBattle();
			} else if (healthBarLength <= (pokemonCurrentHealth / pokemonFullHealth) * 100 - 5) {
				animating = false;
				healthBarTimer.stop();
				redBarStartingLength = (int) healthBarLength;
				repaint();

			} else {
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
				animating = false;
				startingTimer.stop();

			} else {
				trainerX++;
				repaint();
			}
		}
	}

	private class PokeballWobbleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			pokemonX = pokemonOffScreen;
			if (wobbleY == 100) {
				if (index >= 400) {
					// Check if its caught ONLY ONCE. (At index 400)
					if (index == 400) {
						// If it is caught, Set to the correct pokeball picture
						// Pop a Window that say the pokemon is caught.
						if (currentPokemon.isCaught()) {
							wobbleIndex = 3;
							caught = true;
							repaint();

							JOptionPane.showMessageDialog(null, "YOU CAUGHT " + name.toUpperCase(), "",
									JOptionPane.INFORMATION_MESSAGE);
							game.addPokemon(currentPokemon);
							endOfTurn();
							endOfBattle();
							wobbleTimer.stop();
							animating = false;
						}
						// Otherwise it isn't caught.
						// Set the pokeball to open and finish the animation
						else {
							wobbleIndex = 8;
							repaint();
							index++;
						}
					} else {
						index++;
					}
					// At index == 500 we stop the animation.
					if (index == 500) {
						wobbleTimer.stop();
						animating = false;
						// Pokemon got free, reset its x position.
						pokemonX = 350;
						repaint();
						wobbleY = 45;
						endOfTurn();
					}
				} // end if index==400 "if"
				else {
					index++;
					if (index % 40 == 0) {
						wobbleIndex++;
						if (wobbleIndex == 8) {
							wobbleIndex = 0;
						}
						repaint();
					}
				}
			} else {
				wobbleY++;
				repaint();
			}

		}

	}

	public class throwProjectileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			// Once the projectile hit desired distance.
			if (projectileX == 370) {
				// stop the timer
				projectileTimer.stop();

				if (projType == projectileType.ROCK) {
					// Let the pokemon know it was hit with a rock
					System.out.println(name.toUpperCase());
					System.out.println("Before: " + currentPokemon.getHealth()[0]);
					currentPokemon.hitWithRock();
					// Get its new current health.
					pokemonCurrentHealth = Math.max(0, currentPokemon.getHealth()[0]);
					System.out.println("After: " + currentPokemon.getHealth()[0]);
					shakeTimer.start();
				}
				// If we threw a ball, start the wobble animation.
				else if (projType == projectileType.BALL) {
					wobbleTimer.start();
				}
				// Otherwise we threw bait. Just reset the projectile values.
				else {
					currentPokemon.hitWithBait();
					if (currentPokemon.isEating()) {
						JOptionPane.showMessageDialog(null, name.toUpperCase() + " IS EATING!", "",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, name.toUpperCase() + " REFUSED TO EAT!", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					endOfTurn();
					animating = false;
				}
				repaint();
			} else {
				index++;
				projectileX += 2;
				projectileY--;
				if (index % 5 == 0) {
					pokeballIndex++;
				}
				if (index % 10 == 0) {
					projectileWidth--;
					projectileLength--;
				}
				repaint();
			}

		}

	}

}
