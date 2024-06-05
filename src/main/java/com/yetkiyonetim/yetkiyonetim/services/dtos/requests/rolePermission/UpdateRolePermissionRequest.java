package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRolePermissionRequest {
    private Long roleId;
    private Long permissionId;
}
