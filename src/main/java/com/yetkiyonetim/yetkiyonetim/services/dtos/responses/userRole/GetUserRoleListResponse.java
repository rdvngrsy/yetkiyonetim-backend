package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserRoleListResponse {
    private Long userId;
    private Long roleId;
}
