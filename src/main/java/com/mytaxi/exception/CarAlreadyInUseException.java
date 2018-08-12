package com.mytaxi.exception;


public class CarAlreadyInUseException extends Exception
{

    public CarAlreadyInUseException(final String message)
    {
        super(message);
    }

}
