package model;

import java.io.Serializable;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Rock extends MapObject implements Serializable
{
    private static final long serialVersionUID = 1L;

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
