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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameFrame;
import interfaceEnumMocks.Direction;
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
	private BufferedImage selectionBack;
	private BufferedImage rock;
	private BufferedImage bait;
	// A list of different pokeball images for animation.
	private List<BufferedImage> pokeballThrowList;
	private List<BufferedImage> pokeballWobbleList;
	// 0 is the normal pokeball picture.
	private int pokeballIndex = 0;
	private int wobbleIndex = 0;
	// Used for reference for the size of our picture list.
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

	// buttons used to trigger animations/actions in game.
	private JButton throwBall;
	private JButton throwRock;
	private JButton throwBait;
	private JButton runAway;
	private JPanel buttonPanel;
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

	public BattleScenePanel(Game game, GameFrame frame) {
		this.game = game;
		this.container = frame;
		initializePanel();
	}// end constructor.

	// Method called by GameFrame after popping this panel.
	// It switches the Pokemon in the frame to the correct one and starts the
	// animation
	// of the Pokemon and trainer sliding in.
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

		// Set our variables.
		// Index 1 of getHealth is the full health of the pokemon.
		pokemonFullHealth = poke.getHealth()[1];
		// Index 0 is the current health.
		pokemonCurrentHealth = poke.getHealth()[0];
		// The pixel length of a FULL health bar is 95, so we calculate it
		// accordingly.
		healthBarLength = (((pokemonCurrentHealth / pokemonFullHealth) * 100) - 5);
		// Set the starting spot of the red bar used for the animation of a
		// decreasing health bar.
		redBarStartingLength = (int) healthBarLength;
		// Set our boolean flags to false.
		animating = true;
		caught = false;
		// Start the "sliding in" animation.
		startingTimer.start();
		name = poke.toString().toUpperCase();
		repaint();
	}

	private void initializePanel() {

		// Ours lists to contain the various pictures of the pokeball.
		// Used for animation.
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

		// Initialize all the necessary timers for animation.
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
		throwBall = new JButton("Throw Ball");
		throwBall.setContentAreaFilled(false);
		throwBall.setEnabled(true);
		throwBall.addActionListener(new ProjectileButtonListener());

		throwBall.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		throwBall.setFont(font);
		throwRock = new JButton("Throw Rock");
		throwRock.addActionListener(new ProjectileButtonListener());

		throwRock.setContentAreaFilled(false);
		throwRock.setFont(font);
		throwRock.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));

		throwBait = new JButton("Throw Bait");
		throwBait.setContentAreaFilled(false);
		throwBait.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		throwBait.setFont(font);
		throwBait.addActionListener(new ProjectileButtonListener());

		runAway = new JButton("Run Away");
		runAway.setContentAreaFilled(false);
		runAway.setBorder(BorderFactory.createDashedBorder(null, 3, 2, 4, true));
		runAway.setFont(font);
		runAway.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Don't allow a click if there's something happening already or
				// the game is over.
				if (!animating && !game.gameOver()) {
					// set the flag to true.
					animating = true;
					// Start a new timer that animates the trainer leaving.
					new Timer(5, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// If trainer hits spot off screen, end the
							// animation and battle.
							if (trainerX == trainerStartingSpotX) {
								((Timer) e.getSource()).stop();
								animating = false;
								endOfBattle();

							}
							// Else keep sliding the trainer.
							else {
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

	// Method called after use throws something.
	// Used for resetting the variables used for animation.
	private void endOfTurn() {
        

		// Set width and length back to their starting point.
		projectileWidth = 30;
		projectileLength = 30;
		// Put them back where they begin.
		projectileX = projectileStartingSpotX;
		projectileY = projectileStartingSpotY;
		// Put the wobbleIndex back to the start.
		wobbleIndex = 0;
		// Set the index we use for timers to 0.
		index = 0;
		wobbleY = 45;
		// If the game is over, tell the JFrame.
		if (game.gameOver()) {
			container.gameOver();
		}
		// Else, if the pokemon isn't caught AND it's still alive.
		else if (!caught && pokemonCurrentHealth > 0) {
			// If the pokemon returns false for their turn, they ran away.
			if (!currentPokemon.doTurn()) {
				// Move them off screen,
				pokemonX = pokemonOffScreen;
				repaint();
				// Show message they ran.
				JOptionPane.showMessageDialog(null, name.toUpperCase() + " RAN AWAY!", "",
						JOptionPane.INFORMATION_MESSAGE);
				endOfBattle();
			}
			// If pokemon didn't run away, check if it's angry.
			// If so, show message.
			else if (currentPokemon.isAngry()) {
				JOptionPane.showMessageDialog(null, name.toUpperCase() + " IS ANGRY!", "",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}


	}

	// Method used when pokemon is either caught, killed, or it runs away.
	private void endOfBattle() {
		// Resets the positions so they're ready to start a new battle.
		trainerX = trainerStartingSpotX;
		pokemonX = pokemonStartingSpotX;
		healthBarLength = startingHealthBarLength;
		redBarStartingLength = startingHealthBarLength;
		caught = false;
		// Tell JFrame to switch back to the map.
		container.switchPanels();
	}

	/**
	 * Override the paint component so we can draw JPanel they way we want.
	 * Method is called using repaint(). To create animation, we change the
	 * position variables used for drawing and repaint repeatedly.
	 */

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.white);
		// First draw a white rectangle the size of the screen.
		// This is necessary since to "refresh" the screen.
		g2.fillRect(0, 0, 500, 300);
		// Draw the background
		g2.drawImage(backGround, 0, 0, 500, 300, null);
		// Set color to black and write out the pokemon's name and how many
		// balls are left.
		g2.setColor(Color.black);
		g2.setFont(new Font("Dialog.bold", Font.BOLD, 12));
		g2.drawString("Balls Left: " + game.ballsLeft(), 350, 180);
        // Steve - uptate pokeball count during battle
        this.container.getInfoPanel().reset();
		g2.drawString(name, 40, 55);
		// write out the amount of health pokemon has left.
		g2.drawString(pokemonCurrentHealth + " / " + pokemonFullHealth, 120, 55);
		// Draw the health bar.
		g2.setColor(Color.GREEN);
		g2.fillRect(healthBarTopLeftX, healthBarTopLeftY, (int) healthBarLength, healthBarHeight);
		// If timer is running, animate the health decreasing.
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
		// If wobbleTimer is running, we're animating after the ball is thrown.
		// Draw the ball in the correct spot.
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

	/**
	 * 
	 * ActionListener for the button's the are used for throwing objects at
	 * pokemon. Based on the message in the button, we animate throwing the
	 * correct object.
	 *
	 */
	private class ProjectileButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent buttonPressed) {
			JButton button = (JButton) buttonPressed.getSource();
			// If something is already being animated or game is over, ignore click.
			if (!animating && !game.gameOver()) {
				switch (button.getText()) {
				//User is attempting to throw ball.
				case "Throw Ball":
					//If user has pokeballs, throw one.
					if (game.ballsLeft() > 0) {
						projType = projectileType.BALL;
						animating = true;
						//Start the timer
						projectileTimer.start();
						//Tell the Game that ball was thrown.
						game.throwBall();
					}
					//Else user is out, show message.
					else {
						JOptionPane.showMessageDialog(null, "OUT OF BALLS!", "", JOptionPane.INFORMATION_MESSAGE);
					}
					break;
					//Infinite bait, start animation.
				case "Throw Bait":
					projType = projectileType.BAIT;
					animating = true;
					projectileTimer.start();

					break;
					//Infinite rocks, start animation.
				case "Throw Rock":
					projType = projectileType.ROCK;
					animating = true;
					projectileTimer.start();

					break;
				default:
					break;
				}
			}
		}

	}

	/**
	 * ActionListener that animate the pokemon shaking after being hit by rock.
	 * Works by moving the top-left corner of pokemon drawing by 1 and repainting.
	 * Every five movements of the pixel we change directions.
	 */
	private class PokemonShakeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//If we've moved pokemon pixel 50 times, stop the timer.
			if (shakeIndex == 50) {
				shakeTimer.stop();
				//Start the healthBarTimer.
				healthBarTimer.start();
				//Reset the shakeIndex to 0.
				shakeIndex = 0;
				//Call end of turn method.
				endOfTurn();

			}
			//Every 5 movements, change direction.
			if (shakeIndex % 5 == 0) {
				if (shakeDirection == Direction.EAST) {
					shakeDirection = Direction.WEST;
				} else {
					shakeDirection = Direction.EAST;
				}
			}
			//Change positon based on direction.
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
				//We change x by 2 and y by one to animate a line of slope 1/2
				//That the projectile follows.
				projectileX += 2;
				projectileY--;
				if (index % 5 == 0) {
					pokeballIndex++;
				}
				//Every 10 changes, shrink the projectile so it looks like it's moving away.
				if (index % 10 == 0) {
					projectileWidth--;
					projectileLength--;
				}
				repaint();
			}

		}

	}

}
