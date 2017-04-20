package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import interfaceEnumMocks.Direction;
import interfaceEnumMocks.MapObject;
import model.Game;
import model.Map;
import model.Trainer;

public class ModelTests
{
    @Test
    public void testTrainer()
    {
        Trainer trainer = new Trainer();
        
        assertEquals(trainer.getRemainingStep(), 500);
        assertEquals(trainer.getPosition(), new Point(20,14));
        trainer.move(Direction.NORTH);
        assertEquals(trainer.getRemainingStep(), 499);
        assertEquals(trainer.getPosition(), new Point(20, 13));
    }
    
    @Test
    public void testMap()
    {
        //New map 1
        Map map = new Map(1);
        MapObject[][] grid = map.getMap();
        //Does it print correctly??
        map.printMap();
    }
    
    @Test
    public void testGame()
    {
        Game game = new Game();
        
        assertEquals(game.getRemainingSteps(), 500);
        assertFalse(game.gameOver());
        game.moveTrainer(Direction.NORTH);
        assertEquals(game.getRemainingSteps(), 499);
        assertEquals(game.getTrainerPosition(), new Point(20,13));
        game.moveTrainer(Direction.WEST);
        assertEquals(game.getRemainingSteps(), 498);
        assertEquals(game.getTrainerPosition(), new Point(19,13));
        game.moveTrainer(Direction.SOUTH);
        assertEquals(game.getRemainingSteps(), 497);
        assertEquals(game.getTrainerPosition(), new Point(19,14));
        game.moveTrainer(Direction.EAST);
        assertEquals(game.getRemainingSteps(), 496);
        assertEquals(game.getTrainerPosition(), new Point(20,14));
        
        int mapW = game.getMapWidth();
        int mapH = game.getMapHeight();
        assertEquals(mapW, 40);
        assertEquals(mapH, 30);
        
        for(int i = 0; i < 500; i++)
            game.moveTrainer(Direction.NORTH);
        
        assertTrue(game.gameOver());
        
        
    }
   

}
