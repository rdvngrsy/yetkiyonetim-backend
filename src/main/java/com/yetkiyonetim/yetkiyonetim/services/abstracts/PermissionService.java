package com.yetkiyonetim.yetkiyonetim.services.abstracts;

import com.yetkiyonetim.yetkiyonetim.entities.concretes.Permission;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.CreatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.permission.UpdatePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.permission.GetPermissionResponse;

import java.util.List;

public interface PermissionService {
    List<GetPermissionListResponse> getAllPermissions();
    GetPermissionResponse getPermissionById(Long id);
    void createPermission(CreatePermissionRequest createPermissionRequest);
    void updatePermission(UpdatePermissionRequest updatePermissionRequest);
    void deletePermission(Long id);
}