package edu.uchicago.gerber.quark.repositories;

public class IdNotFoundException extends Exception
{
    public IdNotFoundException (String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
