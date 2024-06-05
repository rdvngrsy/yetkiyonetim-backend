package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRolePermissionResponse {
    private Long roleId;
    private Long permissionId;
}
