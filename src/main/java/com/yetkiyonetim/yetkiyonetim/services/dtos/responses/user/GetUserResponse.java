package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserResponse {
    private Long id;
    private String username;
}
