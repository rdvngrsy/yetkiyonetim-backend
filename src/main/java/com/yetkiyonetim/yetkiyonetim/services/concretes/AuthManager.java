package com.yetkiyonetim.yetkiyonetim.services.concretes;


import com.yetkiyonetim.yetkiyonetim.core.services.JwtService;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import com.yetkiyonetim.yetkiyonetim.repositories.UserRepository;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.AuthService;
import com.yetkiyonetim.yetkiyonetim.services.abstracts.UserService;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.authentication.CreateAuthenticationRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.authentication.GetAuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public GetAuthenticationResponse register(CreateUserRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userService.createUser(user);
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public GetAuthenticationResponse authenticate(CreateAuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();

    }


}
