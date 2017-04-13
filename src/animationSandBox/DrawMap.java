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

public class DrawMap extends JPanel {
	BufferedImage grass1;
	BufferedImage grass2; 
	BufferedImage rock;
	char[][] map;
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(5000, 5000);
		createFakeMap map = new createFakeMap();
		frame.add(new DrawMap(map));
		frame.setVisible(true);

		
		
	}
	public DrawMap(createFakeMap mapObj){
		try{
			grass1 = ImageIO.read(new File("src/animationSandBox/grass1.PNG"));
			grass2 = ImageIO.read(new File("src/animationSandBox/grass2.PNG"));
			rock = ImageIO.read(new File("src/animationSandBox/rock.PNG"));
		}
		catch(IOException e){
			System.out.println(e.getStackTrace());
		}
		map = mapObj.getMap();
		this.setSize(2000, 2000);
		
		   BufferedImage img = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		    this.paint(img.getGraphics());
		    try {
		        ImageIO.write(img, "png", new File("THISWORKED.png"));
		        
		        System.out.println("panel saved as image");

		    } catch (Exception e) {
		        System.out.println("panel not saved" + e.getMessage());
		    }

		
	}
	@Override
	public void paint(Graphics g){
//		g.setColor(Color.BLACK);
//		g.drawRect(0, 0, 600, 600);
		g.setColor(Color.black);
		for(int i = 0; i<35; i++){
			g.fillRect(0, i*50, 250, 50);
			g.fillRect(1750, i*50, 250, 50);
		}
		for(int i = 0; i<35; i++){
			g.fillRect(i*50, 0, 50, 250);
			g.fillRect(i*50, 1750, 50, 250);
		}
		
		for(int r =0; r<30; r++){
			for(int c = 0; c<30; c++){
				if(map[r][c]=='r'){
					g.drawImage(rock, c*50+250, r*50+250, 50,50,null);
				}
				else if(map[r][c]=='g'){
					g.drawImage(grass1, c*50+250, r*50+250, 50, 50, null);
					
				}
				else{
					g.drawImage(grass2, c*50+250, r*50+250, 50, 50, null);
				}
			}
		}
	}
	
	
}
