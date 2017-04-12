package model;

import java.awt.Point;

import InterfacesAndEnums.Direction;
import InterfacesAndEnums.GameInterface;
import InterfacesAndEnums.MapObject;

public class Game implements GameInterface
{
    private Map gameMap;
    private Trainer trainer;

    @Override
    public MapObject[][] getMap()
    {
        return gameMap.getMap();
    }

    @Override
    public Point getTrainerPosition()
    {
        return trainer.getPosition();
    }

    @Override
    public int getRemainingSteps()
    {
        return trainer.getRemainingStep();
    }

    @Override
    public void moveTrainer(Direction dir)
    {
        trainer.move(dir);
    }

}
