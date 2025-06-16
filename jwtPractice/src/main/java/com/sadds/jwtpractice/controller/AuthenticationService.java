package com.sadds.jwtpractice.controller;

import com.sadds.jwtpractice.User.*;
import com.sadds.jwtpractice.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 UserRepository userRepository,
                                 TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponse register(RegisterRequest request) {

        User user = new User(
                request.firstname(),
                request.lastname(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);

        saveUserToken(savedUser, jwtToken);

        return new RegisterResponse(jwtToken);

    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(
                jwtToken,
                false,
                false,
                TokenType.BEARER,
                user
        );
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);

        revokeAllUserToken(user);
        saveUserToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken);
    }

    private void revokeAllUserToken(User user) {
        var tokens  = tokenRepository.findByUser(user);
        if (tokens == null) {
            return;
        }
        tokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(tokens);
    }
}




















