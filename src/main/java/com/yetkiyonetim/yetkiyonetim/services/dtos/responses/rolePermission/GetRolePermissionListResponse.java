package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetRolePermissionListResponse {
    private String roleName;
    private String permissionName;
    private String permissionDescription;
    private Long roleId;
    private Long permissionId;
}
