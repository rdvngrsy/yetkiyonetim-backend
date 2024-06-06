package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRolePermissionListResponse {
    private String roleRoleName;
    private String permissionPermissionName;
    private String permissionDescription;
}
