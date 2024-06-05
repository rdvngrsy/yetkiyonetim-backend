package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthenticationRequest {
    private String username;
    String password;
}
