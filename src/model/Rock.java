package model;

import InterfacesAndEnums.MapObject;

public class Rock extends MapObject
{

    public Rock()
    {
        super(false);
    }

    @Override
    public String toString()
    {
        return "#";
    }

}
