package com.devansh.security;

import com.devansh.exception.TokenInvalidException;
import com.devansh.exception.UserAlreadyExistException;
import com.devansh.exception.UserException;
import com.devansh.model.Role;
import com.devansh.request.AuthenticationRequest;
import com.devansh.request.OtpVerificationRequest;
import com.devansh.request.RegisterRequest;
import com.devansh.request.ResetPasswordRequest;
import com.devansh.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request) throws UserAlreadyExistException {
        request.setRole(Role.USER);
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity register(
            @RequestBody AuthenticationRequest request) throws UserException {
        return authenticationService.authenticate(request);

    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader("Authorization") String refreshToken)
            throws IOException, TokenInvalidException {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity otpVerificationHandler(@RequestBody OtpVerificationRequest request) throws ExecutionException {
        return authenticationService.verifyOtp(request);
    }

    @PutMapping("/reset-password")
    public ResponseEntity resetPasswordHandler(@RequestBody ResetPasswordRequest request) throws UserException {
        return authenticationService.resetPassword(request);
    }




}

