package com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.entities.concretes.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserPermissionRequest {
    private Long userId;
    private Long permissionId;
    private User user;
    private Permission permission;
}
