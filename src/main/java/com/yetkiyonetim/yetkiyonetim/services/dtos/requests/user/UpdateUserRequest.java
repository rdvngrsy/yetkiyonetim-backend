package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String username;
    private String password;
}