package com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserPermissionListResponse {
    private Long userId;
    private Long permissionId;
    private User user;
    private Permission permission;
}
