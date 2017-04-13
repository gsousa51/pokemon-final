package animationSandBox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * This class is used to draw a visual representation of the mapgrid used in the model
 * It draws it on, takes a snapshot of it and saves the snapshot.
 * We then use the snapshot in the animation model
 * This JPanel draws a border of 250 pixels around the map.
 */
public class DrawMap extends JPanel {
	BufferedImage grass;
	BufferedImage ground;
	BufferedImage rock1;
	BufferedImage rock;
	char[][] map;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(5000, 5000);
		createFakeMap map = new createFakeMap();
		frame.add(new DrawMap(map));
		frame.setVisible(true);

	}

	public DrawMap(createFakeMap mapObj) {
		try {
			grass = ImageIO.read(new File("src/animationSandBox/GRASSBUSH.PNG"));
			ground = ImageIO.read(new File("src/animationSandBox/GROUND.PNG"));
			rock1 = ImageIO.read(new File("src/animationSandBox/Boulder.png"));
			rock = rock1.getSubimage(0, 0, 125, 125);
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		map = mapObj.getMap();
		this.setSize(2000, 2000);

		BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.paint(img.getGraphics());
		try {
			ImageIO.write(img, "png", new File("updatedMap.png"));

			System.out.println("panel saved as image");

		} catch (Exception e) {
			System.out.println("panel not saved" + e.getMessage());
		}

	}

	@Override
	public void paint(Graphics g) {
		// g.setColor(Color.BLACK);
		// g.drawRect(0, 0, 600, 600);
		g.setColor(Color.black);
		for (int i = 0; i < 35; i++) {

			// g.fillRect(0, i*50, 250, 50);
			for (int j = 0; j < 5; j++) {

				g.drawImage(ground, j*50, i * 50, 50, 50, null);
				g.drawImage(ground, 1750 + j * 50, i * 50, 50, 50, null);
				if (j == 4&& i>4) {
					g.drawImage(rock, j * 50, i * 50, 50, 50, null);
					g.drawImage(rock, 1750, i * 50, 50, 50, null);
				}

			}
			// g.fillRect(1750, i*50, 250, 50);

		}
		for (int i = 0; i < 35; i++) {
			for(int j=0; j<5; j++){
			g.drawImage(ground, i*50, j * 50, 50, 50, null);
			g.drawImage(ground, i * 50, 1750+ j * 50, 50, 50, null);
			if (j == 4&&i>4) {
				g.drawImage(rock, i * 50, j * 50, 50, 50, null);
				g.drawImage(rock, i * 50, 1750, 50, 50, null);
			}
			}

		}
//			g.fillRect(i * 50, 0, 50, 250);
//			g.fillRect(i * 50, 1750, 50, 250);

		for (int r = 0; r < 30; r++) {
			for (int c = 0; c < 30; c++) {
				if (map[r][c] == 'r') {
					g.drawImage(ground, c * 50 + 250, r * 50 + 250, 50, 50, null);
					g.drawImage(rock, c * 50 + 250, r * 50 + 250, 50, 50, null);
				} else if (map[r][c] == 'g') {
					g.drawImage(grass, c * 50 + 250, r * 50 + 250, 50, 50, null);

				} else {
					g.drawImage(ground, c * 50 + 250, r * 50 + 250, 50, 50, null);
				}
			}
		}
	}

}
