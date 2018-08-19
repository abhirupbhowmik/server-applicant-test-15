package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoResultFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoResultFoundException(final String message)
    {
        super(message);
    }
}
