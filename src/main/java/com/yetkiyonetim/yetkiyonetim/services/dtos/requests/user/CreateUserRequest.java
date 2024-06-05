package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    private String password;
}