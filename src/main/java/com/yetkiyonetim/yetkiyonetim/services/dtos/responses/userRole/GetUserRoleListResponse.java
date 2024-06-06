package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserRoleListResponse {
    private String userUsername;
    private String roleRoleName;
}
