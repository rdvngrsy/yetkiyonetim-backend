package com.yetkiyonetim.yetkiyonetim.controllers;

import com.yetkiyonetim.yetkiyonetim.services.abstracts.AuthService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.authentication.CreateAuthenticationRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.authentication.GetAuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<GetAuthenticationResponse> register(
            @RequestBody CreateUserRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }



    @PostMapping("/authenticate")
    public ResponseEntity<GetAuthenticationResponse> authenticate(
            @RequestBody CreateAuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
