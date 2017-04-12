import java.awt.Point;
  
    public interface GameInterface {
        public MapObject[][] getMap();
           public Point getPlayerPosition();
               public Point getTrainerPosition();
               public int getRemaingSteps();
               public void moveTrainer(Direction dir);
               }
