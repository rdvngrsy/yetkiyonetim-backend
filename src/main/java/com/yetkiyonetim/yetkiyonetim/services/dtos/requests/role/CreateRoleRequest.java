package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateRoleRequest {
    private String roleName;
    private String description;
}