package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import interfaceEnumMocks.Direction;
import interfaceEnumMocks.GameOverOptions;
import interfaceEnumMocks.MapObject;
import model.Game;
import model.Map;
import model.Pokemon;
import model.Trainer;

public class ModelTests
{
    @Test
    public void testTrainer()
    {
        Trainer trainer = new Trainer(1);
        
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
        Game game = new Game(1, GameOverOptions.NO_STEPS);

        game.findBall();
        assertEquals(game.ballsLeft(), 34);
        game.throwBall();
        assertEquals(game.ballsLeft(), 33);
        
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
        
        
        assertEquals(game.getMapNumber(), 1);
        assertEquals(game.getGameOverCondition(), GameOverOptions.NO_STEPS);
        game.addPokemon(new Pokemon("Test", 0, 0, 0));
        assertEquals(game.getPokemonCount(), 7);

        Game game2 = new Game(2, GameOverOptions.NO_BALL);

        for(int i = 0; i < 33; i++)
            game2.throwBall();
        assertTrue(game2.gameOver());
        
        Game game3 = new Game(2, GameOverOptions.POKEMON_CAUGHT);
        game3.checkPokemon();
        for(int i = 0; i < 15; i++)
            game3.addPokemon(new Pokemon("Test" , 0, 0, 0));
        assertTrue(game3.gameOver());
                
               
        
    }
   

}
