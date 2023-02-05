package com.library.demo.exceptions;

import java.io.IOException;

public class ConflictException extends RuntimeException {

    public ConflictException(String message){
        super(message);
    }
}
