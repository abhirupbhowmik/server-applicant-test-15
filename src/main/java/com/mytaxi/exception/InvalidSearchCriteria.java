package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidSearchCriteria extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidSearchCriteria(final String message)
    {
        super(message);
    }
}
