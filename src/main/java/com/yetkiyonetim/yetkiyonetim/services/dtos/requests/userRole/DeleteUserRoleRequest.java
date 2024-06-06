package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteUserRoleRequest {
    private Long userId;
    private Long roleId;
}
