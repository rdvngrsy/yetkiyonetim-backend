package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserRoleRequest {
    private Long userId;
    private Long roleId;
}
