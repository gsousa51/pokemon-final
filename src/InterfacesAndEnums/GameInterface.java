import java.awt.Point;

public interface GameInterface {

	public MapObject[][] getMap();
	public Point getTrainerPosition();
	public int getRemainingSteps();
	public void moveTrainer(Direction dir);
}
