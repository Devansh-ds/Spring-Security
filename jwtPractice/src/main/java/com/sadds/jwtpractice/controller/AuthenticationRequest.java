package com.sadds.jwtpractice.controller;

public record AuthenticationRequest(
        String email,
        String password
) {
}
