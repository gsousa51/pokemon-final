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

import InterfacesAndEnums.Direction;
import InterfacesAndEnums.GameInterface;
import InterfacesAndEnums.MapObject;

public class AnimationPanel extends JPanel {

	List<BufferedImage> left = null;
	List<BufferedImage> up = null;
	List<BufferedImage> down = null;
	List<BufferedImage> right = null;
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
	int currX ;
	int currY ;
	final static int height = 500;
	final static int width = 500;
	final static int playerX = 250;
	final static int playerY = 250;
	final static int playerW = 50;
	final static int playerL = 50;
	final static int mapWidth = 30;
	final static int mapHeight = 30;
	final static int pixelSize = 50;
	ActionListener taskPerformer;
	ActionListener timerStopper;
	javax.swing.Timer walkTimer;
	private Direction direction = Direction.SOUTH;
	int index = 0;
	int steps = 0;
	Point trainerPosition;
	GameInterface game;
	MapObject[][] mapGrid;
	boolean walking = false;

	public AnimationPanel(GameInterface game) {
		setImages();
		this.addKeyListener(new Keyboard());
		this.setFocusable(true);
		this.game = game;
		trainerPosition = game.getTrainerPosition();
		mapGrid = game.getMap();
		currX = trainerPosition.x*pixelSize;
		currY = trainerPosition.y*pixelSize;
		taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (currX == trainerPosition.x * pixelSize && 
					currY == trainerPosition.y * pixelSize) {
					walkTimer.stop();
					System.out.println("Steps: " + steps + " X : " + currX + " Y : " + currY);
					index = 0;
					walking = false;
					repaint();
				} else {
					if (direction == Direction.NORTH) {
						currY -= 10;
					} else if (direction == Direction.WEST) {
						currX -= 10;
					} else if (direction == Direction.EAST) {
						currX += 10;
					} else {
						currY += 10;
					}
					index++;
					repaint();
				}

			}
		};

		walkTimer = new javax.swing.Timer(100, taskPerformer);

	}

	public void setImages() {
		try {
			map = ImageIO.read(new File("src/view/new map.PNG"));
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
			up = new ArrayList<>();
			up.add(up1);
			up.add(up2);
			up.add(up3);
			left = new ArrayList<>();
			left.add(left3);
			left.add(left2);
			left.add(left1);
			down = new ArrayList<>();
			down.add(down3);
			down.add(down2);
			down.add(down1);
			right = new ArrayList<>();
			right.add(right3);
			right.add(right2);
			right.add(right1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setSize(width, height);
		this.repaint();
	}

	// Method call to make sure the user is able to move.
	// NOTE: EACH MOVE CALL MOVES US 40 PIXELS IN DIRECTION OF VARIABLE
	// "DIRECTION"
	public boolean canMove() {
		// If they're going up, we need to be at a value greater than 40.
		if (direction.equals(direction.NORTH)) {
			if(trainerPosition.y > 0){
				return mapGrid[trainerPosition.y-1][trainerPosition.x].isWalkable();
			}
			else return false;
		}
		if (direction.equals(direction.SOUTH)) {
			if(trainerPosition.y < mapHeight - 1){
				return mapGrid[trainerPosition.y+1][trainerPosition.x].isWalkable();
			}
			else return false;
		}
		if (direction.equals(direction.WEST)) {
			if (trainerPosition.x > 0){
				return mapGrid[trainerPosition.y][trainerPosition.x-1].isWalkable();
			}
			else return false;
		} else{
			if (trainerPosition.x < mapWidth - 1){
				return mapGrid[trainerPosition.y][trainerPosition.x+1].isWalkable();
			}
			else return false;
		}
	}// end canMove

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//Draw the subimage of the correct size as our background
		g2.drawImage(map.getSubimage(currX, currY, width, height), 0, 0, width, height, null);
		//draw the person in the correct placement.
		if (direction == Direction.NORTH) {
			g2.drawImage(up.get(index % 3), playerX, playerY, playerW, playerL, null);
		} else if (direction == Direction.WEST) {
			g2.drawImage(left.get(index % 3), playerX, playerY, playerW, playerL, null);
		} else if (direction == Direction.EAST) {
			g2.drawImage(right.get(index % 3), playerX, playerY, playerW, playerL, null);
		} else {
			g2.drawImage(down.get(index % 3), playerX, playerY, playerW, playerL, null);
		}
	}

	public void moveNorth() {
		direction = Direction.NORTH;
		if (!canMove()) {
			walking = false;
			System.out.println("Can't move");
			System.out.println("Walking = " + walking);
			return;
		}
		walkTimer.start();
		super.repaint();
	}

	public void moveWest() {
		direction = Direction.WEST;
		if (!canMove()) {
			walking = false;
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
					System.out.println("PUSHED W");
				} else if (key.getKeyCode() == KeyEvent.VK_A) {
					moveWest();
				} else if (key.getKeyCode() == KeyEvent.VK_S) {
					moveSouth();
				} else if (key.getKeyCode() == KeyEvent.VK_D) {
					moveEast();
				}
			} else {
				System.out.println("Skipped key press");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}
