package model;

import java.io.Serializable;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Grass extends MapObject implements Serializable
{ 

    public Grass()
    {
        super(true);
    }
    
    @Override
    public String toString()
    {
        return "^";
    }

}
