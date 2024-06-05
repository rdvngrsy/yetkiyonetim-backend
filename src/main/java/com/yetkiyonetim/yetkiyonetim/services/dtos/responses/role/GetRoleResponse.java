package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRoleResponse {
    private Long id;
    private String roleName;
    private String description;
}
