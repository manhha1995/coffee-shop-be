package com.dap.coffee.exception;

import javax.security.sasl.AuthenticationException;

public class TokenException extends AuthenticationException {
    public TokenException(String message , Throwable t){
        super(message,t);
    }

    public TokenException(String message){
        super(message);
    }
}
