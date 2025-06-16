package com.devansh.security;

public class TokenInvalidException extends Exception {
    public TokenInvalidException(String message) {
        super(message);
    }
}
