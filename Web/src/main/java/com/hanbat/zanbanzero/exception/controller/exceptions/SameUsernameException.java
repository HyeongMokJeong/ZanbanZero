package com.hanbat.zanbanzero.exception.controller.exceptions;

public class SameUsernameException extends Exception{
    public SameUsernameException() {
    }

    public SameUsernameException(String message) {
        super(message);
    }
}
