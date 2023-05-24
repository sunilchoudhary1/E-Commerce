package com.project.ecommerce.exception;

public class EmailAlreadyPresentException extends Exception{
    public EmailAlreadyPresentException(String message){
        super(message);
    }
}
