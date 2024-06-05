package com.yetkiyonetim.yetkiyonetim.services.abstracts;

import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.authentication.CreateAuthenticationRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user.CreateUserRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.authentication.GetAuthenticationResponse;

public interface AuthService {


    GetAuthenticationResponse register(CreateUserRequest request);
    GetAuthenticationResponse authenticate(CreateAuthenticationRequest request);
}
