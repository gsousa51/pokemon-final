/*
 * Author: Gary Sousa
 * Class: MapPanel.java
 * Purpose: This Panel displays the limited view of the map with the trainer
 * 			as the center of focus at all times.
 * 			Panel utilizes a timer to animate the trainer as it walks around board.
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameFrame;
import interfaceEnumMocks.Direction;
import interfaceEnumMocks.GameInterface;
import interfaceEnumMocks.MapObject;
import model.Game;

public class MapPanel extends JPanel {

	// Each of these holds the various BufferedImages for trainer based on
	// which direction he is traveling.
	private List<BufferedImage> west = null;
	private List<BufferedImage> north = null;
	private List<BufferedImage> south = null;
	private List<BufferedImage> east = null;

	// All the buffered images we need for drawing
	// the background and trainer
	private BufferedImage map;
	private BufferedImage spriteSheet;

	// Each set of these is the set of images
	// used for drawing user walking/facing different
	// directions.
	private BufferedImage left1;
	private BufferedImage left2;
	private BufferedImage left3;

	private BufferedImage up1;
	private BufferedImage up2;
	private BufferedImage up3;

	private BufferedImage down1;
	private BufferedImage down2;
	private BufferedImage down3;

	private BufferedImage right1;
	private BufferedImage right2;
	private BufferedImage right3;

	// Variables keep track of the top left corner coords
	// For the subimage we create.
	private int subImageX;
	private int subImageY;
	// Height and width constants for drawing our background
	private final static int height = 500;
	private final static int width = 500;
	// x and y coords to for drawing our player
	// Player is ALWAYS in center of our panel.
	private final static int playerX = 250;
	private final static int playerY = 250;
	// Height and width contsants for drawing the picture of player.
	private final static int playerW = 50;
	private final static int playerL = 50;
	// Constants to keep track of the length/width of our MapObjects array.
	private final static int mapWidth = 40;
	private final static int mapHeight = 30;
	// Constant for pixel size of each square of mapgrid.
	// (Might be unnecessary but if we change the size later it'll be handy)
	private final static int pixelSize = 50;
	// Timer we use for animation when user moves.
	private javax.swing.Timer walkTimer;
	// An action listener for our timer.
	private ActionListener animationPerformer;
	// Initialize the trainer to be facing South
	private Direction direction = Direction.SOUTH;
	// index keeps track of which index we're using in our
	// ArrayLists that hold our trainer's images.
	private int index = 0;
	// Position trainer is at in our model
	private Point trainerPosition;
	// The game object used to reference the back end.
	// private GameInterface game;
	private Game game;
	// The mapGrid that is the back end of our map.
	private MapObject[][] mapGrid;
	// Flag var used to keep track of if trainer is in process
	// of moving. (Prevents user from button smashing and ruining everything.)
	private boolean walking = false;
	// The Frame holding this panel.
	// Allows us to interact with it.
	private GameFrame container;
	//TODO: Create the transition timer.
	private Timer transitionTimer;
	private int mapNumber;

	public MapPanel(Game game, GameFrame container, int mapNumber) {
		this.container = container;
		this.mapNumber = mapNumber;
		// Read in all the necessary images.
		setImages();

		this.setSize(width, height);
		this.repaint();
		this.addKeyListener(new Keyboard());
		this.setFocusable(true);
		transitionTimer = new Timer(25, new TransitionListener());
		this.game = game;
		trainerPosition = game.getTrainerPosition();
		mapGrid = game.getMap();
		// Set the top left corner of subimage for background
		// to be 50 times the position variables.
		// (Makes for ease in drawing)
		subImageX = trainerPosition.x * pixelSize;
		subImageY = trainerPosition.y * pixelSize;
		// Build our ActionListener that helps to perform the animation.
		animationPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// If the subImage variables are at the correct size,
				// The trainer has completed their motion.
				if (subImageX == trainerPosition.x * pixelSize && subImageY == trainerPosition.y * pixelSize) {
					// Stop the timer
					walkTimer.stop();
					// Set index to 0 (our "standing" image is at index 0 for
					// all arrays)
					index = 0;
					// Turn off the flag variable
					walking = false;
					// Tell JFrame our walk ended
					container.walkEnded();
					// repaint once more.
					repaint();
				} else {
					// Else, move the top-left corner of
					// the subimage for background correct variable accordingly.
					if (direction == Direction.NORTH) {
						subImageY -= 10;
					} else if (direction == Direction.WEST) {
						subImageX -= 10;
					} else if (direction == Direction.EAST) {
						subImageX += 10;
					} else {
						subImageY += 10;
					}
					// Change index to change the image used for trainer
					// This helps to make trainer look like he's walking.
					index++;
					repaint();
				}
			}
		};

		// Initialize the walkTimer giving it animationPerformer as its
		// actionListener
		walkTimer = new javax.swing.Timer(50, animationPerformer);
	}

	public void animateLeaving() {
		transitionTimer.start();
		// TODO: Put at the end of the timer.
		/// container.switchPanels();

	}

	private class TransitionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	// Method assigns the correct filePath to our BufferedImages
	public void setImages() {
		try {
			// Sprite sheet containing the images of the trainer
			spriteSheet = ImageIO.read(new File("src/animationSandBox/pokemonSprite.png"));
			if (mapNumber == 1) {
				map = ImageIO.read(new File("src/view/finalMap1.png"));
			} else {
				map = ImageIO.read(new File("src/view/finalMap2.png"));
			}
			// We get the subimages from the sprite sheet that we need for our
			// trainer.
			left1 = spriteSheet.getSubimage(30, 0, 15, 20);
			left2 = spriteSheet.getSubimage(30, 30, 15, 20);
			left3 = spriteSheet.getSubimage(30, 150, 15, 20);

			up1 = spriteSheet.getSubimage(60, 0, 15, 20);
			up2 = spriteSheet.getSubimage(60, 30, 15, 20);
			up3 = spriteSheet.getSubimage(60, 150, 15, 20);

			right1 = spriteSheet.getSubimage(90, 0, 15, 20);
			right2 = spriteSheet.getSubimage(90, 30, 15, 20);
			right3 = spriteSheet.getSubimage(90, 150, 15, 20);

			down1 = spriteSheet.getSubimage(0, 0, 15, 20);
			down2 = spriteSheet.getSubimage(0, 30, 15, 20);
			down3 = spriteSheet.getSubimage(0, 150, 15, 20);

			// Add all the images to the appropriate ArrayList
			// NOTE: ArrayList.getIndex(0) is always the "standing still" image.
			north = new ArrayList<>();
			north.add(up1);
			north.add(up2);
			north.add(up3);

			west = new ArrayList<>();
			west.add(left1);
			west.add(left2);
			west.add(left3);

			south = new ArrayList<>();
			south.add(down1);
			south.add(down2);
			south.add(down3);

			east = new ArrayList<>();
			east.add(right1);
			east.add(right2);
			east.add(right3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// end setImages

	// Method call to make sure the user is able to move.
	public boolean canMove() {
		// If user is moving up, make sure trainer isn't at top of map.
		if (direction.equals(direction.NORTH)) {
			if (trainerPosition.y > 0) {
				// If it is at a correct position, return the value of
				// isWalkable() of what would be the space the trainer moves to
				return mapGrid[trainerPosition.y - 1][trainerPosition.x].isWalkable();
			} else
				// else he's at the top, return false.
				return false;
		}
		// If user is moving down, make sure trainer isn't at bottom of map.
		if (direction.equals(direction.SOUTH)) {
			if (trainerPosition.y < mapHeight - 1) {
				// If user isn't at bottom of map, return value of space in
				// front of him.
				return mapGrid[trainerPosition.y + 1][trainerPosition.x].isWalkable();
			} else
				return false;
		}
		// Make sure user isn't at left edge of map.
		if (direction.equals(direction.WEST)) {
			if (trainerPosition.x > 0) {
				// if he isn't at edge, check if he can move forward.
				return mapGrid[trainerPosition.y][trainerPosition.x - 1].isWalkable();
			} else
				return false;
		} else
		// Else the user is trying to move East.
		// Make sure he isn't at right edge of map.
		{
			if (trainerPosition.x < mapWidth - 1) {
				// if isn't at edge, check if he can move forward.
				return mapGrid[trainerPosition.y][trainerPosition.x + 1].isWalkable();
			} else
				return false;
		}
	}// end canMove

	// Method called when we call repaint()
	// Redraws everything on map with he updated varaible values.
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw the subimage of the correct size as our background
		g2.drawImage(map.getSubimage(subImageX, subImageY, width, height), 0, 0, width, height, null);
		// draw the trainer in the correct placement.
		if (direction == Direction.NORTH) {
			g2.drawImage(north.get(index % 3), playerX, playerY, playerW, playerL, null);
		} else if (direction == Direction.WEST) {
			g2.drawImage(west.get(index % 3), playerX, playerY, playerW, playerL, null);
		} else if (direction == Direction.EAST) {
			g2.drawImage(east.get(index % 3), playerX, playerY, playerW, playerL, null);
		} else {
			g2.drawImage(south.get(index % 3), playerX, playerY, playerW, playerL, null);
		}
	}

	// Method called when user attempts to move up.
	public void moveNorth() {
		direction = Direction.NORTH;
		if (!canMove()) {
			walking = false;
			// This draws the user facing the direction they tried to move.
			super.repaint();
			return;
		} else {
			// Else we can mvoe, notify game trainer is moving.
			game.moveTrainer(direction);
			// Reset position variable.
			trainerPosition = game.getTrainerPosition();
			// Start timer for animation.
			walkTimer.start();
			super.repaint();
		}
	}

	public void moveWest() {
		direction = Direction.WEST;
		if (!canMove()) {
			walking = false;
			// This draws the user facing the direction they tried to move.
			super.repaint();
			return;
		} else {
			// Else we can move, notify game we're moving.
			game.moveTrainer(direction);
			// Reset position variable.
			trainerPosition = game.getTrainerPosition();
			// Start time for animation
			walkTimer.start();
			super.repaint();
		}
	}

	public void moveSouth() {
		// Set the direction we're walking to "south"
		direction = Direction.SOUTH;
		// If we can't move, set walking to false and return.
		if (!canMove()) {
			walking = false;
			// This draws the user facing the direction they tried to move.
			super.repaint();
			return;
		} else {
			// Else we can move. Notify game we're moving.
			game.moveTrainer(direction);
			// Update the position variable
			trainerPosition = game.getTrainerPosition();
			// Begin the timer for animation.
			walkTimer.start();
			super.repaint();
		}
	}

	public void moveEast() {
		// Set the direction to be "EAST"
		direction = Direction.EAST;
		// If we can't move, set walking to false and return.
		if (!canMove()) {
			walking = false;
			// This draws the user facing the direction they tried to move.
			super.repaint();
			return;
		} else {
			// else we can move.
			// Tell the game the trainer is moving to the east.
			game.moveTrainer(direction);
			// Reset our position variable
			trainerPosition = game.getTrainerPosition();
			// Start the timer to begin animation of trainer.
			walkTimer.start();
			super.repaint();
		}
	}

	// The keyboard listener we use to get moves from user.
	public class Keyboard implements KeyListener {
		@Override
		public void keyPressed(KeyEvent key) {

			// TODO this will need to be generalized to lock the rest of the UI
			// when there are more ways to interact with the GUI. Currently
			// wer are only using navigation keys so when the game is over I'm
			// just making a keypress a no-op
			// If the game is over, do nothing
			if (game.gameOver()) {
				return;
			}
			// If user isn't already in the middle of a move, read the key
			// typed.
			if (!walking) {

				if (key.getKeyCode() == KeyEvent.VK_W || key.getKeyCode() == KeyEvent.VK_UP) {
					walking = true;
					moveNorth();

				} else if (key.getKeyCode() == KeyEvent.VK_A || key.getKeyCode() == KeyEvent.VK_LEFT) {
					walking = true;
					moveWest();
				} else if (key.getKeyCode() == KeyEvent.VK_S || key.getKeyCode() == KeyEvent.VK_DOWN) {
					walking = true;
					moveSouth();
				} else if (key.getKeyCode() == KeyEvent.VK_D || key.getKeyCode() == KeyEvent.VK_RIGHT) {
					walking = true;
					moveEast();
				} else {
					return;
				}
			} else {
				// If we get here, user is already walking
				// or userpushed a key outside of valid keys

			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// we don't use
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// We don't use.
		}
	}
}