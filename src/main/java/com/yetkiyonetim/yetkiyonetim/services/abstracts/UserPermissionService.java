package com.yetkiyonetim.yetkiyonetim.services.abstracts;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.UserPermission;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.userPermission.CreateUserPermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission.GetUserPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.userPermission.GetUserPermissionResponse;

import java.util.List;

public interface UserPermissionService {
    List<GetUserPermissionListResponse> getAllUserPermissions();
    List<GetUserPermissionResponse> getPermissionsByUserId(Long userId);
    List<GetUserPermissionResponse> getUserByPermissionId(Long permissionId);
    void save(CreateUserPermissionRequest userPermission);
    void deleteByUserIdAndPermissionId(Long userId, Long permissionId);
}
