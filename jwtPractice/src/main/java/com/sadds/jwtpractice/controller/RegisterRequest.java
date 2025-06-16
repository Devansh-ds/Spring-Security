package com.sadds.jwtpractice.controller;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
