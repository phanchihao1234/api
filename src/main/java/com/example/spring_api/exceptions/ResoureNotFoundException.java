package com.example.spring_api.exceptions;

public class ResoureNotFoundException extends RuntimeException{
    public ResoureNotFoundException(String message){
        super(message);
    }
}
