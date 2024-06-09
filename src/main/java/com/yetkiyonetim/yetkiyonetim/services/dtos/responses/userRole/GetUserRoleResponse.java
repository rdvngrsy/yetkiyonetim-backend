package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserRoleResponse {
    private Long userId;
    private Long roleId;
    private String roleName;
}
