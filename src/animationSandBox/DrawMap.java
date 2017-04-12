package animationSandBox;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
		this.setSize(1000, 1000);
		
	}
	@Override
	public void paint(Graphics g){
//		g.setColor(Color.BLACK);
//		g.drawRect(0, 0, 600, 600);
		for(int r =0; r<100; r++){
			for(int c = 0; c<100; c++){
				if(map[r][c]=='r'){
					g.drawImage(rock, r*50, c*50, 50,50,null);
				}
				else if(map[r][c]=='g'){
					g.drawImage(grass1, r*50, c*50, 50, 50, null);
				}
				else{
					g.drawImage(grass2, r*50, c*50, 50, 50, null);
				}
			}
		}
	}
	
}
