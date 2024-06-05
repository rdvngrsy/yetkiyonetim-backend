package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.user;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class GetUserListResponse {
    private Long id;
    private String username;

}
