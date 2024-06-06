package com.yetkiyonetim.yetkiyonetim.services.abstracts;

import com.yetkiyonetim.yetkiyonetim.entities.compositeKey.RolePermissionId;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.CreateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.DeleteRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.requests.rolePermission.UpdateRolePermissionRequest;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionListResponse;
import com.yetkiyonetim.yetkiyonetim.services.dtos.responses.rolePermission.GetRolePermissionResponse;

import java.util.List;

public interface RolePermissionService {
    List<GetRolePermissionListResponse> getAllRolePermissions();
    List<GetRolePermissionResponse> getPermissionsByRoleId(Long roleId);
    List<GetRolePermissionResponse> getRolesByPermissionId(Long permissionId);
    void createRolePermission(CreateRolePermissionRequest createRolePermissionRequest);
//    void updateRolePermission(UpdateRolePermissionRequest updateRolePermissionRequest);
    void deleteRolePermission(DeleteRolePermissionRequest deleteRolePermissionRequest);
}
