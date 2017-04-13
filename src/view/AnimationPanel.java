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
import javax.swing.JFrame;
import javax.swing.JPanel;

import InterfacesAndEnums.Direction;
import InterfacesAndEnums.GameInterface;
import view.MockGame.replaceWithMapObject;

/*
 * NOTE: THIS PANEL IS USING A CLASS CALLED "replaceWithMapObject"
 * 		  as well as "MockGame" for testing purposes.
 * 		  Once we merge our work it will seamlessly work once we change them to MapObject and
 * 		  a regular game object.
 * 	    
 */
public class AnimationPanel extends JPanel {

	// Each of these holds the various BufferedImages for trainer based on
	// which direction he is travelling.
	List<BufferedImage> west = null;
	List<BufferedImage> north = null;
	List<BufferedImage> south = null;
	List<BufferedImage> east = null;

	// All the buffered images we need for drawing
	// the background and trainer
	BufferedImage map;

	BufferedImage left1;
	BufferedImage left2;
	BufferedImage left3;

	BufferedImage up1;
	BufferedImage up2;
	BufferedImage up3;

	BufferedImage down1;
	BufferedImage down2;
	BufferedImage down3;

	BufferedImage right1;
	BufferedImage right2;
	BufferedImage right3;

	// Variables keep track of the top left corner coords
	// For the subimage we create.
	int subImageX;
	int subImageY;
	// Height and width constants for drawing our background
	final static int height = 500;
	final static int width = 500;
	// x and y coords to for drawing our player
	// Player is ALWAYS in center of our panel.
	final static int playerX = 250;
	final static int playerY = 250;
	// Height and width contsants for drawing the picture of player.
	final static int playerW = 50;
	final static int playerL = 50;
	// Constants to keep track of the length/width of our MapObjects array.
	final static int mapWidth = 30;
	final static int mapHeight = 30;
	// Constant for pixel size of each square of mapgrid.
	// (Might be unnecessary but if we change the size later it'll be handy)
	final static int pixelSize = 50;
	// Timer we use for animation when user moves.
	javax.swing.Timer walkTimer;
	// An action listener for our timer.
	ActionListener animationPerformer;
	// Initialize the trainer to be facing South
	private Direction direction = Direction.SOUTH;
	// index keeps track of which index we're using in our
	// ArrayLists that hold our trainer's images.
	private int index = 0;
	private Point trainerPosition;
	private GameInterface game;
	private replaceWithMapObject[][] mapGrid;
	// Flag var used to keep track of if trainer is in process
	// of moving. (Prevents user from button smashing and ruining everything.)
	private boolean walking = false;

	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(520,500);
		MockGame game = new MockGame();
		frame.add(new AnimationPanel(game));
		frame.setVisible(true);
	
	}
	public AnimationPanel(GameInterface game) {
		// Read in all the necessary images.
		setImages();
		this.setSize(width, height);
		this.repaint();
		this.addKeyListener(new Keyboard());
		this.setFocusable(true);
		this.game = game;
		trainerPosition = game.getTrainerPosition();
		mapGrid = ((MockGame)game).getMap2();
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
		walkTimer = new javax.swing.Timer(100, animationPerformer);
	}

	// Method assigns the correct filePath to our BufferedImages
	public void setImages() {
		try {
			map = ImageIO.read(new File("src/view/THISWORKED.png"));
			left1 = ImageIO.read(new File("src/animationSandBox/Left1.PNG"));
			left2 = ImageIO.read(new File("src/animationSandBox/Left2.PNG"));
			left3 = ImageIO.read(new File("src/animationSandBox/Left3Stand.PNG"));

			up1 = ImageIO.read(new File("src/animationSandBox/Up1.PNG"));
			up2 = ImageIO.read(new File("src/animationSandBox/Up2.PNG"));
			up3 = ImageIO.read(new File("src/animationSandBox/Up3.PNG"));

			right1 = ImageIO.read(new File("src/animationSandBox/Right1.PNG"));
			right2 = ImageIO.read(new File("src/animationSandBox/Right2.PNG"));
			right3 = ImageIO.read(new File("src/animationSandBox/Right3.PNG"));

			down1 = ImageIO.read(new File("src/animationSandBox/Down1.PNG"));
			down2 = ImageIO.read(new File("src/animationSandBox/Down2.PNG"));
			down3 = ImageIO.read(new File("src/animationSandBox/Down3.PNG"));

			north = new ArrayList<>();
			north.add(up1);
			north.add(up2);
			north.add(up3);

			west = new ArrayList<>();
			west.add(left3);
			west.add(left2);
			west.add(left1);

			south = new ArrayList<>();
			south.add(down3);
			south.add(down2);
			south.add(down1);

			east = new ArrayList<>();
			east.add(right3);
			east.add(right2);
			east.add(right1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method call to make sure the user is able to move.
	public boolean canMove() {
		// If user is moving up, make sure trainer isn't at top of map.
		if (direction.equals(direction.NORTH)) {
			if (trainerPosition.y > 0) {
				// If it is at a correct position, return the value of
				// isWalkable() of what would be the space the trainer moves to
				return mapGrid[trainerPosition.y - 1][trainerPosition.x].isWalkable();
			} else
				//else he's at the top, return false.
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
		//Make sure user isn't at left edge of map.
		if (direction.equals(direction.WEST)) {
			if (trainerPosition.x > 0) {
				//if he isn't at edge, check if he can move forward.
				return mapGrid[trainerPosition.y][trainerPosition.x - 1].isWalkable();
			} else
				return false;
		} else
			//Else the user is trying to move East.
			//Make sure he isn't at right edge of map.
			{
			if (trainerPosition.x < mapWidth - 1) {
				//if isn't at edge, check if he can move forward.
				return mapGrid[trainerPosition.y][trainerPosition.x + 1].isWalkable();
			} else
				return false;
		}
	}// end canMove

	//Method called when we call repaint()
	//Redraws everything on map with he updated varaible values.
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Draw the subimage of the correct size as our background
		g2.drawImage(map.getSubimage(subImageX, subImageY, width, height), 0, 0, width, height, null);
		// draw the person in the correct placement.
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

	//Method called when user attempts to move up.
	public void moveNorth() {
		direction = Direction.NORTH;
		if (!canMove()) {
			walking = false;
			super.repaint();
			return;
		}else {
			game.moveTrainer(direction);
			trainerPosition = game.getTrainerPosition();
			walkTimer.start();
			super.repaint();
		}
	}

	public void moveWest() {
		direction = Direction.WEST;
		if (!canMove()) {
			walking = false;
			super.repaint();
			return;
		} else {
			game.moveTrainer(direction);
			trainerPosition = game.getTrainerPosition();
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
			super.repaint();
			return;
		} else {
			game.moveTrainer(direction);
			trainerPosition = game.getTrainerPosition();
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
			super.repaint();
			return;
		} else {
			game.moveTrainer(direction);
			trainerPosition = game.getTrainerPosition();
			walkTimer.start();
			super.repaint();
		}
	}

	// The keyboard listener we use to get moves from user.
	public class Keyboard implements KeyListener {
		@Override
		public void keyPressed(KeyEvent key) {
			// If user isn't already in the middle of a move, read the key
			// typed.
			if (!walking) {
				walking = true;
				if (key.getKeyCode() == KeyEvent.VK_W) {
					moveNorth();
				} else if (key.getKeyCode() == KeyEvent.VK_A) {
					moveWest();
				} else if (key.getKeyCode() == KeyEvent.VK_S) {
					moveSouth();
				} else if (key.getKeyCode() == KeyEvent.VK_D) {
					moveEast();
				}
			} else {
				//If we get here, user is already walking 
				//or userpushed a key outside of valid keys
				System.out.println("Skipped key press");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			//we don't use
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			//We don't use.
		}
	}
}
