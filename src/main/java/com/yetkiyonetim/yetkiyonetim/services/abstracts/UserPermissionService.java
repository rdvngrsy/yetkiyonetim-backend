package com.yetkiyonetim.yetkiyonetim.services.abstracts;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;

public interface UserPermissionService {

    void save(CreateUserPermissionRequest userPermission);
    void deleteByUserIdAndPermissionId(Long userId, Long permissionId);
}
