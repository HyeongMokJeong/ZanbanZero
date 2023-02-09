package com.hanbat.zanbanzero.exception.controller.exceptions;

import lombok.Getter;

@Getter
public class SameUsernameException extends Exception{
    public SameUsernameException() {
    }

    public SameUsernameException(String message) {
        super(message);
    }
}
