package animationSandBox;
import javax.swing.JFrame;

public class Frame extends JFrame {

	public static void main (String[] args){
		Frame frame = new Frame();
	}
	public Frame(){
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(520,510);
		this.add(new AnimationPanel());
		this.setVisible(true);
	}
}
